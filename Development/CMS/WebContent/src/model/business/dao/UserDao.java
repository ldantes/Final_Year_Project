package model.business.dao;


import java.sql.SQLException;

import javax.naming.NamingException;

import model.beans.UserBean;


public interface UserDao 
{
	
	public UserBean 		getUser(String userName, String password) throws SQLException, NamingException;
	
}
