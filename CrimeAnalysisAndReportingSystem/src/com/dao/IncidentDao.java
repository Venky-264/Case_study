package com.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.enums.IncidentType;
import com.enums.Status;
import com.exceptions.InvalidIncidentDataException;
import com.model.Incident;

public interface  IncidentDao {

	public List<Incident> getAllIncidents() throws ClassNotFoundException, SQLException;

	public int createIncident(Incident incident)
			 throws ClassNotFoundException, SQLException,InvalidIncidentDataException;

	public List<Incident> getIncidentsInDateRange(LocalDate from, LocalDate to)
			throws ClassNotFoundException, SQLException ;

	public List<Incident> searchIncidents(IncidentType criteria) 
			 throws ClassNotFoundException, SQLException;

	public int UpdateIncident(int incidentIdToUpdateStatus, Status status)
			throws ClassNotFoundException, SQLException;

}
