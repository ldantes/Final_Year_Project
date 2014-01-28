package model.beans;

public class StreamBean {

	private String streamId="";
	private String streamName="";
	private String supportLevel="";
	private int maxPoints=0;
	private int substanceIncrementor=0;
	private int engagementIncrementor=0;
	private String createdBy="";
	private String createdOn="";
	private String updateBy="";
	private String updatedOn="";
	
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getSupportLevel() {
		return supportLevel;
	}
	public void setSupportLevel(String supportLevel) {
		this.supportLevel = supportLevel;
	}
	public int getMaxPoints() {
		return maxPoints;
	}
	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}
	public int getSubstanceIncrementor() {
		return substanceIncrementor;
	}
	public void setSubstanceIncrementor(int substanceIncrementor) {
		this.substanceIncrementor = substanceIncrementor;
	}
	public int getEngagementIncrementor() {
		return engagementIncrementor;
	}
	public void setEngagementIncrementor(int engagementIncrementor) {
		this.engagementIncrementor = engagementIncrementor;
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
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
}
