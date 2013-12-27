package model.business.facade;

import java.sql.SQLException;

import javax.naming.NamingException;

import model.beans.ServiceUserBean;




public interface ServiceUserFacade {
	
	public ServiceUserBean updateServiceUser(ServiceUserBean serviceuserbean) throws SQLException, NamingException;
		
	
	
}
