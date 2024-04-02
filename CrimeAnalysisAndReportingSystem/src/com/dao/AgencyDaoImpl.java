package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.InvalidAgencyDataException;
import com.model.Agency;
import com.model.Officer;
import com.utility.DBConnection;

public class AgencyDaoImpl implements AgencyDao {

	@Override
	public List<Agency> getAllAgencies() throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from agencies";

		Statement stmt = conn.createStatement();

		ResultSet result = stmt.executeQuery(query);

		List<Agency> agencies = new ArrayList<>();

		while (result.next()) {
			int agencyId = result.getInt("agency_id");
			String agencyName = result.getString("agency_name");
			String jurisdiction = result.getString("Jurisdiction");
			String phoneNumber = result.getString("contact_number");

			Agency agency = new Agency(agencyId, agencyName, jurisdiction, phoneNumber);

			agencies.add(agency);
		}

		DBConnection.dbClose();

		return agencies;
	}

	@Override
	public int addAgency(Agency agency) throws ClassNotFoundException, SQLException, InvalidAgencyDataException {

		Connection conn = DBConnection.getDBConn();

		String query = "insert into agencies(agency_name,Jurisdiction,contact_number) values(?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(query);

		String agencyName = agency.getAgencyName();
		String jurisdiction = agency.getJurisdiction();
		String phoneNumber = agency.getContactNumber();

		if (agencyName.equals(null) || agencyName.equals(""))
			throw new InvalidAgencyDataException("agency name can not be empty");
		if (jurisdiction.equals(null) || jurisdiction.equals(""))
			throw new InvalidAgencyDataException("invalid jurisdiction");
		if (phoneNumber.equals(null) || phoneNumber.equals("") || phoneNumber.length() != 10)
			throw new InvalidAgencyDataException("invalid phone number");

		stmt.setString(1, agencyName);
		stmt.setString(2, jurisdiction);
		stmt.setString(3, phoneNumber);

		int update = stmt.executeUpdate();

		DBConnection.dbClose();
		return update;
	}

	@Override
	public void assignOfficerToAgency(int officerId, int agencyId) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.getDBConn();

		String query = "update officers set Agencies_agency_id=? where officer_id=?";

		PreparedStatement stmt = conn.prepareStatement(query);

		stmt.setInt(1, agencyId);
		stmt.setInt(2, officerId);

		stmt.executeUpdate();

		DBConnection.dbClose();
	}

	@Override
	public List<Officer> fetchAllOfficers(int agencyIdToGetAllOfficers) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from officers where Agencies_agency_id=?";
		PreparedStatement stmt = conn.prepareStatement(query);

		stmt.setInt(1, agencyIdToGetAllOfficers);
		
		ResultSet result = stmt.executeQuery();
		
		List<Officer> officers=new ArrayList<>();
		
		while(result.next()) {
			int id = result.getInt("officer_id");
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
			String badgeNumber= result.getString("badge_number");
			int rank = result.getInt("rank");
			String contactNumber=result.getString("contact_number");


			Officer o = new Officer();
			o.setOfficerId(id);
			o.setFirstName(firstName);
			o.setLastName(lastName);
			o.setBadgeNumber(badgeNumber);
			o.setRank(rank);
			o.setPhoneNumber(contactNumber);
			
			officers.add(o);
		}

		DBConnection.dbClose();
		return officers;
	}

}
