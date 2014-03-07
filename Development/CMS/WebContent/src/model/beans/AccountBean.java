package model.beans;

import java.math.BigDecimal;
import java.util.List;

public class AccountBean {

private String Account_Id; 
private BigDecimal  Account_Balance;
private String Active;
private String Updated_By;
private String Updated_On;
private String Created_By;
private String Created_On;
private List<TransactionBean> transactions;

public String getAccount_Id() {
	return Account_Id;
}
public void setAccount_Id(String account_Id) {
	Account_Id = account_Id;
}
public BigDecimal  getAccount_Balance() {
	return Account_Balance;
}
public void setAccount_Balance(BigDecimal  account_Balance) {
	Account_Balance = account_Balance;
}
public String getActive() {
	return Active;
}
public void setActive(String active) {
	Active = active;
}
public String getUpdated_By() {
	return Updated_By;
}
public void setUpdated_By(String updated_By) {
	Updated_By = updated_By;
}
public String getUpdated_On() {
	return Updated_On;
}
public void setUpdated_On(String updated_On) {
	Updated_On = updated_On;
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
public List<TransactionBean> getTransactions() {
	return transactions;
}
public void setTransactions(List<TransactionBean> transactions) {
	this.transactions = transactions;
}
}
