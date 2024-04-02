
package com.dao;

import java.sql.SQLException;

import java.util.List;

import com.exceptions.InvalidSuspectDataException;
import com.exceptions.SuspectNotFoundException;
import com.model.Suspect;

public interface SuspectDao  {
	
	public int addRecord( Suspect suspect) throws ClassNotFoundException, SQLException,InvalidSuspectDataException;
	
	public List<Suspect> getSuspect(int incidentIdForSuspect) throws ClassNotFoundException, SQLException ;
	
	public List<Suspect> getSuspectByName(String name) throws ClassNotFoundException, SQLException;

	public int deleteSuspect(int suspectId) throws ClassNotFoundException, SQLException, SuspectNotFoundException ;
}
