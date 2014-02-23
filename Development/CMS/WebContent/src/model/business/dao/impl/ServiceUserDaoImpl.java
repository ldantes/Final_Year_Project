package model.business.dao.impl;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import model.beans.AttendanceBean;
import model.beans.NoteBean;
import model.beans.ServiceUserBean;
import model.beans.SubstanceBean;
import model.beans.TransactionBean;
import model.business.dao.ServiceUserDao;
import model.data.cmsQueryServiceUser;
import model.data.cmsQuerySubstance;
import utilities.DataSourceManager;
import utilities.XMLFunctions;

public class ServiceUserDaoImpl implements ServiceUserDao {
		
	private static Logger log = Logger.getLogger(ServiceUserDaoImpl.class);
	
	public ServiceUserBean updateServiceUser(ServiceUserBean serviceuserbean) 
	{
		
		
	    String fuctionName ="updateServiceUser";
	    Connection connection = null;	
		CallableStatement stmt =null;
		ServiceUserBean returnBean = new ServiceUserBean(); 
		
		try {
			
			String xmlDbDetails = ConvertToXML(serviceuserbean);
			
			
			connection = DataSourceManager.getDataSource().getConnection();		
			stmt = connection.prepareCall("{call cm_system.update_service_user(?,?,?)}");			
			stmt.setString(1, xmlDbDetails);			
			stmt.registerOutParameter(2,java.sql.Types.VARCHAR);			
			stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
			stmt.executeQuery();			
			
			//update bean with new/updated record
			if (stmt.getString(3)!=null){
				String ServiceUserId =stmt.getString(3);
				returnBean = cmsQueryServiceUser.searchServiceUsersById(ServiceUserId);
			}
			
			//db transaction returns OK if successful transaction. Otherwise it returns
			//and error description.
			if (stmt.getString(2).equals("OK")== false){
						
			}
		}
		catch (SQLException  e) {							
			log.error(fuctionName, e);
						
		} finally {
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
		}
		
	
	    
		return returnBean;
	}
	
	
	public ServiceUserBean addServiceUser(ServiceUserBean serviceuserbean) 
	{
		
		
	    String fuctionName ="addServiceUser";
	    Connection connection = null;	
		CallableStatement stmt =null;
		ServiceUserBean returnBean = new ServiceUserBean(); 
		
		try {
			
			String xmlDbDetails = ConvertToXML(serviceuserbean);
			
			
			connection = DataSourceManager.getDataSource().getConnection();		
			stmt = connection.prepareCall("{call cm_system.add_service_user(?,?,?)}");			
			stmt.setString(1, xmlDbDetails);			
			stmt.registerOutParameter(2,java.sql.Types.VARCHAR);			
			stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
			stmt.executeQuery();			
			
			//update bean with new/updated record
			if (stmt.getString(3)!=null){
				String ServiceUserId =stmt.getString(3);
				returnBean = cmsQueryServiceUser.searchServiceUsersById(ServiceUserId);
			}
			
			//db transaction returns OK if successful transaction. Otherwise it returns
			//and error description.
			if (stmt.getString(2).equals("OK")== false){
						
			}
		}
		catch (SQLException  e) {							
			log.error(fuctionName, e);
						
		} finally {
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
		}
		
	
	    
		return returnBean;
	}
	
	public void insertNewSubstanceResult(SubstanceBean substanceBean, int accum){
		
		String fuctionName ="insertNewSubstanceResult";
	    Connection connection = null;	
		CallableStatement stmt =null;
		
		
		try {
					
				String xmlDbDetails = ConvertSubstanceToXML(substanceBean,accum);
					
					
				connection = DataSourceManager.getDataSource().getConnection();		
				stmt = connection.prepareCall("{call cm_system.add_substance_result(?,?)}");			
				stmt.setString(1, xmlDbDetails);			
				stmt.registerOutParameter(2,java.sql.Types.VARCHAR);			
				stmt.executeQuery();			
						
				if (stmt.getString(2).equals("OK")== false){
									
				}
			}
		catch (SQLException  e) {							
			log.error(fuctionName, e);
						
		} finally {
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
		}
		
	}
	
