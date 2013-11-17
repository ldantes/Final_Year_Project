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
	   login; 
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	
		throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)		
	throws ServletException, IOException {
		String destination	= "/test.jsp";
		String jsp_path	= "/WEB-INF/jsp";
		String userMessage	= null;
		
			WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			UserFacade userFacade = (UserFacade)applicationContext.getBean("userFacade");
			
			String action= request.getParameter("requestAction");	
			
			
			switch (validActions.valueOf(action)){
		
				case login:
					UserBean 	userDetails 	=null;
					List <UserRoleBean> userRoles 	=null;
									
				try 
				{
					userDetails= userFacade.authenticateUser(request.getParameter("username"), request.getParameter("password"));

					if(userDetails != null){
						session = request.getSession(true);
						
						
						request.setAttribute("userDetails", userDetails);
						session.setAttribute("userDetails", userDetails);	
						userRoles = cmsQueryUsers.qryUserRoles(userDetails);
						session.setAttribute("userRoles", userRoles);
						boolean userAdmin=false;
						boolean userAccountant=false;
						boolean userReports = false;
						 
						 for (int i = 0 ; i> userRoles.size();i++){
							 
							 if (userRoles.get(i).getRoleName().equals("admin")){
								 userAdmin=true;	
							 }
							 
							 if (userRoles.get(i).getRoleName().equals("accountant")){
								 userAccountant=true;	
							 }
							 
							 if (userRoles.get(i).getRoleName().equals("reports")){
								 userReports=true;	
							 }
							 
						 }
						
						 session.setAttribute("userAdmin", userAdmin);	
						 session.setAttribute("userAccountant", userAccountant);
						 session.setAttribute("userReports", userReports);
						
						
						request.setAttribute("username", userDetails.getUserName());
						request.setAttribute("userRoles", userRoles);
						request.setAttribute("userMessage", userMessage);
						
						destination= "/ServiceUsersSrch.jsp";
					}
					else
					{
						destination= "/test.jsp";	
						userMessage= "Failure";
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
				
					
						
						
			}			
			
			requestDispatch = request.getRequestDispatcher(jsp_path+destination);
			requestDispatch.forward(request,response);
	}
}



