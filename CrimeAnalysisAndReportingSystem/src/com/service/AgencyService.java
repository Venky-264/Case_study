package com.service;

import java.sql.SQLException;
import java.util.List;

import com.exceptions.AgencyNotFoundException;
import com.exceptions.InvalidAgencyDataException;
import com.exceptions.OfficerNotFoundException;
import com.dao.AgencyDao;
import com.dao.AgencyDaoImpl;
import com.model.Agency;
import com.model.Officer;

public class AgencyService {

	public List<Agency> getAllAgencies() throws ClassNotFoundException, SQLException {

		AgencyDao dao = new AgencyDaoImpl();
		return dao.getAllAgencies();

	}

	public int addAgency(Agency agency) throws ClassNotFoundException, SQLException, InvalidAgencyDataException {

		AgencyDao dao = new AgencyDaoImpl();
		return dao.addAgency(agency);
	}

	public boolean validateAgency(List<Agency> agencies, int agencyId) throws AgencyNotFoundException {

		boolean validate = false;

		for (Agency a : agencies) {
			if (a.getAgencyId() == agencyId)
				validate = true;
		}

		if (!validate)
			throw new AgencyNotFoundException("Inavlid agency id");
		return validate;
	}

	public void validateOfficer(List<Officer> allOfficers, int officerId) throws OfficerNotFoundException {

		boolean validate = false;

		for (Officer o : allOfficers) {
			if (o.getOfficerId() == officerId)
				validate = true;
		}

		if (!validate)
			throw new OfficerNotFoundException("invalid officerId");
	}

	public void assignOfficerToAgency(int officerId, int agencyId)
			throws ClassNotFoundException, SQLException {
		
		AgencyDao dao = new AgencyDaoImpl();
		dao.assignOfficerToAgency(officerId,agencyId);
		
	}

	public List<Officer> fetchAllOfficers(int agencyIdToGetAllOfficers) 
			throws ClassNotFoundException, SQLException {
		
		AgencyDao dao = new AgencyDaoImpl();
		return dao.fetchAllOfficers(agencyIdToGetAllOfficers);
	}

}
