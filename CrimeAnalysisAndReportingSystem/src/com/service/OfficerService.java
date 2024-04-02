package com.service;

import java.sql.SQLException;
import java.util.List;

import com.exceptions.IncidentNotFoundException;
import com.exceptions.InvalidOfficerDataException;
import com.exceptions.OfficerNotFoundException;
import com.dao.OfficerDao;
import com.dao.OfficerDaoImpl;
import com.model.Incident;
import com.model.Officer;

public class OfficerService {

	public List<Officer> fetchAllOfficers()
			throws ClassNotFoundException, SQLException {

		OfficerDao dao = new OfficerDaoImpl();
		return dao.fetchAllOfficers();
	}

	public int insertOfficer(Officer officer)
			throws ClassNotFoundException, SQLException, InvalidOfficerDataException {
		OfficerDao dao = new OfficerDaoImpl();

		return dao.insertOfficer(officer);

	}

	public boolean validateOfficer(List<Officer> officers, int officerId)
			throws OfficerNotFoundException {

		boolean validate = false;

		for (Officer o : officers) {
			if (o.getOfficerId() == officerId)
				validate = true;
		}
		if (!validate)
			throw new OfficerNotFoundException("Invalid officerId");
		return validate;
	}

	public boolean validateIncident(List<Incident> allIncidents, int incidentId)
			throws IncidentNotFoundException {
		boolean validate = false;

		for (Incident i : allIncidents) {
			if (i.getIncidentId()==incidentId)
				validate = true;
		}
		if (!validate)
			throw new IncidentNotFoundException("invalid incidentId");
		return validate;
	}

	public int assignOfficerToIncident(int officerId, int incidentId)
			throws ClassNotFoundException, SQLException {
		OfficerDao dao = new OfficerDaoImpl();
		return dao.assignOfficerToIncident(officerId,incidentId);
		
	}

	public int deleteOfficer(int officerIdToDelete)
			throws ClassNotFoundException, SQLException {
		
		OfficerDao dao = new OfficerDaoImpl();
		return dao.deleteOfficer(officerIdToDelete);
	}

	public List<Incident> listIncidents(int officerIdToListIncidents)
			throws ClassNotFoundException, SQLException {
		
		OfficerDao dao = new OfficerDaoImpl();
		return dao.listIncidents(officerIdToListIncidents);
		
	}

}