	public void insertNewAttendance(AttendanceBean attendanceBean)
	{
		String fuctionName ="insertNewAttendance";
	    Connection connection = null;	
		CallableStatement stmt =null;
		
		
		try {
					
				String xmlDbDetails = ConvertAttendanceeToXML(attendanceBean);
					
					
				connection = DataSourceManager.getDataSource().getConnection();		
				stmt = connection.prepareCall("{call cm_system.add_attendance(?,?)}");			
				stmt.setString(1, xmlDbDetails);			
				stmt.registerOutParameter(2,java.sql.Types.VARCHAR);			
				stmt.executeQuery();			
						
				if (stmt.getString(2).equals("OK")== false){
									
				}
			}
		catch (SQLException  e) {							
			log.error(fuctionName, e);
						
		} finally {
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
		}
		
	}
	
	public void adjustBalance(TransactionBean transaction)
	{
		String fuctionName ="adjustBalance";
	    Connection connection = null;	
		CallableStatement stmt =null;
		
		
		try {
					
				
					
					
				connection = DataSourceManager.getDataSource().getConnection();		
				stmt = connection.prepareCall("{call cm_system.adjust_balance(?,?,?,?,?)}");			
				stmt.setString(1, transaction.getAccount_Id());		
				stmt.setString(2, transaction.getAmount_Credited());	
				stmt.setString(3, transaction.getAmount_Withdrawn());	
				stmt.setString(4, transaction.getApproved_By());	
				stmt.registerOutParameter(5,java.sql.Types.VARCHAR);			
				stmt.executeQuery();			
						
				if (stmt.getString(5).equals("OK")== false){
									
				}
			}
		catch (SQLException  e) {							
			log.error(fuctionName, e);
						
		} finally {
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
		}
		
	}
	
