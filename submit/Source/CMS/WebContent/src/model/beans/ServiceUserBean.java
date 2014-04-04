package model.beans;

import java.util.List;
/**
Leslie Ducray - 2014
ServiceUserBean class for storing the complete service user's details
maps associations to service user's stream, account details,
 attendance history, notes, substance accumulations,
  eligibilities, target date to test negative,
   substance test result history

 */

public class ServiceUserBean{
    
    private String id="";
	private String firstname="";
	private String surname="";
	private String DoB="";
	private String gender ="";
	private String contactNumber="";
	private String address="";
	private String pps="";
	private String ethnicity="";
	private String nationality="";
	private String occupation="";
	private String familyInformation="";
	private String createdBy="";
	private String createdOn="";
	private String updatedOn="";
	private String updatedBy="";
	private StreamBean streamDetails;
	private AccountBean accountDetails;
	private List<AttendanceBean> attendanceDetails;
	private List<NoteBean> notes;
	private List<SubstanceAccumBean> subAccums;
	private List<EligibilityBean> eligibilityBeans;
	private DateToCleanBean dateToClean;
	private List<SubstanceBean> substanceDetails;
	
	
	public StreamBean getStreamDetails(){
		return streamDetails;
	}
	
	public void setStreamDetails(StreamBean streamDetails)
	{
		this.streamDetails = streamDetails;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getfirstname() {
		return firstname;
	}
	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getDoB() {
		return DoB;
	}
	public void setDoB(String doB) {
		this.DoB = doB;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getFamilyInformation() {
		return familyInformation;
	}
	public void setFamilyInformation(String familyInformation) {
		this.familyInformation = familyInformation;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<AttendanceBean> getAttendanceDetails() {
		return attendanceDetails;
	}

	public void setAttendanceDetails(List<AttendanceBean> attendanceDetails) {
		this.attendanceDetails = attendanceDetails;
	}

	public String getPps() {
		return pps;
	}

	public void setPps(String pps) {
		this.pps = pps;
	}

	public List<NoteBean> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteBean> notes) {
		this.notes = notes;
	}

	public List<SubstanceAccumBean> getSubAccums() {
		return subAccums;
	}

	public void setSubAccums(List<SubstanceAccumBean> subAccums) {
		this.subAccums = subAccums;
	}

	public List<EligibilityBean> getEligibilityBeans() {
		return eligibilityBeans;
	}

	public void setEligibilityBeans(List<EligibilityBean> eligibilityBeans) {
		this.eligibilityBeans = eligibilityBeans;
	}

	public AccountBean getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountBean accountDetails) {
		this.accountDetails = accountDetails;
	}

	
	public DateToCleanBean getDateToClean() {
		return dateToClean;
	}

	public void setDateToClean(DateToCleanBean dateToClean) {
		this.dateToClean = dateToClean;
	}

	public List<SubstanceBean> getSubstanceDetails() {
		return substanceDetails;
	}

	public void setSubstanceDetails(List<SubstanceBean> substanceDetails) {
		this.substanceDetails = substanceDetails;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	


	

	
	
}