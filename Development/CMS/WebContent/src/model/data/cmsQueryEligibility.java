package model.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




import model.beans.EligibilityBean;


import model.beans.ServiceUserBean;

import org.apache.log4j.Logger;

import utilities.DataSourceManager;

public class cmsQueryEligibility {
	
	private static Logger log = Logger.getLogger(cmsQueryUsers.class);
	
	public static List<EligibilityBean> srvUserEligibility(String id)
	{
		
		
		String 	funcExceptionErrorMsg 	= "srvUserEligibility. ";
		List<EligibilityBean> eligibilities =  new  ArrayList<EligibilityBean>();
		EligibilityBean  eligibility = null;
		Connection connection = null;		
		Statement stmt = null;		
			
			try{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();			
				String query = "SELECT a.Eligibility_Id, "
						+ " b.Eligibility_name,"
						+ " a.Active, a.Amount,"
						+ " b.min_value, b.max_value,"
						+ " b.Stream_Id, a.Created_By,"
						+ " a.Created_On FROM cm_client_eligibilities a,"
						+ " cm_eligibility b "
						+ " where a.Eligibility_Id = b.Eligibility_Id "
						+ " and b.active = 'Y'"
						+ " and a.client_id ="+id+"" ;

				

				ResultSet  results = stmt.executeQuery(query);
				while (results.next())
				{
					eligibility = new EligibilityBean();
					eligibility.setId(results.getString("Eligibility_Id"));
					eligibility.setName(results.getString("Eligibility_name"));
					eligibility.setActive(results.getString("Active"));
					eligibility.setAmount(results.getInt("Amount"));
					eligibility.setMinValue(results.getString("min_value"));
					eligibility.setMaxValue(results.getString("max_value"));
					eligibility.setStreamId(results.getString("Stream_Id"));
					eligibility.setCreatedBy(results.getString("Created_By"));
					eligibility.setCreatedOn(results.getString("Created_On"));
					eligibilities.add(eligibility);
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
		
		
		return eligibilities;
		
	}
	
	public static void changeEligibility(ServiceUserBean serviceuser, int eleigibility, int amount) 
	{
		String funcExceptionErrorMsg ="changeEligibility";
	    Connection connection = null;	
		Statement stmt =null;
		
		for(int i=0; i < serviceuser.getEligibilityBeans().size(); i++)
		{
			if(Integer.parseInt(serviceuser.getEligibilityBeans().get(i).getId()) == eleigibility)
			{
			try{
					connection = DataSourceManager.getDataSource().getConnection();
					stmt = connection.createStatement();	
					System.out.print("\n"+serviceuser.getEligibilityBeans().get(i).getActive()+" "+eleigibility+"\n");
					String query = "update cm_client_eligibilities set active ='"+serviceuser.getEligibilityBeans().get(i).getActive()+"', amount="+amount+" where client_id ="+serviceuser.getId()+" and eligibility_id ="+ eleigibility+ "";
					System.out.print(query+"\n\n\n");
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
		
		
	}

}
