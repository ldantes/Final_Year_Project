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

import web.services.serviceUserService;
import model.beans.ServiceUserBean;
import model.beans.SubstanceBean;
import model.beans.UserBean;
import model.data.cmsQueryServiceUser;
import model.data.cmsQuerySubstance;
import model.data.cmsQueryUsers;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * Servlet implementation class ServiceUserServlet
 * 
 * 
 * 20-Nov-13 Servlet established. First action : search service user.
 */


public class ServiceUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher 	requestDispatch;
	
	
	private enum validActions
	{
	   srchServiceUser, editServiceUser, updateServiceUser, newSubstanceEntry, insertNewSubstanceResult, newEngagmentEntry , insertNewAttendance; 
	}
	
    public ServiceUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination	= "/ServiceUsersSrch.jsp";
		String jsp_path	= "/WEB-INF/jsp";
		String userMessage	= null;
		HttpSession session = request.getSession(true);	
		String logUserName 	= session.getAttribute("username").toString();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		
		serviceUserService service = new serviceUserService();
			
			
			String action= request.getParameter("requestAction");	
			
			
			switch (validActions.valueOf(action)){
		
				case srchServiceUser:
						if ( request.getParameter("serviceUserName") != null)
						{
							String searchedName = request.getParameter("serviceUserName");
							List<ServiceUserBean> searchedUsers = cmsQueryServiceUser.searchServiceUsersByName(searchedName);
							request.setAttribute("serviceUserResults", searchedUsers);
						}
						destination	= "/ServiceUsersSrch.jsp";
						break;
						
				case editServiceUser:
					String srchId = request.getParameter("serviceUserId");
					if(srchId != "" && srchId != null  )
					{
						ServiceUserBean searchedUser = (ServiceUserBean) cmsQueryServiceUser.searchServiceUsersById( srchId);
						destination	= "/editServiceUser.jsp";
						request.setAttribute("serviceUser",searchedUser);
						
					}
					else
					{
						ServiceUserBean serviceUser = new ServiceUserBean();
						serviceUser.setId("");
						destination	= "/editServiceUser.jsp";
						request.setAttribute("serviceUser",serviceUser);
					}
					break;
				
				case updateServiceUser:
					service.setApplicationContext(applicationContext);
					service.setRequest(request);
					String servId =request.getParameter("srvUserid");
					if( servId != null && servId.length() != 0)
					{
						try {
							service.updateServiceuser();
							destination	= "/editServiceUser.jsp";
							request.setAttribute("serviceUser",service.getServiceUserBean());
							userMessage =service.userMessage;
							request.setAttribute("userMsg",userMessage);
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NamingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
						service.newServiceUser();
						destination	= "/editServiceUser.jsp";
						request.setAttribute("serviceUser",service.getServiceUserBean());
						userMessage =service.userMessage;
						request.setAttribute("userMsg",userMessage);
					}
					
						break;
						
				case newSubstanceEntry:
					
					service.setApplicationContext(applicationContext);
					service.setRequest(request);
					String id = request.getParameter("serviceUserId");
					service.setReferenceInformation(id);
					
					request.setAttribute("serviceUser",service.getServiceUserBean());
					request.setAttribute("substanceBeans",service.substanceBeans);
					request.setAttribute("userBeans",service.userBeans);
					request.setAttribute("previousResultsBeans",cmsQuerySubstance.qryServiceUserResults(id));
					destination="/editViewSubstance.jsp";
					break;
					
				case insertNewSubstanceResult:
					service.setApplicationContext(applicationContext);
					service.setRequest(request);
					service.newSubstanceResult();
										
					request.setAttribute("serviceUser",service.getServiceUserBean());
//					request.setAttribute("substanceBeans",service.substanceBeans);
//					request.setAttribute("userBeans",service.userBeans);
//					request.setAttribute("previousResultsBeans",cmsQuerySubstance.qryServiceUserResults(service.getServiceUserBean().getId()));
					userMessage =service.userMessage;
					request.setAttribute("userMsg",userMessage);
					destination="/editServiceUser.jsp";
					break;
					
					
				case newEngagmentEntry:
					
					service.setApplicationContext(applicationContext);
					service.setRequest(request);
					String id2 = request.getParameter("serviceUserId");
					service.setReferenceInformation(id2);
					
					request.setAttribute("serviceUser",service.getServiceUserBean());
					request.setAttribute("attendanceDetails",service.attendanceDetails);
					
					destination="/editViewAttendance.jsp";
					break;
				
				case insertNewAttendance:
					
					service.setApplicationContext(applicationContext);
					service.setRequest(request);
					service.newAttendance();
										
					request.setAttribute("serviceUser",service.getServiceUserBean());
					userMessage =service.userMessage;
					request.setAttribute("userMsg",userMessage);
					destination="/editServiceUser.jsp";
					
					
				
					
			}
			
			request.setAttribute("username", logUserName);
			requestDispatch = request.getRequestDispatcher(jsp_path+destination);
			requestDispatch.forward(request,response);
	}

}
