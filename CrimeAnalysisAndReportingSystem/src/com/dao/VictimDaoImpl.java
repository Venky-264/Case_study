package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.InvalidVictimDataException;
import com.exceptions.VictimNotFoundException;
import com.model.Victim;
import com.utility.DBConnection;

public class VictimDaoImpl implements VictimDao{

	public int addRecord( Victim victim) throws ClassNotFoundException, SQLException, InvalidVictimDataException {
		
		Connection conn = DBConnection.getDBConn();
		String sql="insert into victim(first_name,last_name,dob,gender,contact_number,incidents_incident_id) values (?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		String firstName=victim.getFirstName();
		String lastName=victim.getLastName();
		String  dob=victim.getDob().toString();
		String gender=victim.getGender();
		String contactInfo=victim.getContactNumber();
		int incidentId=victim.getIncidentId();
		
		if(firstName.equals(null) || firstName.equals(""))
			throw new InvalidVictimDataException("Invalid firstName");
		if(lastName.equals(null) || lastName.equals(""))
			throw new InvalidVictimDataException("Invalid lastName");
		if(gender.equals(null) || gender.equals(""))
			throw new InvalidVictimDataException("Invalid gender");
		if(contactInfo.equals(null) || contactInfo.equals("") || contactInfo.length()!=10)
			throw new InvalidVictimDataException("Invalid phoneNumber");
		
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

	
	
	
	public List<Victim> getVictim(int incidentIdForvictim) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();
		String query="select * from victim where incidents_incident_id=?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, incidentIdForvictim);
		ResultSet rst= pstmt.executeQuery();
		
		List<Victim> victims=new ArrayList<>();
		while(rst.next()) { 
			
			int victimId = rst.getInt("victim_id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			LocalDate dob=LocalDate.parse(rst.getDate("dob").toString());
			String gender=rst.getString("gender");
			String contactInfo=rst.getString("contact_number");
			int incidentId = rst.getInt("incidents_incident_id");
			Victim victim = new Victim(victimId,firstName,lastName,dob,gender,contactInfo,incidentId);
			victims.add(victim); 
	}
    
	DBConnection.dbClose();
	return victims;
	

}




	public List<Victim> getVictimByName(String name) throws SQLException, ClassNotFoundException {
		
		Connection conn = DBConnection.getDBConn();
		String query="select * from victim where first_name=? or last_name=?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1,name);
		pstmt.setString(2,name);
		ResultSet rst= pstmt.executeQuery();
		
		List<Victim> victims=new ArrayList<>();
		
		while(rst.next()) { 
			int id = rst.getInt("victim_id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			LocalDate dob=LocalDate.parse(rst.getDate("dob").toString());
			String gender=rst.getString("gender");
			String contactInfo=rst.getString("contact_number");
			int incidentId = rst.getInt("incidents_incident_id");
			Victim victim = new Victim(id,firstName,lastName,dob,gender,contactInfo,incidentId);
			victims.add(victim); 
	}
		DBConnection.dbClose();
		return victims;
	}




	public int deleteVictim(int victimId) throws SQLException, ClassNotFoundException, VictimNotFoundException {

		Connection conn = DBConnection.getDBConn();
		
		String query="delete from victim where victim_id=?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		String query1="select * from victim where victim_id=?";
		PreparedStatement pstmt1 = conn.prepareStatement(query1);
		pstmt1.setInt(1, victimId);
		ResultSet result = pstmt1.executeQuery();
		if(!result.next())
			throw new VictimNotFoundException("Invalid victimId");
		
		pstmt.setInt(1, victimId);
		int update = pstmt.executeUpdate();
		DBConnection.dbClose();
		return update;
	}
}
