package model.beans;
/**
Leslie Ducray - 2014
SubstanceAccumBean class stores the association between and service user and a substance, most importantly, the current accumulator (of negative/passed tests consecutively)
for that substance
 */


public class SubstanceAccumBean {
	
	private String srvUserId;
	private String substance;
	private int accum;
	private String updatedBy;
	private String updatedOn;
	
	public String getSubstance() {
		return substance;
	}
	public void setSubstance(String substance) {
		this.substance = substance;
	}
	
	public String getSrvUserId() {
		return srvUserId;
	}
	public void setSrvUserId(String srvUserId) {
		this.srvUserId = srvUserId;
	}
	public int getAccum() {
		return accum;
	}
	public void setAccum(int accum) {
		this.accum = accum;
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
