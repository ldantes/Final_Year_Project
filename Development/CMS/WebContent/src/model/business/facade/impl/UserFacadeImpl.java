package model.business.facade.impl;


import java.sql.SQLException;

import javax.naming.NamingException;

import model.beans.UserBean;
import model.beans.UserRoleBean;
import model.business.dao.UserDao;
import model.business.facade.UserFacade;

public class UserFacadeImpl implements UserFacade 
{
	
	
	private UserDao userDao;
	
	
	
	
	public UserBean authenticateUser(String userName, String password) throws SQLException, NamingException
	{
		UserBean userbean =userDao.getUser(userName, password);
		return userbean ;
	
	
	}
	
	public void editUser(UserBean user)
	{
		userDao.editUser(user);
		
	}
	
	public void removeUserRoles(UserRoleBean role)
	{
		userDao.removeUserRoles(role);
	}
	
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
}

	