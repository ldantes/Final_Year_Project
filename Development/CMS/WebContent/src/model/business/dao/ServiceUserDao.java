package model.business.dao;


import model.beans.AttendanceBean;
import model.beans.NoteBean;
import model.beans.ServiceUserBean;
import model.beans.SubstanceBean;
import model.beans.TransactionBean;


// method prototypes for Service user data abstract object methods
public interface ServiceUserDao 
{
	
	public ServiceUserBean 		updateServiceUser(ServiceUserBean serviceuserbean);
	public ServiceUserBean      addServiceUser(ServiceUserBean serviceUserBean);
	public void insertNewSubstanceResult(SubstanceBean substanceBean, int accum);
	public void insertNewAttendance(AttendanceBean attendanceBean);
	public void adjustBalance(TransactionBean transaction);
	public void addNewNote(NoteBean note);
	public void changeEligibility(ServiceUserBean serviceuser);
	public void updateDTC(ServiceUserBean serviceUserBean);
	
}

