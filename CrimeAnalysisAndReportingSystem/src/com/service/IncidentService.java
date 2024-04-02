package com.service;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.dao.IncidentDao;
import com.dao.IncidentDaoImpl;
import com.enums.IncidentType;
import com.enums.Status;
import com.exceptions.InvalidIncidentDataException;
import com.model.Incident;

public class IncidentService {

	public List<Incident> getAllIncidents() throws ClassNotFoundException, SQLException {
		
		IncidentDao dao=new IncidentDaoImpl();
		
		return dao.getAllIncidents();
	}
	
	public int createIncident(Incident incident) throws ClassNotFoundException, SQLException, InvalidIncidentDataException {
		IncidentDao dao = new IncidentDaoImpl();
		return dao.createIncident(incident);
		
	}
	
	public List<Incident> getIncidentsInDateRange(LocalDate from, LocalDate to)
			throws ClassNotFoundException, SQLException
	{
		IncidentDao dao = new IncidentDaoImpl();
		return dao.getIncidentsInDateRange(from, to);
	}
	
	public List<Incident> searchIncidents(IncidentType criteria)throws ClassNotFoundException, SQLException
	{
		IncidentDao dao = new IncidentDaoImpl();
		return dao.searchIncidents(criteria);
	}

	public int UpdateIncident(int incidentIdToUpdateStatus, Status status) throws ClassNotFoundException, SQLException {
		
		IncidentDao dao = new IncidentDaoImpl();
		return dao.UpdateIncident(incidentIdToUpdateStatus,status);
	}

}