	public void addNewNote(NoteBean note)
	{
		String fuctionName ="addNewNote";
	    Connection connection = null;	
		CallableStatement stmt =null;
		
		
		try {
					
				connection = DataSourceManager.getDataSource().getConnection();		
				stmt = connection.prepareCall("{call cm_system.newNote(?,?,?,?,?)}");			
				stmt.setString(1, note.getClient_Id());	
				if(note.getId() != null )
				{
					stmt.setString(2, note.getId());	
				}
				else
				{
					stmt.setString(2, "0");	
				}
				stmt.setString(3, note.getNote());	
				stmt.setString(4, note.getUserName());	
				stmt.registerOutParameter(5,java.sql.Types.VARCHAR);			
				stmt.executeQuery();			
						
				if (stmt.getString(5).equals("OK")== false){
									
				}
			}
		catch (SQLException  e) {							
			log.error(fuctionName, e);
						
		} finally {
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException sqle) {
				log.error(fuctionName, sqle);
				
			}
		}
		
	}
	
	public void changeEligibility(ServiceUserBean serviceuser) 
	{
		String funcExceptionErrorMsg ="changeEligibility";
	    Connection connection = null;	
		Statement stmt =null;
		
		for(int i=0; i < serviceuser.getEligibilityBeans().size(); i++)
		{
			if(serviceuser.getEligibilityBeans().get(i).getId() == "1")
			{
				try{
					connection = DataSourceManager.getDataSource().getConnection();
					stmt = connection.createStatement();			
					String query = "update cm_client_eligibilities set active ='"+serviceuser.getEligibilityBeans().get(i).getActive()+"' where client_id ="+serviceuser.getId()+" and eligibility_id ="+ serviceuser.getEligibilityBeans().get(i).getId()+ "";
					
					stmt.executeUpdate(query);
					
				}
				catch (SQLException  e) {							
					log.error(funcExceptionErrorMsg, e);
								
				} finally {
					try {
						if(stmt != null){
							stmt.close();
						}
					} catch (SQLException sqle) {
						log.error(funcExceptionErrorMsg, sqle);
						
					}
					try {
						if(connection != null){
							connection.close();
						}
					} catch (SQLException sqle) {
						log.error(funcExceptionErrorMsg, sqle);
						
					}
				}
			
				}
		}
		
		
	}

	public void updateDTC(ServiceUserBean serviceUserBean)
	{
		String funcExceptionErrorMsg ="updateDTC";
	    Connection connection = null;	
		Statement stmt =null;
		
		
		try{
					connection = DataSourceManager.getDataSource().getConnection();
					stmt = connection.createStatement();			
					
					
					String query = "update cm_client_date_to_clean"
							+ " set card = (select id from cm_date_to_clean where Order_of_Progress ="+serviceUserBean.getDateToClean().getOrderOfProgress()+"),"
									+ " set_on = curdate(),"
									+ " Date_to_Clean= DATE_ADD(curdate(),INTERVAL 7 DAY), "
									+ " set_by='"+serviceUserBean.getDateToClean().getSetBy()+"'"
									+ " where client_id ="+serviceUserBean.getId()+"";
					
					stmt.executeUpdate(query);
					
				}
				catch (SQLException  e) {							
					log.error(funcExceptionErrorMsg, e);
								
				} finally {
					try {
						if(stmt != null){
							stmt.close();
						}
					} catch (SQLException sqle) {
						log.error(funcExceptionErrorMsg, sqle);
						
					}
					try {
						if(connection != null){
							connection.close();
						}
					} catch (SQLException sqle) {
						log.error(funcExceptionErrorMsg, sqle);
						
					}
				}
			
				
		
		
		
	}
	
	
	private String ConvertAttendanceeToXML(AttendanceBean attendanceBean) {
		String xml ="";
		Document doc = null;	
	    
		try{			
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		    DocumentBuilder parser = fact.newDocumentBuilder();
		    doc = parser.newDocument();
		    
		    //node elements must match the db column names
	        Node root = doc.createElement("attendance");
	        doc.appendChild(root);
	        Node node1 = doc.createElement("serviceuser");
	        root.appendChild(node1);	
	        Node node2 = doc.createElement("username");
	        root.appendChild(node2);
	        Node node3 = doc.createElement("timedate");
	        root.appendChild(node3);
	        Node node4 = doc.createElement("attended");
	        root.appendChild(node4);
	        Node node5 = doc.createElement("missedreason");
	        root.appendChild(node5);
	        Node node6 = doc.createElement("validreason");
	        root.appendChild(node6);
	        Node node7 = doc.createElement("reviewmeeting");
	        root.appendChild(node7);
	        Node node8 = doc.createElement("participation");
	        root.appendChild(node8);
	        Node node9 = doc.createElement("staffprof");
	        root.appendChild(node9);
	               
	        
	       
	        	        
	        node1.appendChild(doc.createTextNode(attendanceBean.getSrvUserId()));
	        node2.appendChild(doc.createTextNode(attendanceBean.getUsername()));
	       	node3.appendChild(doc.createTextNode(attendanceBean.getTimeDate()));
	        node4.appendChild(doc.createTextNode(attendanceBean.getAttended()));
	        node5.appendChild(doc.createTextNode(attendanceBean.getAttndFailedReason()));
	        node6.appendChild(doc.createTextNode(attendanceBean.getValidReason()));
	        node7.appendChild(doc.createTextNode(attendanceBean.getTreatmentReviewMeeting()));
	        node8.appendChild(doc.createTextNode(attendanceBean.getParticipation()));
	        node9.appendChild(doc.createTextNode(attendanceBean.getStaffProfession()));
	       
	        
	        xml = XMLFunctions.xmlToString(doc);
		}
		catch (Exception  e) {							
			log.error( e);
						
		} 
	        	        
			
	    return xml;    
	}


	private String ConvertSubstanceToXML(SubstanceBean substanceBean, int accum) {
		String xml ="";
		Document doc = null;	
	    
		try{			
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		    DocumentBuilder parser = fact.newDocumentBuilder();
		    doc = parser.newDocument();
		    
		    //node elements must match the db column names
	        Node root = doc.createElement("substances");
	        doc.appendChild(root);
	        Node node1 = doc.createElement("serviceuser");
	        root.appendChild(node1);	
	        Node node2 = doc.createElement("substance");
	        root.appendChild(node2);
	        Node node3 = doc.createElement("result");
	        root.appendChild(node3);
	        Node node4 = doc.createElement("adminby");
	        root.appendChild(node4);
	        Node node5 = doc.createElement("adminon");
	        root.appendChild(node5);
	        Node node6 = doc.createElement("createdby");
	        root.appendChild(node6);
	        Node node7 = doc.createElement("accum");
	        root.appendChild(node7);
	       
	        
	       
	        	        
	        node1.appendChild(doc.createTextNode(substanceBean.getSrvUserId()));
	        node2.appendChild(doc.createTextNode(substanceBean.getSubstance()));
	       	node3.appendChild(doc.createTextNode(substanceBean.getResult()));
	        node4.appendChild(doc.createTextNode(substanceBean.getAdministeredBy()));
	        node5.appendChild(doc.createTextNode(substanceBean.getAdministeredOn()));
	        node6.appendChild(doc.createTextNode(substanceBean.getCreatedBy()));
	        node7.appendChild(doc.createTextNode(""+accum+""));
	       
	        	        
			//convert xml to string
	        xml = XMLFunctions.xmlToString(doc);
		}
		catch (Exception  e) {							
			log.error( e);
						
		} 
		return xml;
		
	}	
	


	private String ConvertToXML(ServiceUserBean serviceuserbean) {
		
		String xml ="";
		Document doc = null;	
	    
		try{			
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		    DocumentBuilder parser = fact.newDocumentBuilder();
		    doc = parser.newDocument();
		    
		    //node elements must match the db column names
	        Node root = doc.createElement("serviceUser");
	        doc.appendChild(root);
	        Node node1 = doc.createElement("id");
	        root.appendChild(node1);	
	        Node node2 = doc.createElement("fname");
	        root.appendChild(node2);
	        Node node3 = doc.createElement("sname");
	        root.appendChild(node3);
	        Node node4 = doc.createElement("gender");
	        root.appendChild(node4);
	        Node node5 = doc.createElement("dob");
	        root.appendChild(node5);
	        Node node6 = doc.createElement("address");
	        root.appendChild(node6);
	        Node node7 = doc.createElement("contactnumber");
	        root.appendChild(node7);
	        Node node8 = doc.createElement("ethnicity");
	        root.appendChild(node8);
	        Node node9 = doc.createElement("nationality");
	        root.appendChild(node9);
	        Node node10 = doc.createElement("familyinfo");
	        root.appendChild(node10);
	        Node node11 = doc.createElement("occupation");
	        root.appendChild(node11);
	        Node node12 = doc.createElement("username");
	        root.appendChild(node12);
	        Node node13 = doc.createElement("pps");
	        root.appendChild(node13);
	        
	       
	        	        
	        node1.appendChild(doc.createTextNode(serviceuserbean.getId().toString()));
	        node2.appendChild(doc.createTextNode(serviceuserbean.getfirstname()));
	        node3.appendChild(doc.createTextNode(serviceuserbean.getSurname()));
	       	node4.appendChild(doc.createTextNode(serviceuserbean.getGender()));
	        node5.appendChild(doc.createTextNode(serviceuserbean.getDoB()));
	        node6.appendChild(doc.createTextNode(serviceuserbean.getAddress()));
	        node7.appendChild(doc.createTextNode(serviceuserbean.getContactNumber()));
	        node8.appendChild(doc.createTextNode(serviceuserbean.getEthnicity()));
	        node9.appendChild(doc.createTextNode(serviceuserbean.getNationality()));
	        node10.appendChild(doc.createTextNode(serviceuserbean.getFamilyInformation()));
	        node11.appendChild(doc.createTextNode(serviceuserbean.getOccupation()));
	        node12.appendChild(doc.createTextNode(serviceuserbean.getUpdatedBy()));
	        node13.appendChild(doc.createTextNode(serviceuserbean.getPps()));
	        	        
			//convert xml to string
	        xml = XMLFunctions.xmlToString(doc);
		}
		catch (Exception e) {	
			
		} 
		return xml;
		
	}


	


	
}

