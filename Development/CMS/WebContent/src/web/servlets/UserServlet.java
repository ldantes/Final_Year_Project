package web.servlets;

import java.io.IOException;
import java.sql.SQLException;


import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.beans.UserBean;
import model.beans.UserRoleBean;
import model.business.facade.UserFacade;
import model.data.cmsQueryUsers;





/*********************************************************************************************
File History

09-NOV-2013	UserServlet is created to receive Login form
16-NOV-2013 Redirection on successful login to Main page.
16-NOV-2013 Set User session variables using the HttpServletRequest variable
20-Nov-2013 Altered the User Roles to be attained through the DAO after querying login details.
			UserRoles are user stored in a List of UserRole beans which are an attribute of a Userbean.
		
************************************************************************************************/
public class UserServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RequestDispatcher 	requestDispatch;
	HttpSession session=null;
	
	//assign valid actions
	private enum validActions
	{
	   login, logout , init , editUser, saveUser; 
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	
		throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)		
	throws ServletException, IOException {
		String destination	= "/index.jsp";
		String jsp_path	= "/WEB-INF/jsp";
		String userMessage	= null;
		
			WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			UserFacade userFacade = (UserFacade)applicationContext.getBean("userFacade");
			
					
			String action= request.getParameter("requestAction");	
			
			
			switch (validActions.valueOf(action))
			{
		
				case login:
					UserBean 	userDetails 	=null;
					try 
					{
						String usernameEnter = request.getParameter("username");
						String passwordEntered = request.getParameter("password");
						
						if(usernameEnter != "" && passwordEntered != "" && usernameEnter != null && passwordEntered != null)
						{
							userDetails= userFacade.authenticateUser(usernameEnter, passwordEntered);
						}
						
	
						if(userDetails != null)
						{
							session = request.getSession(true);
							
							
							request.setAttribute("userDetails", userDetails);
							session.setAttribute("userprofession", userDetails.getProfession());
							session.setAttribute("username", userDetails.getUserName());	
							
							List <UserRoleBean> userRoles 	=null;
							userRoles = cmsQueryUsers.qryUserRoles(userDetails);
							userDetails.setUserRoles(userRoles);
							
							
							boolean userAdmin=false;
							boolean userAccountant=false;
							boolean userReports = false;
							 
							 for (int i = 0 ; i< userDetails.getUserRoles().size();i++)
							 {
								 
								 if (userDetails.getUserRoles().get(i).getRoleName().equals("admin"))
								 {
									 userAdmin=true;	
								 }
								 
								 if (userDetails.getUserRoles().get(i).getRoleName().equals("accounting"))
								 {
									 userAccountant=true;	
								 }
								 
								 if (userDetails.getUserRoles().get(i).getRoleName().equals("reports"))
								 {
									 userReports=true;	
								 }
								 
							 }
							
							 session.setAttribute("userAdmin", userAdmin);	
							 session.setAttribute("userAccountant", userAccountant);
							 session.setAttribute("userReports", userReports);
							
							
							request.setAttribute("username", userDetails.getUserName());
							request.setAttribute("userRoles", userDetails.getUserRoles());
							request.setAttribute("userMessage", userMessage);
							
							destination= "/ServiceUsersSrch.jsp";
							
						}
						else
						{
							jsp_path	= "";
							destination= "/index.jsp";	
							userMessage= "Entered details do not match any active users";
							request.setAttribute("userMessage", userMessage);
						}
					} 
					catch (SQLException e) 
					{
						
						
						userMessage	= e.getMessage();
						e.printStackTrace();
					} 
					catch (NamingException e) 
					{
						
						userMessage	= e.getMessage();
						e.printStackTrace();
					}
					break;
				
				case logout:
					
					response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
					response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
					session = request.getSession(true);
					session.removeAttribute("userDetails");
					session.removeAttribute("userprofession");
					session.removeAttribute("username");
					session.removeAttribute("userAdmin");
					session.removeAttribute("userAccountant");
					session.removeAttribute("userReports");
										
					session.invalidate();
					jsp_path	= "";
					destination= "/index.jsp";	
					userMessage= "Logged Out";
					request.setAttribute("userMessage", userMessage);
					
					break;
				
				case init:
					
					request.setAttribute("users", cmsQueryUsers.qryUsers(null));
					System.out.print(cmsQueryUsers.qryUsers(null).size());
					destination= "/userManagaement.jsp";
					break;
						
				case editUser:
					
					request.setAttribute("selecteduser", cmsQueryUsers.getUserByName(request.getParameter("userId")));
					request.setAttribute("users", cmsQueryUsers.qryUsers(null));
					System.out.print(cmsQueryUsers.qryUsers(null).size());
					destination= "/userManagaement.jsp";
					break;
					
				case saveUser:
					
					UserBean user = new UserBean();
					if( request.getParameter("enteredusername") != null)
					{
					user.setUserName(request.getParameter("enteredusername"));
					}
					else
					{
						user.setUserName(request.getParameter("setusername"));
					}
					user.setFirstName(request.getParameter("fname"));
					user.setSurname(request.getParameter("sname"));
					user.setActive(request.getParameter("active"));
					user.setProfession(request.getParameter("profession"));
					user.setEmail(request.getParameter("email"));
					user.setPassword(request.getParameter("password"));
					user.setUpdatedBy(request.getParameter("username"));
					
					
					userFacade.editUser(user);
					
					request.setAttribute("selecteduser", cmsQueryUsers.getUserByName(request.getParameter(user.getUserName())));
					request.setAttribute("users", cmsQueryUsers.qryUsers(null));
					System.out.print(cmsQueryUsers.qryUsers(null).size());
					destination= "/userManagaement.jsp";
					break;
						
			}			
			
			
			requestDispatch = request.getRequestDispatcher(jsp_path+destination);
			requestDispatch.forward(request,response);
	}
}



