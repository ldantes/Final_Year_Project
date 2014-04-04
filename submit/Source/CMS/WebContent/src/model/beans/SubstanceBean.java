package model.beans;

/**
Leslie Ducray - 2014
SubstanceAccumBean class stores the association between and service user and a substance, most importantly, the current accumulator (of negative/passed tests consecutively)
for that substance
 */

public class SubstanceBean {
	
	private String srvUserId ="";
	private String testId ="";
	private String substance ="";
	private String resetValue = "0";
	private String maxValue = "0";
	private String active = "0";
	private String streamRegressionFlag = "0";
	private String result = "0";
	private String administeredBy = "0";
	private String administeredOn = "0";
	private String createdBy = "0";
	private String createdOn = "0";
	private String updatedBy = "0";
	private String updatedOn = "0";
	
	public String getSubstance() {
		return substance;
	}
	public void setSubstance(String substance) {
		this.substance = substance;
	}
	public String getResetValue() {
		return resetValue;
	}
	public void setResetValue(String resetValue) {
		this.resetValue = resetValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAdministeredBy() {
		return administeredBy;
	}
	public void setAdministeredBy(String administeredBy) {
		this.administeredBy = administeredBy;
	}
	public String getAdministeredOn() {
		return administeredOn;
	}
	public void setAdministeredOn(String administeredOn) {
		this.administeredOn = administeredOn;
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
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getSrvUserId() {
		return srvUserId;
	}
	public void setSrvUserId(String srvUserId) {
		this.srvUserId = srvUserId;
	}
	public String getStreamRegressionFlag() {
		return streamRegressionFlag;
	}
	public void setStreamRegressionFlag(String streamRegressionFlag) {
		this.streamRegressionFlag = streamRegressionFlag;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	

}
