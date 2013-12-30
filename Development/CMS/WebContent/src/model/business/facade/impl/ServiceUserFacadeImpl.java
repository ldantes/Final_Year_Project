package model.business.facade.impl;

import model.beans.AttendanceBean;
import model.beans.ServiceUserBean;
import model.beans.SubstanceBean;
import model.business.dao.ServiceUserDao;
import model.business.facade.ServiceUserFacade;


public class ServiceUserFacadeImpl implements ServiceUserFacade 
{
	
	
	private ServiceUserDao serviceUserDao;

	public ServiceUserBean updateServiceUser(ServiceUserBean serviceuserbean) 
	{
		
		return serviceUserDao.updateServiceUser(serviceuserbean);
	}
	
	public ServiceUserBean addServiceUser(ServiceUserBean serviceUserBean)
	{
		return serviceUserDao.addServiceUser(serviceUserBean);
	}
	
	public void setServiceUserDao(ServiceUserDao serviceUserDao) {
		this.serviceUserDao = serviceUserDao;
	}
	
	public void newSubstanceResult(SubstanceBean substanceBean){
		serviceUserDao.insertNewSubstanceResult(substanceBean);
	};
	
	public void newAttendance(AttendanceBean attendanceBean){
		serviceUserDao.insertNewAttendance(attendanceBean);
	};
}

	
	
	

	