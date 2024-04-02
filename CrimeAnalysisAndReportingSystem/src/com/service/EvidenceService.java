package com.service;

import java.sql.SQLException;
import java.util.List;

import com.exceptions.IncidentNotFoundException;
import com.exceptions.InvalidEvidenceException;
import com.dao.EvidenceDao;
import com.dao.EvidenceDaoImpl;
import com.model.Evidence;
import com.model.Incident;

public class EvidenceService {

	public void validateIncident(List<Incident> incidents, int incidentId)
			throws IncidentNotFoundException {
		boolean validate=false;
		
		for(Incident i:incidents) {
			if(i.getIncidentId()==incidentId)
				validate=true;
		}
		if(!validate)
			throw new IncidentNotFoundException("Invalid incidentId");
	}

	public int assignEvidenceToIncident(Evidence evidence) 
			throws ClassNotFoundException, SQLException, InvalidEvidenceException {
		
		EvidenceDao dao=new EvidenceDaoImpl();
		return dao.assignEvidenceToIncident(evidence);
		
	}

	public List<Evidence> getAllEvidences(int incidentIdForEvidences)
			throws ClassNotFoundException, SQLException {
		
		EvidenceDao dao=new EvidenceDaoImpl();
		return dao.getAllEvidences(incidentIdForEvidences);
	}

}
