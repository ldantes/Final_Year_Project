package model.beans;

public class UserBean {
	private String userName="";
	private String firstName="";
	private String surname="";
	private String active="";
	private String password="";	
	private String email="";
	
	
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
	
	

}