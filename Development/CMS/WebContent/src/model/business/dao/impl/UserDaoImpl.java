package model.business.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utilities.DataSourceManager;
import model.beans.UserBean;
import model.beans.UserRoleBean;
import model.business.dao.UserDao;
import model.data.cmsQueryUsers;




public class UserDaoImpl implements UserDao{
		
	private final String qryUsers ="select distinct"
			+ "    username as userName,"
			+ "    user_Fname as firstName, "
			+ "    User_Sname as surname, "
			+ "    user_active as active, "
			+ "    user_password as password,"
			+ "    user_email as email"
			+ "	   from    cm_users " ;
	
	public UserBean getUser(String userName, String password) 
	{
		String qryCondition = " where username = '" + userName + "'" + " and user_password = '" + password + "' and user_active ='Y'";
		return getUser(qryUsers +  qryCondition);
	}
	
	public UserBean getUser(String qryCondition) 
	{
		UserBean userDetails=null;
		Connection connection = null;
		Statement stmt = null;
		
		try {
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();
				ResultSet  results = stmt.executeQuery(qryCondition)  ;
				
				while (results.next()) {
					userDetails= new UserBean();
					
					userDetails.setUserName(results.getString("userName"));
					userDetails.setActive(results.getString("active"));				
					userDetails.setFirstName(results.getString("firstName"));
					userDetails.setSurname(results.getString("surname"));
					userDetails.setEmail(results.getString("email"));
					userDetails.setPassword(results.getString("password"));				
					
												
				}	
				List <UserRoleBean> userRoles 	=null;
				userRoles = cmsQueryUsers.qryUserRoles(userDetails);
				
				userDetails.setUserRoles(userRoles);
				
				results.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			
			
		return userDetails;
	}


	

	
	
		
	
	
}

