package model.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.beans.StreamBean;




import org.apache.log4j.Logger;

import utilities.DataSourceManager;

public class cmsStreamQuery {
	/**
	Leslie Ducray - 2014
	Class of methods for querying and executing statements on the service user's stream information
	 */	
private static Logger log = Logger.getLogger(cmsQuerySubstance.class);
	
	public static List<StreamBean> qryStreams()
	{
		String 	funcExceptionErrorMsg 	= "qryStreams. ";
		List<StreamBean> streams =  new  ArrayList<StreamBean>();
		StreamBean stream = null;
		Connection connection = null;		
		Statement stmt = null;		
			
			try{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();			
				String query = "select Stream_Id,"
						+ " Stream_Name,"
						+ " Support_Level,"
						+ " Weekly_Max_Points,"
						+ " Points_Conversion,"
						+ " Created_By,"
						+ " Created_On"
						+ " from cm_streams";				
				ResultSet  results = stmt.executeQuery(query);
				
				while (results.next())
				{
					stream = new StreamBean();
					stream.setCreatedBy(results.getString("Created_By"));
					stream.setCreatedOn(results.getString("Created_On"));
					stream.setMaxPoints(results.getInt("Weekly_Max_Points"));
					stream.setPointConversion(results.getFloat("Points_Conversion"));
					stream.setStreamId(results.getString("Stream_Id"));
					stream.setStreamName(results.getString("Stream_Name"));
					stream.setSupportLevel(results.getInt("Support_Level"));
					streams.add(stream);
					
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
			return streams;
	}

	public static String updateStreams(List<StreamBean> newVals) {
		String funcExceptionErrorMsg ="updateStreams";
	    Connection connection = null;	
		Statement stmt =null;
		
		for(int i=0; i < newVals.size(); i++)
		{
			try{
					connection = DataSourceManager.getDataSource().getConnection();
					stmt = connection.createStatement();			
					String query = "update cm_streams set Weekly_Max_Points = "+newVals.get(i).getMaxPoints()+" ,"
							+ "					  Points_Conversion = "+newVals.get(i).getPointConversion()+""
							+ "				  where Stream_Id = '"+newVals.get(i).getStreamId()+"'";
					System.out.println(query);
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
		return  "Stream Updates Successful";	
	}
	

}
