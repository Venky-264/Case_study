
package com.service;


import java.sql.SQLException;

import java.util.List;

import com.dao.SuspectDao;
import com.dao.SuspectDaoImpl;
import com.exceptions.InvalidSuspectDataException;
import com.exceptions.SuspectNotFoundException;
import com.model.Suspect;

public class SuspectService{
	
	SuspectDao dao=new SuspectDaoImpl();

	public int addRecord(Suspect suspect) throws ClassNotFoundException, SQLException, InvalidSuspectDataException {
		
		return dao.addRecord(suspect);
	}
	
	public int deleteSuspect(int suspectId) throws ClassNotFoundException, SQLException, SuspectNotFoundException {
		return dao.deleteSuspect(suspectId);
		
	}

	public List<Suspect> getSuspect(int incidentIdForSuspect) throws ClassNotFoundException, SQLException {
		return dao.getSuspect(incidentIdForSuspect);
	}

	public List<Suspect> getSuspectByName(String name) throws ClassNotFoundException, SQLException {
		
		return dao.getSuspectByName(name);
	}

	

	
	
}
