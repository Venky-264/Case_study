package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exceptions.InvalidOfficerDataException;
import com.model.Incident;
import com.model.Officer;

public interface OfficerDao {

	public List<Officer> fetchAllOfficers() 
			throws ClassNotFoundException, SQLException;
	public int insertOfficer(Officer officer) 
			throws SQLException,ClassNotFoundException,InvalidOfficerDataException;
	public int assignOfficerToIncident(int officerId, int incidentId)
			throws ClassNotFoundException, SQLException;
	public int deleteOfficer(int officerIdToDelete)
			throws ClassNotFoundException, SQLException;
	public List<Incident> listIncidents(int officerIdToListIncidents)
			throws ClassNotFoundException, SQLException;
}
