package model.beans;

public class NoteBean {
	private String Id;
	private String Client_Id;
	private String UserName;
	private String Note;
	private String Created_By;
	private String Created_On;
	private String Updated_On;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getClient_Id() {
		return Client_Id;
	}
	public void setClient_Id(String client_Id) {
		Client_Id = client_Id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String getCreated_By() {
		return Created_By;
	}
	public void setCreated_By(String created_By) {
		Created_By = created_By;
	}
	public String getCreated_On() {
		return Created_On;
	}
	public void setCreated_On(String created_On) {
		Created_On = created_On;
	}
	public String getUpdated_On() {
		return Updated_On;
	}
	public void setUpdated_On(String updated_On) {
		Updated_On = updated_On;
	}

}
