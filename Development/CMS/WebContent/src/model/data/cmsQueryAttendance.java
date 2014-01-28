package model.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import utilities.DataSourceManager;
import model.beans.AttendanceBean;


public class cmsQueryAttendance {
	
	private static Logger log = Logger.getLogger(cmsQueryUsers.class);
	
	public static List<AttendanceBean> srvUserAttendance(String id)
	{
		
		
		String 	funcExceptionErrorMsg 	= "cmsQueryAttendance. ";
		List<AttendanceBean> attendanceDetails =  new  ArrayList<AttendanceBean>();
		AttendanceBean attendance = null;
		Connection connection = null;		
		Statement stmt = null;		
			
			try{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();			
				String query = "SELECT `Id`, "
						+ "`Client_Id`, "
						+ "`UserName`, "
						+ "`Time_date`, "
						+ "`Attended`, "
						+ " Valid_Reason ,"
						+ " participated, "
						+ "`Attnd_Failed_Reason`, "
						+ "`Treatment_review_meeting` "
						+ "FROM cm_client_attnd"
						+ " where "
						+ " client_id = "+id
						+ " order by `Time_date` desc ";

				

				ResultSet  results = stmt.executeQuery(query);
				
				while (results.next())
				{
					attendance = new AttendanceBean();
					attendance.setId(results.getString("Id"));
					attendance.setSrvUserId(results.getString("Client_Id"));
					attendance.setUsername(results.getString("UserName"));
					attendance.setTimeDate(results.getString("Time_date").toString());
					attendance.setAttended(results.getString("Attended"));
					attendance.setParticipation(results.getString("participated"));
					attendance.setValidReason(results.getString("Valid_Reason"));
					attendance.setAttndFailedReason(results.getString("Attnd_Failed_Reason"));
					attendance.setTreatmentReviewMeeting(results.getString("Treatment_review_meeting"));
					attendanceDetails.add(attendance);
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
		
		
		return attendanceDetails;
		
	}

}
