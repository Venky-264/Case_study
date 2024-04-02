package com.dao;

import java.sql.SQLException;

import java.util.List;

import com.exceptions.InvalidVictimDataException;
import com.exceptions.VictimNotFoundException;
import com.model.Victim;

public interface VictimDao {
	
	public int addRecord( Victim victim) throws ClassNotFoundException, SQLException,InvalidVictimDataException;
	
	public List<Victim> getVictim(int incidentIdForvictim) throws ClassNotFoundException, SQLException ;
	
	public List<Victim> getVictimByName(String name) throws ClassNotFoundException, SQLException;

	public int deleteVictim(int victimId) throws ClassNotFoundException, SQLException, VictimNotFoundException ;
}
