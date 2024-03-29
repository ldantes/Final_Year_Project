package model.business.dao;


import java.sql.SQLException;

import javax.naming.NamingException;

import model.beans.UserBean;
import model.beans.UserRoleBean;

//method prototypes for user data abstract object methods
public interface UserDao 
{
	
	public UserBean 		getUser(String userName, String password) throws SQLException, NamingException;

	public void editUser(UserBean user);

	public void removeUserRoles(UserRoleBean role);
	
}

