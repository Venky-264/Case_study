package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.enums.IncidentType;

import com.enums.Status;
import com.exceptions.InvalidIncidentDataException;
import com.model.Incident;
import com.utility.DBConnection;

public class IncidentDaoImpl implements IncidentDao{

	@Override
	public List<Incident> getAllIncidents() throws ClassNotFoundException, SQLException {
		
		Connection conn=DBConnection.getDBConn();
		
		String query="select * from incident";
		
		Statement stmt=conn.createStatement();
		
		ResultSet result = stmt.executeQuery(query);
		
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
	
	@Override
	public int createIncident(Incident incident) throws ClassNotFoundException, SQLException, InvalidIncidentDataException {
		
		Connection conn = DBConnection.getDBConn();
		
		String query="insert into incident (incident_type,incident_date,location,description,status,Officers_officer_id)values (?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		if(incident.getLocation().equals(null) || incident.getLocation().equals(""))
			throw new InvalidIncidentDataException("location cant be empty");
		if(incident.getDescription().equals(null) || incident.getDescription().equals(""))
			throw new InvalidIncidentDataException("description cant be empty");
		
		pstmt.setString(1, incident.getIncidentType().toString());
		pstmt.setString(2,incident.getIncidentDate().toString());
		pstmt.setString(3, incident.getLocation());
		pstmt.setString(4, incident.getDescription());
		pstmt.setString(5, incident.getStatus().toString());
		pstmt.setInt(6,incident.getOfficerId());
		int update = pstmt.executeUpdate();
		
		
		
		DBConnection.dbClose();
		return update;
	}
	
	@Override
	public List<Incident> getIncidentsInDateRange(LocalDate from ,LocalDate to) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();

		String query = "Select * from incident where incident_date between ? and ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1,from.toString());
		pstmt.setString(2,to.toString());
		
		ResultSet result = pstmt.executeQuery();
		List <Incident>incidents = new ArrayList<>();
		while(result.next())
		{
			int id=result.getInt("incident_id");
			String incidentType=result.getString("incident_type");
			LocalDate incidentDate=LocalDate.parse(result.getDate("incident_date").toString());
			String location=result.getString("location");
			String description = result.getString("description");
			String status=result.getString("status");
			int officerId=result.getInt("Officers_officer_id");
			
			Incident incident=new Incident();
			incident.setIncidentId(id);
			incident.setIncidentType(IncidentType.valueOf(incidentType.toUpperCase()));
			incident.setIncidentDate(incidentDate);
			incident.setLocation(location);
			incident.setDescription(description);
			incident.setStatus(Status.valueOf(status.toUpperCase()));
			incident.setOfficerId(officerId);
			incidents.add(incident);
		}
		DBConnection.dbClose();
		return incidents;
	}
	
	@Override
	public List<Incident> searchIncidents(IncidentType criteria) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();
		
		String query = "Select * from incident where incident_type = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, criteria.toString());
		ResultSet result = pstmt.executeQuery();
		List <Incident> incidents = new ArrayList<>();
		while(result.next())
		{
			int id=result.getInt("incident_id");
			String incidentType=result.getString("incident_type");
			LocalDate incidentDate=LocalDate.parse(result.getDate("incident_date").toString());
			String location=result.getString("location");
			String description = result.getString("description");
			String status=result.getString("status");
			int officerId=result.getInt("Officers_officer_id");
			
			Incident incident=new Incident();
			incident.setIncidentId(id);
			incident.setIncidentType(IncidentType.valueOf(incidentType.toUpperCase()));
			incident.setIncidentDate(incidentDate);
			incident.setLocation(location);
			incident.setDescription(description);
			incident.setStatus(Status.valueOf(status.toUpperCase()));
			incident.setOfficerId(officerId);
			incidents.add(incident);
		}
		DBConnection.dbClose();
		return incidents;
	}
	
	@Override
	public int UpdateIncident(int incidentIdToUpdateStatus, Status status) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();
		
		String query="update incident set status=? where incident_id=?";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		pstmt.setString(1, status.toString().toUpperCase());
		pstmt.setInt(2, incidentIdToUpdateStatus);
		
		int update = pstmt.executeUpdate();
		
		DBConnection.dbClose();
		return update;
	}
	
}
