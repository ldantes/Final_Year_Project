package model.beans;

public class UserRoleBean {
	private String roleName="";
	private String userName="";
	private String createdBy="";	
	private String createdOn="";
	
	
	public String getUserName(){
		return userName;
	}	
	public void setUserName(String userName){		
		this.userName=userName;		
	}
	
	public String getRoleName(){
		return roleName;
	}	
	public void setRoleName(String roleName){		
		this.roleName=roleName;		
	}
	
	public String getCreatedBy(){
		return createdBy;
	}	
	public void setCreatedBy(String createdBy){		
		this.createdBy=createdBy;		
	}
	
	public String getCreatedOn(){
		return createdOn;
	}	
	public void setCreatedOn(String createdOn){		
		this.createdOn=createdOn;		
	}
	
	

}
