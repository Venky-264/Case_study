package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.InvalidOfficerDataException;
import com.enums.IncidentType;
import com.enums.Status;
import com.model.Incident;
import com.model.Officer;
import com.utility.DBConnection;

public class OfficerDaoImpl implements OfficerDao {

	@Override
	public List<Officer> fetchAllOfficers() 
			throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from officers";

		Statement stmt = conn.createStatement();

		ResultSet result = stmt.executeQuery(query);

		List<Officer> officers = new ArrayList<>();

		while (result.next()) {
			int id = result.getInt("officer_id");
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
			String badgeNumber= result.getString("badge_number");
			int rank = result.getInt("rank");
			String contactNumber=result.getString("contact_number");
			int agencyId=result.getInt("agencies_agency_id");

			Officer o = new Officer();
			o.setOfficerId(id);
			o.setFirstName(firstName);
			o.setLastName(lastName);
			o.setBadgeNumber(badgeNumber);
			o.setRank(rank);
			o.setPhoneNumber(contactNumber);
			o.setAgencyId(agencyId);
			
			officers.add(o);
		}

		DBConnection.dbClose();

		return officers;
	}
	
	@Override
	public int insertOfficer(Officer officer) throws SQLException, ClassNotFoundException, InvalidOfficerDataException {
		
		Connection conn = DBConnection.getDBConn();

		String query = "insert into officers(first_name,last_name,badge_number,rank,contact_number,agencies_agency_id) values(?,?,?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(query);
		
		String firstName=officer.getFirstName();
		String lastName=officer.getLastName();
		String badgeNumber=officer.getBadgeNumber();
		int rank=officer.getRank();
		String phoneNumber=officer.getPhoneNumber();
		int agencyId=officer.getAgencyId();
		
		String query2="select agency_id from agencies";
		
		Statement stmt2=conn.createStatement();
		
		ResultSet agencyIdSet = stmt2.executeQuery(query2);
		
		boolean agencyIdVal=false;
		
		while(agencyIdSet.next()) {
			if(agencyIdSet.getInt("agency_id")==agencyId)
				agencyIdVal=true;
		}
		
		
		if(firstName.equals(null) || firstName.equals(""))
			throw new InvalidOfficerDataException("FirstName can not be empty");
		if(lastName.equals(null) || lastName.equals(""))
			throw new InvalidOfficerDataException("lastName can not be empty");
		if(badgeNumber.equals(null) || badgeNumber.equals(""))
			throw new InvalidOfficerDataException("badgeNumber can not be null");
		if(rank<0)
			throw new InvalidOfficerDataException("invalid rank");
		if(phoneNumber.equals(null) || phoneNumber.equals("") || phoneNumber.length()!=10)
			throw new InvalidOfficerDataException("invalid phone number");
		if(!agencyIdVal)
			throw new InvalidOfficerDataException("invalid agency id");
		
		stmt.setString(1,firstName);
		stmt.setString(2,lastName);
		stmt.setString(3,badgeNumber);
		stmt.setInt(4, rank);
		stmt.setString(5,phoneNumber);
		stmt.setInt(6, agencyId);
		
		 int execute = stmt.executeUpdate();
		
		DBConnection.dbClose();
		return execute;
	}
	
	@Override
	public int assignOfficerToIncident(int officerId, int incidentId)
			throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();
		
		String query="update incident set officers_officer_id=? where incident_id=?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		stmt.setInt(1,officerId);
		stmt.setInt(2, incidentId);
		
		int update = stmt.executeUpdate();
		DBConnection.dbClose();
		return update;
		
	}

	@Override
	public int deleteOfficer(int officerIdToDelete)
			throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getDBConn();
		
		String query="delete from officers where officer_id=?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		stmt.setInt(1, officerIdToDelete);
		int update = stmt.executeUpdate();
		
		DBConnection.dbClose();
		return update;
	}
	
	@Override
	public List<Incident> listIncidents(int officerIdToListIncidents) throws ClassNotFoundException, SQLException{
		
		Connection conn = DBConnection.getDBConn();
		
		String query="select * from incident where officers_officer_id=?";
		PreparedStatement stmt=conn.prepareStatement(query);
		stmt.setInt(1, officerIdToListIncidents);
		
		ResultSet result = stmt.executeQuery();
		
		List<Incident> incidents=new ArrayList<>();
		
		while(result.next()) {
			
			int id=result.getInt("incident_id");
			String incidentType=result.getString("incident_type");
			LocalDate incidentDate=LocalDate.parse(result.getDate("incident_date").toString());
			String location=result.getString("location");
			String status=result.getString("status");
			int officerId=result.getInt("officers_officer_id");
			
			Incident i=new Incident();
			i.setIncidentId(id);
			i.setIncidentType(IncidentType.valueOf(incidentType.toUpperCase()));
			i.setIncidentDate(incidentDate);
			i.setLocation(location);
			i.setStatus(Status.valueOf(status.toUpperCase()));
			i.setOfficerId(officerId);
			
			incidents.add(i);
		}
		
		
		DBConnection.dbClose();
		return incidents;
	}

}
