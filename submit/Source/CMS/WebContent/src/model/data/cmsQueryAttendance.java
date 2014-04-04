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

/**
Leslie Ducray - 2014
Class of methods for querying and executing statements on the service user's attendance
 */
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
						+ "	staff_profession,"
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
					attendance.setStaffProfession(results.getString("staff_profession"));
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
	
	public static int awardedMeetingsThisWeek(String id)
	{
		String 	funcExceptionErrorMsg 	= "awardedMeetingsThisWeek. ";
		Connection connection = null;		
		Statement stmt = null;	
		int rewardedAttendance = 0;
			
			try{
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();			
				String query = "select count(*) as count"+
						" from cm_client_attnd where Client_Id = "+id+" and Attended='Y' and participated ='Y' "+
							"	and TO_DAYS(time_date)> to_days(curdate())-7 ";
				

				ResultSet  results = stmt.executeQuery(query);
				while (results.next())
				{
					rewardedAttendance = results.getInt("count");
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
		return rewardedAttendance;
	}

}
