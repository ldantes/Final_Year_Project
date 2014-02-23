package model.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import utilities.DataSourceManager;
import model.beans.UserBean;
import model.beans.UserRoleBean;

public class cmsQueryUsers  {

	private static Logger log = Logger.getLogger(cmsQueryUsers.class);
	
	public static List<UserRoleBean> qryUserRoles(UserBean userBean)
	{
		String 	funcExceptionErrorMsg 	= "qryUserRoles. ";
		
		List <UserRoleBean> userRoleBeans = new  ArrayList<UserRoleBean>();
		UserRoleBean userRoleBean = null;
		Connection connection = null;		
		Statement stmt = null;		
		
		try{
			connection = DataSourceManager.getDataSource().getConnection();
			stmt = connection.createStatement();			
			String query = "select role_name  , username , created_by, created_on from cm_user_roles where username = '"+ userBean.getUserName()+"'";
			ResultSet  results = stmt.executeQuery(query);
			
			while (results.next()) {
				userRoleBean = new UserRoleBean();
				userRoleBean.setRoleName(results.getString("role_name"));
				userRoleBean.setUserName(results.getString("username"));
				userRoleBean.setCreatedBy(results.getString("created_by"));
				userRoleBean.setCreatedOn(results.getString("created_On"));
				
				userRoleBeans.add(userRoleBean);				
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
		
		
		return userRoleBeans;
	}
	
	public static List<UserBean> qryUsers(String active)
	{
		String 	funcExceptionErrorMsg 	= "qryUsers. ";
		
		List <UserBean> userBeans = new  ArrayList<UserBean>();
		UserBean userBean = null;
		Connection connection = null;		
		Statement stmt = null;		
		
		try{
			connection = DataSourceManager.getDataSource().getConnection();
			stmt = connection.createStatement();			
			String query = "SELECT `UserName`,"
					+ " `User_FName`,"
					+ " `User_SName`,"
					+ " `User_Email`,"
					+ " `User_Active`,"
					+ " `User_Password`,"
					+ " `Created_By`,"
					+ " `Created_On`,"
					+ " `Updated_By`,"
					+ " `Updated_On` "
					+ "FROM "
					+ "`cm_users`";
					
			if (active != null)
			{
				query= query+ " where user_active='Y'";
			}
			ResultSet  results = stmt.executeQuery(query);
			
			while (results.next()) {
				userBean = new UserBean();
				userBean.setFirstName(results.getString("User_FName"));
				userBean.setSurname(results.getString("User_SName"));
				userBean.setEmail(results.getString("User_Email"));
				userBean.setUserName(results.getString("UserName"));
				userBean.setCreatedBy(results.getString("Created_By"));
				userBean.setCreatedOn(results.getString("Created_On"));
				userBean.setUpdatedBy(results.getString("Updated_By"));
				userBean.setUpdatedOn(results.getString("Updated_On"));
				
				userBeans.add(userBean);				
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
		
		
		return userBeans;
	}
	
	public static UserBean getUserByName(String username) 
	{
		UserBean userDetails=null;
		String funcExceptionErrorMsg ="getUserByName ";
		Connection connection = null;
		Statement stmt = null;
		
		try {
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();
				String qryUser ="select `UserName`,"
						+ " `User_FName`,"
						+ " `User_SName`,"
						+ " `User_Email`,"
						+ " `User_Profession`,"
						+ " `User_Active`,"
						+ " `User_Password`,"
						+ " `Created_By`,"
						+ " `Created_On`,"
						+ " `Updated_By`,"
						+ " `Updated_On`"
						+ "	   from    cm_users  "
						+ "		where UserName ='"+username+"'" ;
				
				ResultSet  results = stmt.executeQuery(qryUser)  ;
				
				while (results.next()) {
					userDetails= new UserBean();
					
					userDetails.setUserName(results.getString("UserName"));
					userDetails.setActive(results.getString("User_Active"));				
					userDetails.setFirstName(results.getString("User_FName"));
					userDetails.setSurname(results.getString("User_SName"));
					userDetails.setProfession(results.getString("User_Profession"));
					userDetails.setEmail(results.getString("User_Email"));
					userDetails.setPassword(results.getString("User_Password"));	
					userDetails.setUpdatedBy(results.getString("Updated_By"));
					userDetails.setUpdatedOn(results.getString("Updated_On"));
					userDetails.setCreatedBy(results.getString("Created_By"));
					userDetails.setCreatedOn(results.getString("Created_On"));
					
												
				}	
				
				
				results.close();
				} catch (SQLException  e) {							
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
				
			
			
		return userDetails;
	}

	
}