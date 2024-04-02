package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exceptions.InvalidAgencyDataException;
import com.model.Agency;
import com.model.Officer;

public interface AgencyDao {

	public List<Agency> getAllAgencies()
			throws ClassNotFoundException, SQLException;
	
	public int addAgency(Agency agency)
			throws ClassNotFoundException, SQLException,InvalidAgencyDataException;

	public void assignOfficerToAgency(int officerId, int agencyId)
			throws ClassNotFoundException, SQLException;

	public List<Officer> fetchAllOfficers(int agencyIdToGetAllOfficers)  
			throws ClassNotFoundException, SQLException;

	

	

}
