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
}