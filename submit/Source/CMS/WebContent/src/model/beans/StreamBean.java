package model.beans;
/**
Leslie Ducray - 2014
StreamBean class for storing a service user's stream, or stream details for system administration

 */

public class StreamBean {

	private String streamId="";
	private String streamName="";
	private int supportLevel;
	private int maxPoints=0;
	private float pointConversion =0;
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
	public int getSupportLevel() {
		return supportLevel;
	}
	public void setSupportLevel(int supportLevel) {
		this.supportLevel = supportLevel;
	}
	public int getMaxPoints() {
		return maxPoints;
	}
	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
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
	public float getPointConversion() {
		return pointConversion;
	}
	public void setPointConversion(float pointConversion) {
		this.pointConversion = pointConversion;
	}

}
