package model.data;

import java.text.DateFormat;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;





import org.apache.log4j.Logger;

import utilities.DataSourceManager;
import model.beans.ServiceUserBean;
import model.beans.StreamBean;


public class cmsQueryServiceUser{
	
	private static Logger log = Logger.getLogger(cmsQueryServiceUser.class);
	public static List<ServiceUserBean> searchServiceUsersByName(String srchName){
			
		String 	funcExceptionErrorMsg 	= "searchServiceUsers. ";
			List<ServiceUserBean> serviceUsers =  new  ArrayList<ServiceUserBean>();
			ServiceUserBean serviceUser = null;
			Connection connection = null;		
			Statement stmt = null;		
			
			try{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();			
				String query = "SELECT    client_id,"
						+ "   Client_Name,"
						+ "    Client_DOB,"
						+ "    Client_Gender,"
						+ "    Client_Contact_No,"
						+ "    Client_Address,"
						+ "    Client_Nationality,"
						+ "    Client_Ethnicity,"
						+ "    Client_Occupation,"
						+ "    Client_Family_Info,"
						+ "    Created_By,"
						+ "    Created_On,"
						+ "    Updated_By,"
						+ "    Updated_On "
						+ " FROM    cm_clients "
						+ " where   UPPER(client_name) like UPPER('%"+srchName+"%') ";
				ResultSet  results = stmt.executeQuery(query);
				
				while (results.next()) {
					serviceUser = new ServiceUserBean();
					serviceUser.setId(results.getString("client_id"));
					serviceUser.setName(results.getString("Client_Name"));
					serviceUser.setDoB(String.valueOf(results.getString("Client_DOB")));
					serviceUser.setGender(results.getString("Client_Gender"));
					serviceUser.setContactNumber(results.getString("Client_Contact_No"));
					serviceUser.setAddress(results.getString("Client_Address"));
					serviceUser.setEthnicity(results.getString("Client_Ethnicity"));
					serviceUser.setNationality(results.getString("Client_Nationality"));
					serviceUser.setOccupation(results.getString("Client_Occupation"));
					serviceUser.setFamilyInformation(results.getString("Client_Family_Info"));
					serviceUser.setCreatedBy(results.getString("Created_By"));
					serviceUser.setCreatedOn(String.valueOf(results.getDate("Created_On")));
					serviceUser.setUpdatedBy(results.getString("Updated_By"));
					serviceUser.setUpdatedOn(String.valueOf(results.getDate("Updated_On")));
			
					
					serviceUsers.add(serviceUser);				
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
			
			
			return serviceUsers;
			
			
		}
	
		public static ServiceUserBean searchServiceUsersById(String srchID )
		{
		
			String 	funcExceptionErrorMsg 	= "searchServiceUsers. ";
				
				ServiceUserBean serviceUser = null;
				StreamBean streamBean = null;
				Connection connection = null;		
				Statement stmt = null;		
				
				try{
					connection = DataSourceManager.getDataSource().getConnection();
					stmt = connection.createStatement();			
					String query = "SELECT   c.client_id,"
							+ "    c.Client_Name,"
							+ "    c.Client_DOB,"
							+ "    c.Client_Gender,"
							+ "    c.Client_Contact_No,"
							+ "    c.Client_Address,"
							+ "    c.Client_Nationality,"
							+ "    c.Client_Ethnicity,"
							+ "    c.Client_Occupation,"
							+ "    c.Client_Family_Info,"
							+ "    c.Created_By client_createdBy,"
							+ "    c.Created_On client_createdOn,"
							+ "    c.Updated_By client_updatedBy,"
							+ "    c.Updated_On client_updatedOn,"
							+ "    cs.substance_Incrementor,"
							+ "    cs.Engagemnet_Incrementor,"
							+ "    cs.Created_By stream_createdBy,"
							+ "    cs.Created_On stream_createdOn,"
							+ "    cs.Updated_By stream_updatedBy,"
							+ "    cs.Updated_On stream_updatedOn,"
							+ "    s.stream_name ,"
							+ "    s.stream_id ,"
							+ "    s.support_level,"
							+ "    s.weekly_max_points "
							+ " FROM    cm_clients c,"
							+ "    cm_client_stream cs,"
							+ "    cm_streams s "
							+ " where    c.client_id = "+srchID
							+ "        and c.client_id = cs.Client_Id"
							+ "        and s.stream_id = cs.Stream_Id " ;
					
					ResultSet  results = stmt.executeQuery(query);
					
					while (results.next()) {
						serviceUser = new ServiceUserBean();
						streamBean = new StreamBean();
						serviceUser.setId(results.getString("client_id"));
						serviceUser.setName(results.getString("Client_Name"));
						serviceUser.setDoB(String.valueOf(results.getString("Client_DOB")));
						serviceUser.setGender(results.getString("Client_Gender"));
						serviceUser.setContactNumber(results.getString("Client_Contact_No"));
						serviceUser.setAddress(results.getString("Client_Address"));
						serviceUser.setEthnicity(results.getString("Client_Ethnicity"));
						serviceUser.setNationality(results.getString("Client_Nationality"));
						serviceUser.setOccupation(results.getString("Client_Occupation"));
						serviceUser.setFamilyInformation(results.getString("Client_Family_Info"));
						serviceUser.setCreatedBy(results.getString("client_createdBy"));
						serviceUser.setCreatedOn(String.valueOf(results.getDate("client_createdOn")));
						serviceUser.setUpdatedBy(results.getString("client_updatedBy"));
						serviceUser.setUpdatedOn(String.valueOf(results.getDate("client_updatedOn")));
						streamBean.setStreamId(results.getString("stream_id"));
						streamBean.setStreamName(results.getString("stream_name"));
						streamBean.setSupportLevel(results.getString("support_level"));
						streamBean.setEngagementIncrementor(results.getString("Engagemnet_Incrementor"));
						streamBean.setSubstanceIncrementor(results.getString("substance_Incrementor"));
						streamBean.setMaxPoints(results.getString("weekly_max_points"));
						streamBean.setCreatedBy(results.getString("stream_createdBy"));
						streamBean.setCreatedOn(String.valueOf(results.getDate("stream_createdOn")));
						streamBean.setUpdateBy(results.getString("stream_updatedBy"));
						streamBean.setUpdatedOn(String.valueOf(results.getDate("stream_updatedOn")));
						serviceUser.setStreamDetails(streamBean);
					
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
				
				
			return serviceUser;
			
			
		}
}