package com.service;


import java.sql.SQLException;

import java.util.List;

import com.dao.VictimDao;
import com.dao.VictimDaoImpl;
import com.exceptions.InvalidVictimDataException;
import com.exceptions.VictimNotFoundException;
import com.model.Victim;

public class VictimService {
	
	VictimDao dao=new VictimDaoImpl();

	public int addRecord(Victim victim) throws ClassNotFoundException, SQLException, InvalidVictimDataException {
		
		return dao.addRecord(victim);
	}
	
	public int deleteVictim(int victimId) throws ClassNotFoundException, SQLException, VictimNotFoundException {
		return dao.deleteVictim(victimId);
		
	}

	public List<Victim> getVictim(int incidentIdForvictim) throws ClassNotFoundException, SQLException {
		return dao.getVictim(incidentIdForvictim);
	}

	public List<Victim> getVictimByName(String name) throws ClassNotFoundException, SQLException {
		
		return dao.getVictimByName(name);
	}

	

	
	
}
