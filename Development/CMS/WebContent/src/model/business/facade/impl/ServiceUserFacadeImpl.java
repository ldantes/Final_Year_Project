package model.business.facade.impl;

import model.beans.ServiceUserBean;
import model.business.dao.ServiceUserDao;
import model.business.facade.ServiceUserFacade;


public class ServiceUserFacadeImpl implements ServiceUserFacade 
{
	
	
	private ServiceUserDao serviceUserDao;

	public ServiceUserBean updateServiceUser(ServiceUserBean serviceuserbean) 
	{
		
		return serviceUserDao.updateServiceUser(serviceuserbean);
	}
	
	public void setServiceUserDao(ServiceUserDao serviceUserDao) {
		this.serviceUserDao = serviceUserDao;
	}
}

	
	
	

	