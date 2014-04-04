package model.business.facade;

import java.sql.SQLException;

import javax.naming.NamingException;

import model.beans.AttendanceBean;
import model.beans.NoteBean;
import model.beans.ServiceUserBean;
import model.beans.SubstanceBean;
import model.beans.TransactionBean;

/**
Leslie Ducray - 2014

 */


public interface ServiceUserFacade {
	
	public ServiceUserBean updateServiceUser(ServiceUserBean serviceuserbean) throws SQLException, NamingException;

	public ServiceUserBean addServiceUser(ServiceUserBean serviceUserBean);

	public void newSubstanceResult(SubstanceBean substanceBean, int accum);

	public void newAttendance(AttendanceBean attendanceBean);

	public void adjustBalance(TransactionBean transaction);

	public void addNewNote(NoteBean note);

	public void changeEligibility(ServiceUserBean serviceuser);

	public void updateDTC(ServiceUserBean serviceUserBean);
		
	
	
}
