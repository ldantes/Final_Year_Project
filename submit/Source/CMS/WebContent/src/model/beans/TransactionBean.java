package model.beans;
/**
Leslie Ducray - 2014
transactionBean class holds the information on a transaction.
 */
import java.math.BigDecimal;

public class TransactionBean {


private String Id;
private String Account_Id;
private BigDecimal Amount_Withdrawn;
private BigDecimal Amount_Credited;
private String Approved_By;
private String Date_of_Transaction;

public String getId() {
	return Id;
}
public void setId(String id) {
	Id = id;
}
public String getAccount_Id() {
	return Account_Id;
}
public void setAccount_Id(String account_Id) {
	Account_Id = account_Id;
}
public BigDecimal getAmount_Withdrawn() {
	return Amount_Withdrawn;
}
public void setAmount_Withdrawn(BigDecimal amount_Withdrawn) {
	Amount_Withdrawn = amount_Withdrawn;
}
public BigDecimal getAmount_Credited() {
	return Amount_Credited;
}
public void setAmount_Credited(BigDecimal amount_Credited) {
	Amount_Credited = amount_Credited;
}
public String getApproved_By() {
	return Approved_By;
}
public void setApproved_By(String approved_By) {
	Approved_By = approved_By;
}
public String getDate_of_Transaction() {
	return Date_of_Transaction;
}
public void setDate_of_Transaction(String date_of_Transaction) {
	Date_of_Transaction = date_of_Transaction;
}

}