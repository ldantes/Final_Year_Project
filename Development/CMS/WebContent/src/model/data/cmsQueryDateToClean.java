package model.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.beans.DateToCleanBean;


import model.beans.ServiceUserBean;

import org.apache.log4j.Logger;

import utilities.DataSourceManager;

public class cmsQueryDateToClean {

private static Logger log = Logger.getLogger(cmsQueryUsers.class);
	
	public static DateToCleanBean srvUserDateToClean(String id)
	{
		
		
		String 	funcExceptionErrorMsg 	= "srvUserDateToClean. ";
		DateToCleanBean dtc = new DateToCleanBean();
		
		Connection connection = null;		
		Statement stmt = null;		
			
			try{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();			
				String query = "select a.Days_Extension, a.Stream_Id, a.Order_of_Progress, a.Active, a.No_Days, a.Card, b.Extension_applied, b.Date_to_Clean, b.Set_By, b.Set_On"
						+ "	from cm_client_date_to_clean b, cm_date_to_clean a"
						+ " where a.id = b.card"
						+ " and b.Client_Id ="+id;
				

				ResultSet  results = stmt.executeQuery(query);
				while (results.next())
				{
					dtc.setCard(results.getString("card"));
					dtc.setDateToClean(results.getString("Date_to_Clean"));
					dtc.setDaysExtension(results.getString("Days_Extension"));
					dtc.setExtensionApplied(results.getString("Extension_applied"));
					dtc.setActive(results.getString("Active"));
					dtc.setStreamId(results.getString("Stream_Id"));
					dtc.setOrderOfProgress(results.getInt("Order_of_Progress"));
					dtc.setNumDays(results.getString("No_Days"));
					dtc.setSetBy(results.getString("Set_By"));
					dtc.setSetOn(results.getString("Set_On"));
					
				}
				
								
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
		
		return dtc;
	}
	
	public static void updateDTC(ServiceUserBean serviceUserBean)
	{
		String funcExceptionErrorMsg ="updateDTC";
	    Connection connection = null;	
		Statement stmt =null;
		
		
		try{
					connection = DataSourceManager.getDataSource().getConnection();
					stmt = connection.createStatement();			
					
					
					String query = "update cm_client_date_to_clean"
							+ " set card = (select id from cm_date_to_clean where Order_of_Progress ="+serviceUserBean.getDateToClean().getOrderOfProgress()+"),"
									+ " set_on = curdate(),"
									
									+ " set_by='"+serviceUserBean.getDateToClean().getSetBy()+"',";
					if(serviceUserBean.getDateToClean().getExtensionApplied().equals("Y"))
					{
						query = query+ " Extension_applied ='"+serviceUserBean.getDateToClean().getExtensionApplied()+"',"
								+ "		 Date_to_Clean= DATE_ADD('"+serviceUserBean.getDateToClean().getDateToClean()+"',INTERVAL "+serviceUserBean.getDateToClean().getDaysExtension()+" DAY) ";
					}
					else
					{
						query = query+  " Extension_applied ='"+serviceUserBean.getDateToClean().getExtensionApplied()+"',"
								+ " Date_to_Clean= DATE_ADD(curdate(),INTERVAL "+serviceUserBean.getDateToClean().getNumDays()+" DAY) ";
					}
					query = query+ " where client_id ="+serviceUserBean.getId()+"";
					System.out.print("QUERY"+query);
					stmt.executeUpdate(query);
					
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
			
				
		
		
		
	}
}
