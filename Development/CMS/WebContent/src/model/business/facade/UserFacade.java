package model.business.facade;

import java.sql.SQLException;

import javax.naming.NamingException;

import model.beans.UserBean;
import model.beans.UserRoleBean;



public interface UserFacade {
	
	public UserBean authenticateUser(String userName, String password) throws SQLException, NamingException;

	public void editUser(UserBean user);

	public void removeUserRoles(UserRoleBean userRole);
	
}
