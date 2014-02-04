package web.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.beans.AttendanceBean;
import model.beans.EligibilityBean;
import model.beans.NoteBean;
import model.beans.ServiceUserBean;
import model.beans.StreamBean;
import model.beans.SubstanceAccumBean;
import model.beans.SubstanceBean;
import model.beans.TransactionBean;
import model.beans.UserBean;
import model.business.facade.ServiceUserFacade;
import model.data.cmsQueryAccount;
import model.data.cmsQueryAttendance;
import model.data.cmsQueryEligibility;
import model.data.cmsQueryServiceUser;
import model.data.cmsQuerySubstance;
import model.data.cmsQueryUsers;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import Rules.SubstanceRules;
import utilities.DataSourceManager;

public class serviceUserService{
	
	public String userMessage = "";
	private ApplicationContext 		applicationContext=null;
	private HttpServletRequest      request=null;
	public ServiceUserBean serviceUserBean;
	public List<SubstanceBean> substanceBeans;
	public List<UserBean> userBeans;
	public List<AttendanceBean> attendanceDetails;
	public List<EligibilityBean> eligibilityBeans;
	
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
		serviceUserBean.setPps(request.getParameter("srvPPS").toString());
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
		this.eligibilityBeans = cmsQueryEligibility.srvUserEligibility(id);
				
