package model.business.dao.impl;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import model.beans.AttendanceBean;
import model.beans.ServiceUserBean;
import model.beans.SubstanceBean;
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
				returnBean.setName("ERROR");				
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
				returnBean.setName("ERROR");				
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
	
	public void insertNewSubstanceResult(SubstanceBean substanceBean){
		
		String fuctionName ="insertNewSubstanceResult";
	    Connection connection = null;	
		CallableStatement stmt =null;
		
		
		try {
					
				String xmlDbDetails = ConvertSubstanceToXML(substanceBean);
					
					
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
	       
	        
	       
	        	        
	        node1.appendChild(doc.createTextNode(attendanceBean.getSrvUserId()));
	        node2.appendChild(doc.createTextNode(attendanceBean.getUsername()));
	       	node3.appendChild(doc.createTextNode(attendanceBean.getTimeDate()));
	        node4.appendChild(doc.createTextNode(attendanceBean.getAttended()));
	        node5.appendChild(doc.createTextNode(attendanceBean.getAttndFailedReason()));
	        node6.appendChild(doc.createTextNode(attendanceBean.getValidReason()));
	        node7.appendChild(doc.createTextNode(attendanceBean.getTreatmentReviewMeeting()));
	        
	        xml = XMLFunctions.xmlToString(doc);
		}
		catch (Exception  e) {							
			log.error( e);
						
		} 
	        	        
			
	    return xml;    
	}


	private String ConvertSubstanceToXML(SubstanceBean substanceBean) {
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
	       
	        
	       
	        	        
	        node1.appendChild(doc.createTextNode(substanceBean.getSrvUserId()));
	        node2.appendChild(doc.createTextNode(substanceBean.getSubstance()));
	       	node3.appendChild(doc.createTextNode(substanceBean.getResult()));
	        node4.appendChild(doc.createTextNode(substanceBean.getAdministeredBy()));
	        node5.appendChild(doc.createTextNode(substanceBean.getAdministeredOn()));
	        node6.appendChild(doc.createTextNode(substanceBean.getCreatedBy()));
	       
	        	        
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
	        Node node2 = doc.createElement("name");
	        root.appendChild(node2);
	        Node node3 = doc.createElement("gender");
	        root.appendChild(node3);
	        Node node4 = doc.createElement("dob");
	        root.appendChild(node4);
	        Node node5 = doc.createElement("address");
	        root.appendChild(node5);
	        Node node6 = doc.createElement("contactnumber");
	        root.appendChild(node6);
	        Node node7 = doc.createElement("ethnicity");
	        root.appendChild(node7);
	        Node node8 = doc.createElement("nationality");
	        root.appendChild(node8);
	        Node node9 = doc.createElement("familyinfo");
	        root.appendChild(node9);
	        Node node10 = doc.createElement("occupation");
	        root.appendChild(node10);
	        Node node11 = doc.createElement("username");
	        root.appendChild(node11);
	        
	       
	        	        
	        node1.appendChild(doc.createTextNode(serviceuserbean.getId().toString()));
	        node2.appendChild(doc.createTextNode(serviceuserbean.getName()));
	       	node3.appendChild(doc.createTextNode(serviceuserbean.getGender()));
	        node4.appendChild(doc.createTextNode(serviceuserbean.getDoB()));
	        node5.appendChild(doc.createTextNode(serviceuserbean.getAddress()));
	        node6.appendChild(doc.createTextNode(serviceuserbean.getContactNumber()));
	        node7.appendChild(doc.createTextNode(serviceuserbean.getEthnicity()));
	        node8.appendChild(doc.createTextNode(serviceuserbean.getNationality()));
	        node9.appendChild(doc.createTextNode(serviceuserbean.getFamilyInformation()));
	        node10.appendChild(doc.createTextNode(serviceuserbean.getOccupation()));
	        node11.appendChild(doc.createTextNode(serviceuserbean.getUpdatedBy()));
	        	        
			//convert xml to string
	        xml = XMLFunctions.xmlToString(doc);
		}
		catch (Exception e) {	
			
		} 
		return xml;
		
	}



	
}

