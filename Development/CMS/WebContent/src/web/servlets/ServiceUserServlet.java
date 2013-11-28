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
import model.data.cmsQueryServiceUser;

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
	   srchServiceUser; 
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
		
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			
			
			String action= request.getParameter("requestAction");	
			
			
			switch (validActions.valueOf(action)){
		
				case srchServiceUser:
						String searchedName = request.getParameter("serviceUserName");
						List<ServiceUserBean> searchedUsers = cmsQueryServiceUser.searchServiceUsers(searchedName);
						request.setAttribute("serviceUserResults", searchedUsers);
					
					
			}
			
			requestDispatch = request.getRequestDispatcher(jsp_path+destination);
			requestDispatch.forward(request,response);
	}

}
