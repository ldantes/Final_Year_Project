package web.servlets;

import java.io.IOException;
import java.math.BigDecimal;
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
import model.beans.AccountBean;
import model.beans.ServiceUserBean;
import model.beans.TransactionBean;
import model.data.cmsQueryAccount;
import model.data.cmsQueryDateToClean;
import model.data.cmsQueryServiceUser;
import model.data.cmsQuerySubstance;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * Servlet implementation class ServiceUserServlet
 * 
 *  20-Nov-13 Servlet established. First action : search service user.
 *  
 */


public class ServiceUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher 	requestDispatch;
	
	
	private enum validActions
	{
	   srchServiceUser, editServiceUser, updateServiceUser, newSubstanceEntry,addDTCEx, insertNewSubstanceResult, newEngagmentEntry , insertNewAttendance, viewAccount, newTransaction, viewNotes,newNote, updateNote; 
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
		String userMessage	= "";
		String firedrules = "";
		HttpSession session = request.getSession();  
		if (request.getRequestedSessionId().equals(session.getId())){  
		    
		   	String logUserName 	= session.getAttribute("username").toString();
			
			WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			
			serviceUserService service = new serviceUserService();
			String id = request.getParameter("serviceUserId");	
				
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
						
						if(id != "" && id != null  )
						{
							service.setReferenceInformation(id);
							
							destination	= "/editServiceUser.jsp";
							request.setAttribute("serviceUser",service.getServiceUserBean());
							
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
						
						if( id != null && id.length() != 0 && id != "")
						{
							try {
								service.updateServiceuser();
								destination	= "/editServiceUser.jsp";
								service.setReferenceInformation(id);
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
							service.setReferenceInformation(service.getServiceUserBean().getId());
							request.setAttribute("serviceUser",service.getServiceUserBean());
							userMessage =service.userMessage;
							request.setAttribute("userMsg",userMessage);
						}
						
							break;
							
					case newSubstanceEntry:
						
						service.setApplicationContext(applicationContext);
						service.setRequest(request);
						id = request.getParameter("serviceUserId");
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
						userMessage =service.userMessage;
						firedrules=service.firedrules;
						request.setAttribute("userMsg",userMessage);
						request.setAttribute("firedrules",firedrules);
						destination="/editServiceUser.jsp";
						
					
						break;
						
						
					case newEngagmentEntry:
						
						service.setApplicationContext(applicationContext);
						service.setRequest(request);
						id = request.getParameter("serviceUserId");
						service.setReferenceInformation(id);
						
						request.setAttribute("serviceUser",service.getServiceUserBean());
						request.setAttribute("attendanceDetails",service.attendanceDetails);
						
						destination="/editViewAttendance.jsp";
						break;
					
					case insertNewAttendance:
						
						String logUserProfession 	= session.getAttribute("userprofession").toString();
						service.setApplicationContext(applicationContext);
						service.setRequest(request);
						service.newAttendance(logUserProfession);
											
						request.setAttribute("serviceUser",service.getServiceUserBean());
						userMessage =service.userMessage;
						firedrules=service.firedrules;
						request.setAttribute("userMsg",userMessage);
						request.setAttribute("firedrules",firedrules);
						destination="/editServiceUser.jsp";
						break;
						
					case viewAccount:
						
						service.setApplicationContext(applicationContext);
						service.setRequest(request);
						id = request.getParameter("serviceUserId");
						service.setReferenceInformation(id);
						AccountBean accountDetails = cmsQueryAccount.srvUserAccount(id);
						
						request.setAttribute("accountDetails", accountDetails );
						request.setAttribute("serviceUser", service.getServiceUserBean() );
						destination="/viewAccount.jsp";
						break;
						
					case newTransaction:
						
						service.setApplicationContext(applicationContext);
						service.setRequest(request);
						
						///
						service.setReferenceInformation(id);
						
						TransactionBean transaction = new TransactionBean();
						
						transaction.setAccount_Id(id);
						
						if(request.getParameter("credit")!= null && request.getParameter("credit").length() != 0)
						{
							transaction.setAmount_Credited(new BigDecimal(request.getParameter("credit")));	
						}
						
						transaction.setAmount_Withdrawn(new BigDecimal(0));	
						if(service.serviceUserBean.getEligibilityBeans().get(0).getActive().equals("Y"))
						{
							if(request.getParameter("withdraw")!= null && request.getParameter("withdraw").length() != 0)
							{
								transaction.setAmount_Withdrawn(new BigDecimal(request.getParameter("withdraw")));	
							}
							
						}
						transaction.setApproved_By(request.getParameter("username"));
							
						if(transaction.getAmount_Withdrawn().floatValue() <= service.serviceUserBean.getAccountDetails().getAccount_Balance().floatValue())
						{
							service.adjustBalance(transaction);
							id = request.getParameter("serviceUserId");
							service.setReferenceInformation(id);
							AccountBean accountDetails2 = cmsQueryAccount.srvUserAccount(id);
							service.setReferenceInformation(id);
							request.setAttribute("accountDetails", accountDetails2 );
							request.setAttribute("serviceUser", service.getServiceUserBean() );
							destination="/viewAccount.jsp";
							request.setAttribute("userMsg","Transaction Successful");
						}
						else
						{
							request.setAttribute("userMsg","Requested withdraw amount exceeds current credit");
						}
						break;
						
					case viewNotes:
						id = request.getParameter("serviceUserId");
						request.setAttribute("notes",cmsQueryServiceUser.qryServiceUserNotes(id));
						request.setAttribute("serviceUser", cmsQueryServiceUser.searchServiceUsersById(id) );
						destination="/viewNotes.jsp";
						break;
						
					case newNote:
						
						service.setApplicationContext(applicationContext);
						service.setRequest(request);
						service.addNewNote();
						id = request.getParameter("serviceUserId");
						request.setAttribute("notes",cmsQueryServiceUser.qryServiceUserNotes(id));
						request.setAttribute("serviceUser", cmsQueryServiceUser.searchServiceUsersById(id) );
						destination="/viewNotes.jsp";
						break;
						
					case updateNote:
						service.setApplicationContext(applicationContext);
						service.setRequest(request);
						service.updateNote();
						id = request.getParameter("serviceUserId");
						request.setAttribute("notes",cmsQueryServiceUser.qryServiceUserNotes(id));
						request.setAttribute("serviceUser", cmsQueryServiceUser.searchServiceUsersById(id) );
						destination="/viewNotes.jsp";
						break;
						
					case addDTCEx:
						service.setApplicationContext(applicationContext);
						service.setRequest(request);
						service.setReferenceInformation(id);
						System.out.print("HERE");
						if (service.getServiceUserBean().getDateToClean().getExtensionApplied() ==null || service.getServiceUserBean().getDateToClean().getExtensionApplied() !="Y")
						{
							
								service.getServiceUserBean().getDateToClean().setExtensionApplied("Y");
								cmsQueryDateToClean.updateDTC(service.getServiceUserBean());
							
						}
						service.setReferenceInformation(id);
						request.setAttribute("serviceUser",service.getServiceUserBean());
						destination="/editServiceUser.jsp";
						break;
						
						
					
						
				}
				
				request.setAttribute("username", logUserName);
				requestDispatch = request.getRequestDispatcher(jsp_path+destination);
				requestDispatch.forward(request,response);
			
		}
		else
		{
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
			userMessage= "Session Timed Out";
			request.setAttribute("userMessage", userMessage);
			requestDispatch = request.getRequestDispatcher(jsp_path+destination);
			requestDispatch.forward(request,response);
		}
		
	}

}
