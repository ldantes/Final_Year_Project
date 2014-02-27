package model.beans;

import model.beans.UserRoleBean;

import java.util.List;

public class UserBean {
	private String id="";
	private String userName="";
	private String firstName="";
	private String surname="";
	private String active="";
	private String profession="";	
	private String password="";	
	private String email="";
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;
	private List<UserRoleBean> userRoles;
	
	
	public String getId(){
		return id;
	}	
	public void setId(String id){		
		this.id=id;		
	}
	
	public String getUserName(){
		return userName;
	}	
	public void setUserName(String userName){		
		this.userName=userName;		
	}
	
	public String getFirstName(){
		return firstName;
	}	
	public void setFirstName(String firstName){		
		this.firstName=firstName;		
	}
	
	public String getSurname(){
		return surname;
	}	
	public void setSurname(String surname){		
		this.surname=surname;		
	}
	
	public String getActive(){
		return active;
	}	
	public void setActive(String active){
		this.active=active;		
	}
		
	public String getPassword(){
		return password;
	}	
	public void setPassword(String password){
		this.password=password;	
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<UserRoleBean> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRoleBean> userRoles) {
		this.userRoles = userRoles;
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
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	

}