		this.serviceUserBean.setSubstanceDetails(this.substanceBeans);
		this.serviceUserBean.setAttendanceDetails(this.attendanceDetails);
		this.serviceUserBean.setEligibilityBeans(this.eligibilityBeans);
		this.serviceUserBean.setSubAccums(cmsQuerySubstance.qrySubstanceAccum(id));
		this.serviceUserBean.setAccountDetails(cmsQueryAccount.srvUserAccount(id));
		
	}
	
	public void newSubstanceResult()
	{
		String id = request.getParameter("srvUserid");
		setReferenceInformation(id);
		List<SubstanceAccumBean> subAccumList = cmsQuerySubstance.qrySubstanceAccum(id);
		int accum = 0;
		String substance= "";
		String testedSubstancce ="";
		float credit =0;
		float creditThisWeek =0;
		ServiceUserFacade serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		
		for(int i=0; i < this.substanceBeans.size(); i++)
		{
			SubstanceBean substanceBean = new SubstanceBean();
			substanceBean.setSrvUserId(id);
			substanceBean.setSubstance(this.substanceBeans.get(i).getSubstance());
			substanceBean.setResult(request.getParameter("result"+this.substanceBeans.get(i).getSubstance()));
			substanceBean.setAdministeredBy(request.getParameter("administeredBy"));
			substanceBean.setAdministeredOn(request.getParameter("administeredOn"));
			substanceBean.setCreatedBy(request.getParameter("username"));
			
			
			for(int j=0; j < subAccumList.size(); j++)
			{
				substance = subAccumList.get(j).getSubstance().toString().toLowerCase();
				testedSubstancce = substanceBean.getSubstance().toString().toLowerCase();
				if(substance.equals(testedSubstancce))
				{
					if(substanceBean.getResult().equals("F"))
					{
						accum = Integer.parseInt(this.substanceBeans.get(i).getResetValue());
						
					}
					else
					{
						accum = Integer.parseInt(subAccumList.get(j).getAccum())+1;
						if(accum > Integer.parseInt(this.substanceBeans.get(i).getMaxValue()))
						{
							accum = Integer.parseInt(this.substanceBeans.get(i).getMaxValue());
						}
						
					}
					break;
				}
				credit = credit + (accum * this.serviceUserBean.getStreamDetails().getPointConversion());
			}
		
			
			
			serviceUserFacade.newSubstanceResult(substanceBean, accum);
			
		};
		
		creditThisWeek = cmsQueryAccount.queryWeekCredit(id);
		if((creditThisWeek + credit) > this.serviceUserBean.getStreamDetails().getMaxPoints())
		{
			credit = this.serviceUserBean.getStreamDetails().getMaxPoints() - creditThisWeek;
		}
		
		TransactionBean transaction = new TransactionBean();
		transaction.setAccount_Id(id);
		transaction.setAmount_Credited(""+credit+"");
		transaction.setAmount_Withdrawn("0");
		transaction.setApproved_By(request.getParameter("administeredBy"));
		
		serviceUserFacade.adjustBalance(transaction);
		setReferenceInformation(id);
		this.userMessage="Service Test results successfull added";
		SubstanceRules.adjustSubstanceAccumaltor(this.serviceUserBean);
	}

	public void newAttendance(String logUserProfession) 
	{
		String id = request.getParameter("srvUserid");
				
		AttendanceBean attendanceBean = new AttendanceBean();
		attendanceBean.setSrvUserId(id);
		attendanceBean.setUsername(request.getParameter("username"));
		attendanceBean.setAttended(request.getParameter("attended"));
		attendanceBean.setTimeDate(request.getParameter("attndDate")+" "+request.getParameter("attndTime"));
		attendanceBean.setAttndFailedReason(request.getParameter("missedReason"));
		attendanceBean.setValidReason(request.getParameter("reasonValid"));
		attendanceBean.setParticipation(request.getParameter("particaption"));
		attendanceBean.setParticipation(request.getParameter("particaption"));
		attendanceBean.setTreatmentReviewMeeting(request.getParameter("reviewMeeting"));
		attendanceBean.setStaffProfession(logUserProfession);
		
		ServiceUserFacade serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		serviceUserFacade.newAttendance(attendanceBean);
		
		this.serviceUserBean = cmsQueryServiceUser.searchServiceUsersById(id);
		
		this.userMessage="Service Test results successfull added";
	
			
	}
	
	public void adjustBalance() 
	{
		String id = request.getParameter("serviceUserId");
		
		TransactionBean transaction = new TransactionBean();
		
		transaction.setAccount_Id(id);
		
		if(request.getParameter("credit")!= null && request.getParameter("credit").length() != 0)
		{
			transaction.setAmount_Credited(request.getParameter("credit"));	
		}
		
		if(request.getParameter("withdraw")!= null && request.getParameter("withdraw").length() != 0)
		{
			transaction.setAmount_Withdrawn(request.getParameter("withdraw"));	
		}
		transaction.setApproved_By(request.getParameter("username"));
			
			
		ServiceUserFacade serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		serviceUserFacade.adjustBalance(transaction);
		
		setReferenceInformation(id);
				
		this.userMessage="Account successfully adjusted";
		SubstanceRules.adjustSubstanceAccumaltor(this.serviceUserBean);
	
			
	}



	public void addNewNote() {
		
		NoteBean note = new NoteBean();
		note.setClient_Id(request.getParameter("serviceUserId").toString());
		note.setUserName(request.getParameter("username").toString());
		note.setNote(request.getParameter("note").toString());
		
		
		ServiceUserFacade serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		serviceUserFacade.addNewNote(note);
		
		
		
	}

	public void updateNote() {

		NoteBean note = new NoteBean();
		note.setClient_Id(request.getParameter("serviceUserId").toString());
		note.setUserName(request.getParameter("username").toString());
		note.setId(request.getParameter("noteId").toString());
		note.setNote(request.getParameter(""+note.getId()+"").toString());
		
		ServiceUserFacade serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		serviceUserFacade.addNewNote(note);
		
	}
	
	public void changeEligibility(ServiceUserBean serviceuser ,String active)
	{
				
		for(int i=0; i < serviceuser.getEligibilityBeans().size(); i++)
		{
			if(serviceuser.getEligibilityBeans().get(i).getId().toString().equals("1"))
			{
				if(serviceuser.getEligibilityBeans().get(i).getActive() == active)
				{
					break;
				}
				else
				{
					serviceuser.getEligibilityBeans().get(i).setActive(active);
//					ServiceUserFacade serviceUserFacade = (ServiceUserFacade) this.applicationContext.getBean("serviceUserFacade");
//					serviceUserFacade.changeEligibility(serviceuser);
					cmsQueryEligibility.changeEligibility(serviceuser);
				}
			}
		}
	}
	
}