package model.beans;

import java.util.List;


public class ServiceUserBean{
    
    private String id="";
	private String name="";
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
	private List<AttendanceBean> attendanceDetails;
	private List<SubstanceBean> substanceDetails;
	private List<NoteBean> notes;
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public List<SubstanceBean> getSubstanceDetails() {
		return substanceDetails;
	}

	public void setSubstanceDetails(List<SubstanceBean> substanceDetails) {
		this.substanceDetails = substanceDetails;
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

	
	
}