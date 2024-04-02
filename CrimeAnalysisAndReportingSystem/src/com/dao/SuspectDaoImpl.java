package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.InvalidSuspectDataException;
import com.exceptions.SuspectNotFoundException;
import com.model.Suspect;
import com.utility.DBConnection;

public class SuspectDaoImpl implements SuspectDao{

	public int addRecord( Suspect suspect) throws ClassNotFoundException, SQLException, InvalidSuspectDataException {
		
		Connection conn = DBConnection.getDBConn();
		String sql="insert into suspect(first_name,last_name,dob,gender,contact_number,incidents_incident_id) values (?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		String firstName=suspect.getFirstName();
		String lastName=suspect.getLastName();
		String  dob=suspect.getDob().toString();
		String gender=suspect.getGender();
		String contactInfo=suspect.getContactNumber();
		int incidentId=suspect.getIncidentId();
		
		if(firstName.equals(null) || firstName.equals(""))
			throw new InvalidSuspectDataException("Invalid firstName");
		if(lastName.equals(null) || lastName.equals(""))
			throw new InvalidSuspectDataException("Invalid lastName");
		if(gender.equals(null) || gender.equals(""))
			throw new InvalidSuspectDataException("Invalid gender");
		if(contactInfo.equals(null) || contactInfo.equals("") || contactInfo.length()!=10)
			throw new InvalidSuspectDataException("Invalid phoneNumber");
		
		pstmt.setString(1,firstName );
		pstmt.setString(2,lastName );
		pstmt.setString(3,dob);
		pstmt.setString(4,gender);
		pstmt.setString(5,contactInfo);
		pstmt.setInt(6,incidentId);
		int update = pstmt.executeUpdate();
		
		DBConnection.dbClose();
		return update;
		
	}

	
	
	
	public List<Suspect> getSuspect(int incidentIdForSuspect) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();
		String query="select * from suspect where incidents_incident_id=?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, incidentIdForSuspect);
		ResultSet rst= pstmt.executeQuery();
		
		List<Suspect> suspects=new ArrayList<>();
		while(rst.next()) { 
			
			int suspectId = rst.getInt("suspect_id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			LocalDate dob=LocalDate.parse(rst.getDate("dob").toString());
			String gender=rst.getString("gender");
			String contactInfo=rst.getString("contact_number");
			int incidentId = rst.getInt("incidents_incident_id");
			Suspect suspect = new Suspect(suspectId,firstName,lastName,dob,gender,contactInfo,incidentId);
			suspects.add(suspect); 
	}
    
	DBConnection.dbClose();
	return suspects;
	

}


	public List<Suspect> getSuspectByName(String name) throws SQLException, ClassNotFoundException {
		
		Connection conn = DBConnection.getDBConn();
		String query="select * from suspect where first_name=? or last_name=?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1,name);
		pstmt.setString(2,name);
		ResultSet rst= pstmt.executeQuery();
		
		List<Suspect> suspects=new ArrayList<>();
		
		while(rst.next()) { 
			int id = rst.getInt("suspect_id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			LocalDate dob=LocalDate.parse(rst.getDate("dob").toString());
			String gender=rst.getString("gender");
			String contactInfo=rst.getString("contact_number");
			int incidentId = rst.getInt("incidents_incident_id");
			Suspect suspect = new Suspect(id,firstName,lastName,dob,gender,contactInfo,incidentId);
			suspects.add(suspect); 
	}
		DBConnection.dbClose();
		return suspects;
	}




	public int deleteSuspect(int suspectId) throws SQLException, ClassNotFoundException, SuspectNotFoundException {

		Connection conn = DBConnection.getDBConn();
		
		String query="delete from Suspect where suspect_id=?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		String query1="select * from Suspect where suspect_id=?";
		PreparedStatement pstmt1 = conn.prepareStatement(query1);
		pstmt1.setInt(1, suspectId);
		ResultSet result = pstmt1.executeQuery();
		if(!result.next())
			throw new SuspectNotFoundException("Invalid SuspectId");
		
		pstmt.setInt(1, suspectId);
		int update = pstmt.executeUpdate();
		DBConnection.dbClose();
		return update;
	}
}
