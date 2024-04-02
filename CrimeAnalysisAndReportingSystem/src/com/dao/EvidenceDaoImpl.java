package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.InvalidEvidenceException;
import com.model.Evidence;
import com.utility.DBConnection;

public class EvidenceDaoImpl implements EvidenceDao {

	@Override
	public int assignEvidenceToIncident(Evidence evidence)
			throws ClassNotFoundException, SQLException, InvalidEvidenceException {
		Connection conn = DBConnection.getDBConn();

		String query = "insert into evidence(description,location,Incidents_incident_id) values(?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(query);

		String description = evidence.getDescription();
		String location = evidence.getLocation();
		int incidentId = evidence.getIncidentId();

		if (description.equals(null) || description.equals(""))
			throw new InvalidEvidenceException("description can not be empty");

		if (location.equals(null) || location.equals(""))
			throw new InvalidEvidenceException("location can not be empty");

		stmt.setString(1, description);
		stmt.setString(2, location);
		stmt.setInt(3, incidentId);

		int update = stmt.executeUpdate();

		DBConnection.dbClose();
		return update;
	}

	@Override
	public List<Evidence> getAllEvidences(int incidentIdForEvidences)
			throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from evidence where Incidents_incident_id=?";

		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, incidentIdForEvidences);
		
		ResultSet result = stmt.executeQuery();
		
		List<Evidence> evidences=new ArrayList<>();
		
		while(result.next()) {
			int evidenceId=result.getInt("evidence_id");
			String description=result.getString("description");
			String location=result.getString("location");
			
			Evidence evidence=new Evidence();
			evidence.setEvidenceId(evidenceId);
			evidence.setDescription(description);
			evidence.setLocation(location);
			
			evidences.add(evidence);
		}

		DBConnection.dbClose();

		return evidences;
	}

}
