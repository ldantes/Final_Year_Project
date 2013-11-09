package web.servlets;

import java.io.IOException;


import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.beans.UserBean;
import model.business.facade.UserFacade;




/*********************************************************************************************
File History

9-APR-2013	UserServlet is created to receive Login form.
		
************************************************************************************************/
public class UserServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RequestDispatcher 	requestDispatch;

	
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
		String destination	= "/index.html";
		//String jsp_path	= "/WEB-INF/jsp/";
		String userMessage	= null;
		
			WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			UserFacade userFacade = (UserFacade)applicationContext.getBean("userFacade");
			
			String action= request.getParameter("requestAction");	
			
			
			switch (validActions.valueOf(action)){
		
				case login:
					UserBean 	logUserEntry 	=null;
									
				try {
					logUserEntry= userFacade.authenticateUser(request.getParameter("username"), request.getParameter("password"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					if(logUserEntry != null){
										
							destination= "/index.jsp";
							request.setAttribute("username", logUserEntry.getUserName());
							request.setAttribute("userMessage", "Success");
							
					}else
					{
							destination= "/index.jsp";	
							request.setAttribute("userMessage", "Failure");
					}
						
						
			}			
			
			requestDispatch = request.getRequestDispatcher(destination);
			requestDispatch.forward(request,response);
	}
}



