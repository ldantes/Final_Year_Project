package model.beans;

public class AttendanceBean {
	
	private String id = "";
	private String srvUserId = "";
	private String username = "";
	private String timeDate = "";
	private String attended = "";
	private String participation = "";
	private String validReason = "";
	private String attndFailedReason = "";
	private String treatmentReviewMeeting = "";
	private String staffProfession = "";
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSrvUserId() {
		return srvUserId;
	}
	public void setSrvUserId(String srvUserId) {
		this.srvUserId = srvUserId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTimeDate() {
		return timeDate;
	}
	public void setTimeDate(String timeDate) {
		this.timeDate = timeDate;
	}
	public String getAttended() {
		return attended;
	}
	public void setAttended(String attended) {
		this.attended = attended;
	}
	public String getAttndFailedReason() {
		return attndFailedReason;
	}
	public void setAttndFailedReason(String attndFailedReason) {
		this.attndFailedReason = attndFailedReason;
	}
	public String getTreatmentReviewMeeting() {
		return treatmentReviewMeeting;
	}
	public void setTreatmentReviewMeeting(String treatmentReviewMeeting) {
		this.treatmentReviewMeeting = treatmentReviewMeeting;
	}
	public String getValidReason() {
		return validReason;
	}
	public void setValidReason(String validReason) {
		this.validReason = validReason;
	}
	public String getParticipation() {
		return participation;
	}
	public void setParticipation(String participation) {
		this.participation = participation;
	}
	public String getStaffProfession() {
		return staffProfession;
	}
	public void setStaffProfession(String staffProfession) {
		this.staffProfession = staffProfession;
	}

}
