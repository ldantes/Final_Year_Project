package model.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.beans.SubstanceAccumBean;
import model.beans.SubstanceBean;

import org.apache.log4j.Logger;

import utilities.DataSourceManager;

public class cmsQuerySubstance {

	private static Logger log = Logger.getLogger(cmsQuerySubstance.class);
	
	public static List<SubstanceBean> qrySubstances(String id, String active)
	{
			
		String 	funcExceptionErrorMsg 	= "searchServiceUsers. ";
		List<SubstanceBean> substances =  new  ArrayList<SubstanceBean>();
		SubstanceBean substance = null;
		Connection connection = null;		
		Statement stmt = null;		
			
			try{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();			
				String query = "SELECT Substance, "
						+ "Reset_value, "
						+ "Max_value, "
						+ " Stream_regression_substance,"
						+ " Active, "
						+ "Created_By, "
						+ "Created_On ,"
						+ "Updated_By, "
						+ "Updated_On "
						+ "FROM cm_substances where Active = 'Y'";
				
				if (active.equals("N"))
				{
					query = query+ "  or active ='N'";
				}
				
				if (id != null && id != "")
				{
					query = query+ " and substance ='"+id+"'";
				}
				
								
				ResultSet  results = stmt.executeQuery(query);
				
				while (results.next())
				{
					substance = new SubstanceBean();
					substance.setSubstance(results.getString("Substance"));
					substance.setResetValue(results.getString("Reset_value"));
					substance.setMaxValue(results.getString("Max_value"));
					substance.setActive(results.getString("Active"));
					substance.setStreamRegressionFlag(results.getString("Stream_regression_substance"));
					substance.setCreatedBy(results.getString("Created_By"));
					substance.setCreatedOn(results.getString("Created_On"));
					substance.setUpdatedBy(results.getString("Updated_By"));
					substance.setUpdatedOn(results.getString("Updated_On"));
					substances.add(substance);
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
			return substances;
	}
	
	
	public static List<SubstanceBean> qryServiceUserResults(String id)
	{
			
		String 	funcExceptionErrorMsg 	= "searchServiceUsers. ";
		List<SubstanceBean> substances =  new  ArrayList<SubstanceBean>();
		SubstanceBean substance = null;
		Connection connection = null;		
		Statement stmt = null;		
			
			try{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();			
				String query = "SELECT r.Id testId, r.Client_Id clientId, r.`Substance`,"
						+ " r.`Result`, r.`Administered_By`, r.`Administered_On`,"
						+ " r.`Created_By`, r.`Created_On`, s.`Reset_value`, s.`Max_value`,"
						+ " s.`Active` FROM cm_client_test_results r,"
						+ " cm_substances s where s.substance = r.substance"
						+ " and client_id = "+id
						+ " order by r.`Administered_On` desc , r.id desc";

				

				ResultSet  results = stmt.executeQuery(query);
				
				while (results.next())
				{
					substance = new SubstanceBean();
					substance.setTestId(results.getString("testId"));
					substance.setSrvUserId(results.getString("clientId"));
					substance.setResult(results.getString("Result"));
					substance.setSubstance(results.getString("Substance"));
					substance.setAdministeredBy(results.getString("Administered_By"));
					substance.setAdministeredOn(results.getString("Administered_On"));
					substance.setResetValue(results.getString("Reset_value"));
					substance.setMaxValue(results.getString("Max_value"));
					substance.setActive(results.getString("Active"));
					substance.setCreatedBy(results.getString("Created_By"));
					substance.setCreatedOn(results.getString("Created_On"));
					substances.add(substance);
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
			return substances;
	}
	

	public static List<SubstanceAccumBean> qrySubstanceAccum(String srchID )
	{
	
		String 	funcExceptionErrorMsg 	= "qrySubstanceAccum. ";
			
			
		List<SubstanceAccumBean> subAccums = new  ArrayList<SubstanceAccumBean>();
		SubstanceAccumBean subAccum =null;
		Connection connection = null;		
		Statement stmt = null;		
			
			try
			{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();	
				String query = "SELECT sa.substance sub, "
						+ "	   sa.accum, "
						+ "	   sa.Updated_On, "
						+ "		sa.updated_By "
						+ " FROM    cm_client_substance_accum sa, cm_substances s "
						+ " where    sa.client_id = "+srchID
						+ " and s.substance = sa.substance "
						+ " and s.active = 'Y'" ;
				
				ResultSet results = stmt.executeQuery(query);
				 while (results.next()) {
					 subAccum = new SubstanceAccumBean();
					 subAccum.setSubstance(results.getString("sub"));
					 subAccum.setAccum(results.getInt("accum"));
					 subAccum.setUpdatedBy(results.getString("updated_By"));
					 subAccum.setUpdatedOn(results.getString("updated_On"));
					 subAccums.add(subAccum);
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
				return subAccums;
			}
	}


	public static String updateSubstances(List<SubstanceBean> newValues) {
		String funcExceptionErrorMsg ="updateSubstances";
	    Connection connection = null;	
		Statement stmt =null;
		
		for(int i=0; i < newValues.size(); i++)
		{
			try{
					connection = DataSourceManager.getDataSource().getConnection();
					stmt = connection.createStatement();			
					String query = "update cm_substances set Reset_value = "+newValues.get(i).getResetValue()+" ,"
							+ "								 max_value = "+newValues.get(i).getMaxValue()+","
							+ "								 active ='"+newValues.get(i).getActive()+"',"
							+ "								 Stream_regression_substance ='"+newValues.get(i).getStreamRegressionFlag()+"',"
							+ "								 Updated_By ='"+newValues.get(i).getUpdatedBy()+"',"
							+ "								 updated_on = curdate()"
							+ "							 where substance = '"+newValues.get(i).getSubstance()+"'";
					System.out.println(query);
					stmt.executeUpdate(query);
					
				}
				catch (SQLException  e) {							
					log.error(funcExceptionErrorMsg, e);
					return e+" error occured";
								
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
		
		return  "Substance Updates Successfull";
		
	}


	public static String newSubstance(SubstanceBean newSub) {
		String funcExceptionErrorMsg ="updateSubstances";
	    Connection connection = null;	
		Statement stmt =null;
		
		try{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();			
				String query = "INSERT INTO cm_substances"
						+ "		VALUES(	'"+newSub.getSubstance()+"',"
						+ "				"+newSub.getResetValue()+","
						+ "				"+newSub.getMaxValue()+","
						+ "				'"+newSub.getStreamRegressionFlag()+"',"
						+ "				'"+newSub.getActive()+"',"
						+ "				'"+newSub.getUpdatedBy()+"',"
						+ "				curdate(),"
						+ "				'"+newSub.getUpdatedBy()+"',"
						+ "				curdate())";
				System.out.println(query);
				stmt.executeUpdate(query);
				
			}
			catch (SQLException  e) {							
				log.error(funcExceptionErrorMsg, e);
				return e+" error occured";
							
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
			
				
		
		return  " New Substance Added Successfully";
		
	
	}
}
