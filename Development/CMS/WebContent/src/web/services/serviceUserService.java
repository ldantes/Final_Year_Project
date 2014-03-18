package web.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import model.data.cmsQueryDateToClean;
import model.data.cmsQueryEligibility;
import model.data.cmsQueryServiceUser;
import model.data.cmsQuerySubstance;
import model.data.cmsQueryUsers;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import Rules.EngagementRules;
import Rules.SubstanceRules;
import utilities.DataSourceManager;

public class serviceUserService{
	
	public String userMessage = "";
	public String firedrules = "";
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
		String id = this.request.getParameter("serviceUserId").toString();
		serviceUserBean.setId(id);
		serviceUserBean.setfirstname(this.request.getParameter("srvUserfname").toString());
		serviceUserBean.setSurname(this.request.getParameter("srvUsersname").toString());
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
		this.substanceBeans = cmsQuerySubstance.qrySubstances(null, "Y");
		this.attendanceDetails =cmsQueryAttendance.srvUserAttendance(id);
		this.serviceUserBean = cmsQueryServiceUser.searchServiceUsersById(id);
		this.userBeans = cmsQueryUsers.qryUsers("Y");
		this.eligibilityBeans = cmsQueryEligibility.srvUserEligibility(id);
				
		this.serviceUserBean.setSubstanceDetails(this.substanceBeans);
		this.serviceUserBean.setAttendanceDetails(this.attendanceDetails);
		this.serviceUserBean.setEligibilityBeans(this.eligibilityBeans);
		this.serviceUserBean.setSubAccums(cmsQuerySubstance.qrySubstanceAccum(id));
		this.serviceUserBean.setAccountDetails(cmsQueryAccount.srvUserAccount(id));
		this.serviceUserBean.setDateToClean(cmsQueryDateToClean.srvUserDateToClean(id));
		
	}
	
	public void newSubstanceResult()
	{
		String id = request.getParameter("srvUserid");
		setReferenceInformation(id);
		List<SubstanceAccumBean> subAccumList = cmsQuerySubstance.qrySubstanceAccum(id);
		int accum = 0;
		String substance= "";
		String testedSubstancce ="";
		int progressionSubstanceCount =0;
		int progressionSubstances =0;
		boolean setDTC=false; 
		float credit = 0.00f;
		
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
					if(this.substanceBeans.get(i).getStreamRegressionFlag().equals("Y"))
					{
						if(substanceBean.getResult().equals("F"))
						{
							accum = Integer.parseInt(this.substanceBeans.get(i).getResetValue());
							setDTC=true;
							
							
						}
						else
						{
							accum = subAccumList.get(j).getAccum()+1;
							if(accum > Integer.parseInt(this.substanceBeans.get(i).getMaxValue()))
							{
								accum = Integer.parseInt(this.substanceBeans.get(i).getMaxValue());
								progressionSubstanceCount++;
								
							}
							
						}
						progressionSubstances++;
					}
					else if(substanceBean.getResult().equals("P"))
					{
						accum = subAccumList.get(j).getAccum()+1;
						if(accum >= Integer.parseInt(this.substanceBeans.get(i).getMaxValue()))
						{
							accum = Integer.parseInt(this.substanceBeans.get(i).getMaxValue());
							
							
						}
						
					}
					
				}
				
			}
			
			credit = credit + (accum * this.serviceUserBean.getStreamDetails().getPointConversion());
			
			
			serviceUserFacade.newSubstanceResult(substanceBean, accum);
			accum=0;
			
		};
		
		applyCreditTransaction(this.serviceUserBean,credit);
		
		if(progressionSubstances == progressionSubstanceCount)
		{
			this.serviceUserBean.getStreamDetails().setSupportLevel(this.serviceUserBean.getStreamDetails().getSupportLevel()+1);
			changeStream(this.serviceUserBean);
			this.userMessage=this.userMessage+"<br/>Service User's stream has progressed";
		}
		
				
		if(setDTC)
		{
			if((this.serviceUserBean.getDateToClean().getOrderOfProgress() +1) < 4)
			{
				this.serviceUserBean.getDateToClean().setOrderOfProgress(this.serviceUserBean.getDateToClean().getOrderOfProgress() +1);
				
				this.userMessage=this.userMessage+"<br/>Service User assigned a new Date to be Clean.";
			}
						
		}
		else
		{
			this.serviceUserBean.getDateToClean().setOrderOfProgress(0);
			
		}
		this.serviceUserBean.getDateToClean().setExtensionApplied("N");
		this.serviceUserBean.getDateToClean().setSetBy(request.getParameter("administeredBy"));
		cmsQueryDateToClean.updateDTC(this.serviceUserBean);
		setReferenceInformation(id);
		
		String oldStreamId = serviceUserBean.getStreamDetails().getStreamId();
		SubstanceRules sRules = new SubstanceRules();
		sRules.adjustSubstanceAccumaltor(this.serviceUserBean);
		
		for (String s : sRules.getFiredRules())
		{
			this.firedrules=this.firedrules+"<br/>"+s;
			
		}
		
		NoteBean note = new NoteBean();
		note.setClient_Id(this.serviceUserBean.getId());
		note.setUserName("system");
		note.setNote(this.firedrules);
		
		
		serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		serviceUserFacade.addNewNote(note);
		
		setReferenceInformation(id);
		if (!oldStreamId.equals(this.serviceUserBean.getStreamDetails().getStreamId()))
		{
			this.userMessage=this.userMessage+"<br/>Service User's stream has changed, accumalators and DTC are reset";
		}
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
			
		setReferenceInformation(id);
		EngagementRules eRules = new EngagementRules();
		eRules.applyEngagmentRules(this.serviceUserBean, attendanceBean);
		
		for (String s : eRules.getFiredRules())
		{
			this.firedrules=this.firedrules+"<br/>"+s;
			
		}
		NoteBean note = new NoteBean();
		note.setClient_Id(this.serviceUserBean.getId());
		note.setUserName("system");
		note.setNote(this.firedrules);
		
		ServiceUserFacade serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		serviceUserFacade.newAttendance(attendanceBean);
		serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		serviceUserFacade.addNewNote(note);
		
		setReferenceInformation(id);
		
		this.userMessage=this.userMessage+"<br/>Attendance successfull added";
	
			
	}
	
	public void adjustBalance(TransactionBean transaction) 
	{
		float creditThisWeek =0;
		float newCredits =0;
		setReferenceInformation(transaction.getAccount_Id());
		creditThisWeek = cmsQueryAccount.queryWeekCredit(transaction.getAccount_Id());
		newCredits = transaction.getAmount_Credited().floatValue() * this.serviceUserBean.getStreamDetails().getPointConversion();
		transaction.setAmount_Credited(new BigDecimal(newCredits));
		if((creditThisWeek + transaction.getAmount_Credited().floatValue()) > this.serviceUserBean.getStreamDetails().getMaxPoints())
		{
			this.userMessage="Credit has reached weekly MAX <br/>";
			transaction.setAmount_Credited(new BigDecimal(this.serviceUserBean.getStreamDetails().getMaxPoints() - creditThisWeek));
			if(transaction.getAmount_Credited().floatValue()<=0)
			{
				transaction.setAmount_Credited(new BigDecimal(0));
			}
		}
		if(transaction.getAmount_Withdrawn()==null || transaction.getAmount_Withdrawn().floatValue()==0)
		{
			transaction.setAmount_Withdrawn(new BigDecimal(0));
		}
		cmsQueryAccount.adjustBalance(transaction);
		setReferenceInformation(transaction.getAccount_Id());		
		this.userMessage="Account successfully adjusted";
		SubstanceRules sRules = new SubstanceRules();
		sRules.adjustSubstanceAccumaltor(this.serviceUserBean);
		
	
			
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
			if(serviceuser.getEligibilityBeans().get(i).getId().toString().equals("1")||serviceuser.getEligibilityBeans().get(i).getId().toString().equals("2"))
			{
				if(serviceuser.getEligibilityBeans().get(i).getActive() == active)
				{
					break;
				}
				else
				{
					this.userMessage="Credit withdraw eligibility changed";
					serviceuser.getEligibilityBeans().get(i).setActive(active);
//					ServiceUserFacade serviceUserFacade = (ServiceUserFacade) this.applicationContext.getBean("serviceUserFacade");
//					serviceUserFacade.changeEligibility(serviceuser);
					cmsQueryEligibility.changeEligibility(serviceuser,1,0);
				}
				if(serviceuser.getStreamDetails().getStreamId().equals("2"))
				{
						this.userMessage="Outing/Visit priveldges changed";
						serviceuser.getEligibilityBeans().get(i).setActive(active);
//						ServiceUserFacade serviceUserFacade = (ServiceUserFacade) this.applicationContext.getBean("serviceUserFacade");
//						serviceUserFacade.changeEligibility(serviceuser);
						cmsQueryEligibility.changeEligibility(serviceuser,2,0);
					
				}
			}
			
			
		}
		
	}
	
	public void changeStream(ServiceUserBean serviceuser)
	{
		cmsQueryServiceUser.changeStream(serviceuser);
		
		serviceuser.getDateToClean().setOrderOfProgress(0);
		cmsQueryDateToClean.updateDTC(serviceuser);
		
	}
	
	public void applyCreditTransaction(ServiceUserBean serviceuser, float credit)
	{
		float creditThisWeek =0;
		
		creditThisWeek = cmsQueryAccount.queryWeekCredit(serviceuser.getId());
		if((creditThisWeek + credit) > this.serviceUserBean.getStreamDetails().getMaxPoints())
		{
			this.userMessage="Credit has reached weekly MAX <br/>";
			credit = this.serviceUserBean.getStreamDetails().getMaxPoints() - creditThisWeek;
			if(credit<=0)
			{
				credit =0;
			}
		}
		
		TransactionBean transaction = new TransactionBean();
		transaction.setAccount_Id(serviceuser.getId());
		transaction.setAmount_Credited(new BigDecimal(credit));
		transaction.setAmount_Withdrawn(new BigDecimal(0));
		transaction.setApproved_By(request.getParameter("administeredBy"));
		ServiceUserFacade serviceUserFacade = (ServiceUserFacade) applicationContext.getBean("serviceUserFacade");
		serviceUserFacade.adjustBalance(transaction);
		
		this.userMessage=this.userMessage+"Test results successfull added <br/> account credited :"+new BigDecimal(credit).setScale(2, RoundingMode.CEILING);
	}
	
}