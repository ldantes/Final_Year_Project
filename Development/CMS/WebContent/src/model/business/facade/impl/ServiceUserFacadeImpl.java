package model.business.facade.impl;

import model.beans.AttendanceBean;
import model.beans.NoteBean;
import model.beans.ServiceUserBean;
import model.beans.SubstanceBean;
import model.beans.TransactionBean;
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
	
	public void adjustBalance(TransactionBean transaction){
		serviceUserDao.adjustBalance(transaction);
	};
	
	public void addNewNote(NoteBean note){
		serviceUserDao.addNewNote(note);
	}
}

	
	
	

	