package web.services;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.beans.AttendanceBean;
import model.beans.ServiceUserBean;
import model.beans.SubstanceBean;
import model.beans.UserBean;
import model.business.facade.ServiceUserFacade;
import model.data.cmsQueryAttendance;
import model.data.cmsQueryServiceUser;
import model.data.cmsQuerySubstance;
import model.data.cmsQueryUsers;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class serviceUserService{
	
	public String userMessage = "";
	private ApplicationContext 		applicationContext=null;
	private HttpServletRequest      request=null;
	public ServiceUserBean serviceUserBean;
	public List<SubstanceBean> substanceBeans;
	public List<UserBean> userBeans;
	public List<AttendanceBean> attendanceDetails;
	
	public void setApplicationContext(ApplicationContext applicationContext){
		this.applicationContext=applicationContext;
	}
	
	public void setRequest(HttpServletRequest request){
		this.request=request;
	}
	
	public void setServiceUserBean(ServiceUserBean serviceUserBean){
		this.serviceUserBean = serviceUserBean;
	}
	
	public ServiceUserBean getServiceUserBean( )
	{
		return this.serviceUserBean;
	}
	
	
	public void updateServiceuser() throws SQLException, NamingException
	{
		ServiceUserBean serviceUserBean = new ServiceUserBean();
		populateServiceUserbean(serviceUserBean);
		ServiceUserFacade serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		serviceUserBean = serviceUserFacade.updateServiceUser(serviceUserBean);
		
		this.userMessage	= "Successful Transaction. Service User updated";	
		this.serviceUserBean = serviceUserBean;
	}

	public void newServiceUser() {
		ServiceUserBean serviceUserBean = new ServiceUserBean();
		populateServiceUserbean(serviceUserBean);
		ServiceUserFacade serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		serviceUserBean = serviceUserFacade.addServiceUser(serviceUserBean);
		
		this.userMessage	= "Successful Transaction. Service User added";	
		this.serviceUserBean = serviceUserBean;
	}
	
	public void populateServiceUserbean(ServiceUserBean serviceUserBean)
	{
		String id = this.request.getParameter("srvUserid").toString();
		serviceUserBean.setId(id);
		String name = this.request.getParameter("srvUsername").toString();
		serviceUserBean.setName(name);
		serviceUserBean.setGender(request.getParameter("srvUserGender").toString());
		serviceUserBean.setDoB(request.getParameter("srvUserDOB").toString());
		serviceUserBean.setAddress(request.getParameter("srvUserAddress").toString());
		serviceUserBean.setContactNumber(request.getParameter("srvUserContactactNumber").toString());
		serviceUserBean.setEthnicity(request.getParameter("srvUserEthnicity").toString());
		serviceUserBean.setNationality(request.getParameter("srvUserNationality").toString());
		serviceUserBean.setFamilyInformation(request.getParameter("srvUserFamily").toString());
		serviceUserBean.setOccupation(request.getParameter("srvUserOccupation").toString());
		serviceUserBean.setUpdatedBy(request.getParameter("username").toString());
	}
	
	public void setReferenceInformation(String id)
	{
		this.substanceBeans = cmsQuerySubstance.qrySubstances(null);
		this.attendanceDetails =cmsQueryAttendance.srvUserAttendance(id);
		this.serviceUserBean = cmsQueryServiceUser.searchServiceUsersById(id);
		this.userBeans = cmsQueryUsers.qryUsers("Y");
		
	}
	
	public void newSubstanceResult()
	{
		String id = request.getParameter("srvUserid");
		setReferenceInformation(id);
		
		for(int i=0; i < this.substanceBeans.size(); i++)
		{
			SubstanceBean substanceBean = new SubstanceBean();
			substanceBean.setSrvUserId(id);
			substanceBean.setSubstance(this.substanceBeans.get(i).getSubstance());
			substanceBean.setResult(request.getParameter("result"+this.substanceBeans.get(i).getSubstance()));
			substanceBean.setAdministeredBy(request.getParameter("administeredBy"));
			substanceBean.setAdministeredOn(request.getParameter("administeredOn"));
			substanceBean.setCreatedBy(request.getParameter("username"));
			
			ServiceUserFacade serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
			serviceUserFacade.newSubstanceResult(substanceBean);
			
		}
		this.userMessage="Service Test results successfull added";
	}

	public void newAttendance() 
	{
		String id = request.getParameter("srvUserid");
				
		AttendanceBean attendanceBean = new AttendanceBean();
		attendanceBean.setSrvUserId(id);
		attendanceBean.setUsername(request.getParameter("username"));
		attendanceBean.setAttended(request.getParameter("attended"));
		attendanceBean.setTimeDate(request.getParameter("attndDate")+" "+request.getParameter("attndTime"));
		attendanceBean.setAttndFailedReason(request.getParameter("missedReason"));
		attendanceBean.setValidReason(request.getParameter("reasonValid"));
		attendanceBean.setTreatmentReviewMeeting(request.getParameter("reviewMeeting"));
			
		ServiceUserFacade serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		serviceUserFacade.newAttendance(attendanceBean);
		
		this.serviceUserBean = cmsQueryServiceUser.searchServiceUsersById(id);
		
		this.userMessage="Service Test results successfull added";
	
			
	}
	
}