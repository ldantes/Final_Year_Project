package model.business.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import utilities.DataSourceManager;
import model.beans.UserBean;
import model.beans.UserRoleBean;
import model.business.dao.UserDao;
import model.data.cmsQueryUsers;




public class UserDaoImpl implements UserDao{
	
	private static Logger log = Logger.getLogger(cmsQueryUsers.class);
	private final String qryUsers ="select distinct"
			+ "    username as userName,"
			+ "    user_Fname as firstName, "
			+ "    User_Sname as surname, "
			+ "    user_active as active, "
			+ "	   User_Profession as profession,"
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
					userDetails.setProfession(results.getString("profession"));
					userDetails.setEmail(results.getString("email"));
					userDetails.setPassword(results.getString("password"));				
					
												
				}	
				
				
				results.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			
			
		return userDetails;
	}


	public void editUser(UserBean user)
	{
		UserBean userDetails=null;
		String funcExceptionErrorMsg ="getUserByName ";
		Connection connection = null;
		Statement stmt = null;
		
		
				
		try {
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();
				String statement ="";
				
				if(cmsQueryUsers.getUserByName(user.getUserName()) != null)
				{
					 statement ="UPDATE `cm_system`.`cm_users`"
					 		+ "SET `User_FName` = '"+user.getFirstName()+"',"
					 		+ "`User_SName` = '"+user.getSurname()+"',"
					 		+ "`User_Email` = '"+user.getEmail()+"',"
					 		+ "`User_Profession` = '"+user.getProfession()+"',"
					 		+ "`User_Active` = '"+user.getActive()+"',"
					 		+ "`User_Password` = '"+user.getPassword()+"',"
					 		+ "`Updated_By` = '"+user.getUpdatedBy()+"',"
					 		+ "`Updated_On` = curdate()"
					 		+ "WHERE username = '"+user.getUserName()+"'";

				}
				else
				{
					 statement ="INSERT INTO `cm_system`.`cm_users`"
					 		+ "(`UserName`,`User_FName`,`User_SName`,`User_Email`,`User_Profession`,`User_Active`,`User_Password`,`Created_By`,`Created_On`,`Updated_By`,`Updated_On`)"
					 		+ " VALUES ('"+user.getUserName()+"','"+user.getFirstName()+"',"
					 		+ "'"+user.getSurname()+"','"+user.getEmail()+"',"
					 		+ "'"+user.getProfession()+"',"
					 		+ "'"+user.getActive()+"',"
					 		+ "'"+user.getPassword()+"',"
					 		+ "'"+user.getUpdatedBy()+"', curdate(),'"+user.getUpdatedBy()+"',curdate())";

				}
				
				stmt.executeUpdate(statement)  ;
				
				for(int i=0;i<user.getUserRoles().size();i++)
				{
					statement="insert into cm_user_roles values ('"+user.getUserRoles().get(i).getRoleName()+"','"+user.getUserName()+"','"+user.getUpdatedBy()+"',curdate())";
					stmt.executeUpdate(statement)  ;
				}
				
				
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
			
		
	}

	
	public void removeUserRoles(UserRoleBean role)
	{
		String funcExceptionErrorMsg ="removeUserRoles ";
		Connection connection = null;
		Statement stmt = null;
		
		
				
		try {
				connection = DataSourceManager.getDataSource().getConnection();
				stmt = connection.createStatement();
				String statement ="delete from cm_user_roles where role_Name ='"+role.getRoleName()+"' and userName ='"+role.getUserName()+"'";

				stmt.executeUpdate(statement)  ;
				
				
				
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
			
	}
		
	
	
}

