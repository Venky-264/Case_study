package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exceptions.InvalidEvidenceException;
import com.model.Evidence;

public interface EvidenceDao {

	public int assignEvidenceToIncident(Evidence evidence) 
			 throws ClassNotFoundException, SQLException,InvalidEvidenceException;

	public List<Evidence> getAllEvidences(int incidentIdForEvidences)
			throws ClassNotFoundException, SQLException ;

}
