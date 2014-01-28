package model.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.beans.SubstanceBean;

import org.apache.log4j.Logger;

import utilities.DataSourceManager;

public class cmsQuerySubstance {

	private static Logger log = Logger.getLogger(cmsQuerySubstance.class);
	
	public static List<SubstanceBean> qrySubstances(String id)
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
						+ " Active, "
						+ "Created_By, "
						+ "Created_On "
						+ "FROM cm_substances where Active = 'Y'";
				
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
}
