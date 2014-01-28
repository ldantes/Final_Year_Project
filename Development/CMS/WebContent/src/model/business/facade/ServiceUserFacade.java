package model.business.facade;

import java.sql.SQLException;

import javax.naming.NamingException;

import model.beans.AttendanceBean;
import model.beans.NoteBean;
import model.beans.ServiceUserBean;
import model.beans.SubstanceBean;
import model.beans.TransactionBean;




public interface ServiceUserFacade {
	
	public ServiceUserBean updateServiceUser(ServiceUserBean serviceuserbean) throws SQLException, NamingException;

	public ServiceUserBean addServiceUser(ServiceUserBean serviceUserBean);

	public void newSubstanceResult(SubstanceBean substanceBean);

	public void newAttendance(AttendanceBean attendanceBean);

	public void adjustBalance(TransactionBean transaction);

	public void addNewNote(NoteBean note);
		
	
	
}