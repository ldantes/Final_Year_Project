package model.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import utilities.DataSourceManager;
import model.beans.AccountBean;
import model.beans.TransactionBean;



public class cmsQueryAccount {
	
	private static Logger log = Logger.getLogger(cmsQueryUsers.class);
	
	public static AccountBean srvUserAccount(String id)
	{
		
		
		String 	funcExceptionErrorMsg 	= "cmsQueryAccount. ";
		AccountBean account = new AccountBean();
		List<TransactionBean> transactions =  new  ArrayList<TransactionBean>();
		TransactionBean  transaction = null;
		Connection connection = null;		
		Statement stmt = null;		
			
			try{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();			
				String query = "SELECT Account_Id, 	Account_Balance,    	Active,    	Updated_By,    	Updated_On,    	Created_By,    	Created_On	FROM cm_accounts   where Account_Id ="+id+"" ;

				

				ResultSet  results = stmt.executeQuery(query);
				while (results.next())
				{
					account.setAccount_Id(results.getString("Account_Id"));
					account.setAccount_Balance(results.getBigDecimal("Account_Balance"));
					account.setActive(results.getString("Active"));
					account.setUpdated_By(results.getString("Updated_By"));
					account.setUpdated_On(results.getString("Updated_On"));
					account.setCreated_By(results.getString("Created_By"));
					account.setCreated_On(results.getString("Created_On"));
				}
				
				query = "SELECT a.`Account_Id`,"
						
						+ "    	t.`Id`, "
						+ " 	t.`Amount_Withdrawn`,"
						+ " 	t.`Amount_Credited`,"
						+ " 	t.`Approved_By`,"
						+ " 	t.`Date_of_Transaction`"
						+ "	FROM cm_accounts a, cm_transactions t "
						+ "	where a.account_id = t.account_id "
						+ " and a.account_id ="+id ;

				

				results = stmt.executeQuery(query);
				
				while (results.next())
				{
					transaction = new TransactionBean();
					transaction.setId(results.getString("Id"));
					transaction.setAmount_Withdrawn(results.getBigDecimal("Amount_Withdrawn"));
					transaction.setAmount_Credited(results.getBigDecimal("Amount_Credited"));
					transaction.setApproved_By(results.getString("Approved_By"));
					transaction.setDate_of_Transaction(results.getString("Date_of_Transaction"));
					
					transactions.add(transaction);
				}
				account.setTransactions(transactions);	
				
			}
			catch (SQLException  e) {							
				log.error(funcExceptionErrorMsg, e);
							
			} finally {
				try {
					if(stmt != null){
						stmt.close();
					}
				} catch (SQLException sqle) {
					log.error(funcExceptionErrorMsg, sqle);
					
				}
				try {
					if(connection != null){
						connection.close();
					}
				} catch (SQLException sqle) {
					log.error(funcExceptionErrorMsg, sqle);
					
				}
			}
		
		
		return account;
		
	}
	
	public static float queryWeekCredit(String id)
	{
		String 	funcExceptionErrorMsg 	= "queryWeekCredit. ";
		Connection connection = null;		
		Statement stmt = null;	
		float creditsEarnedThisWeek = 0;
			
			try{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();			
				String query = "select sum(amount_credited) as sum"
						+ " from cm_transactions where Account_Id = "+id+""
								+ " and TO_DAYS(Date_of_Transaction)> to_days(curdate())-7 ";
				

				ResultSet  results = stmt.executeQuery(query);
				while (results.next())
				{
					creditsEarnedThisWeek = results.getFloat("sum");
				}
				
				results.close();
			}
			catch (SQLException  e) {							
				log.error(funcExceptionErrorMsg, e);
							
			} finally {
				try {
					if(stmt != null){
						stmt.close();
					}
				} catch (SQLException sqle) {
					log.error(funcExceptionErrorMsg, sqle);
					
				}
				try {
					if(connection != null){
						connection.close();
					}
				} catch (SQLException sqle) {
					log.error(funcExceptionErrorMsg, sqle);
					
				}
			}
		return creditsEarnedThisWeek;
	}
	
	public static void adjustBalance(TransactionBean transaction)
	{
		String fuctionName ="adjustBalance";
	    Connection connection = null;	
		CallableStatement stmt =null;
		
		
		try {
					
					
				connection = DataSourceManager.getDataSource().getConnection();		
				stmt = connection.prepareCall("{call cm_system.adjust_balance(?,?,?,?,?)}");			
				stmt.setString(1, transaction.getAccount_Id());		
				stmt.setBigDecimal(2, transaction.getAmount_Credited());	
				stmt.setBigDecimal(3, transaction.getAmount_Withdrawn());	
				stmt.setString(4, transaction.getApproved_By());	
				stmt.registerOutParameter(5,java.sql.Types.VARCHAR);			
				stmt.executeQuery();			
						
				if (stmt.getString(5).equals("OK")== false){
									
				}
			}
		catch (SQLException  e) {							
			log.error(fuctionName, e);
						
		} finally {
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
		}
		
	}

}
