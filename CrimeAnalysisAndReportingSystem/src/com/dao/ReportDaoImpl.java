package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.enums.Status;
import com.exceptions.InvalidReportDataException;
import com.model.Report;
import com.utility.DBConnection;

public class ReportDaoImpl implements ReportDao {

	@Override
	public int addReport(Report report) throws ClassNotFoundException, SQLException, InvalidReportDataException {
		
		Connection conn=DBConnection.getDBConn();
		
		String query="insert into reports(report_date,report_details,status,Incidents_incident_id) values(?,?,?,?)";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		
		LocalDate reportDate=report.getReportDate();
		String reportDateToInsert=reportDate.toString();
		//Integer.toString(reportDate.getYear())+"-"+Integer.toString(reportDate.getMonth())+"-"+Integer.toString(reportDate.getDay());
		
		String reportDetails=report.getReportDetails();
		String status=report.getStatus().toString();
		int incidentId=report.getIncidentId();
		
		if(reportDetails.equals(null) || reportDetails.equals(""))
			throw new InvalidReportDataException("report details can not be empty");
		
		
		stmt.setString(1, reportDateToInsert);
		stmt.setString(2,reportDetails);
		stmt.setString(3, status);
		stmt.setInt(4, incidentId);
		
		int update = stmt.executeUpdate();
		
		DBConnection.dbClose();
		return update;
		
	}
	@Override
	public List<Report> fetchReportForId(int id) throws ClassNotFoundException, SQLException {
		
		Connection conn=DBConnection.getDBConn();
		
		String query="select * from reports where Incidents_incident_id =? ";
		
		PreparedStatement pstmt=conn.prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery();
		List<Report> reports =new ArrayList<>();
		
		while(result.next()) {
			int reportId=result.getInt("report_id");
			LocalDate reportDate=LocalDate.parse(result.getDate("report_date").toString());
			String reportDetails=result.getString("report_details");
			String status=result.getString("status");

			Report report = new Report();
			report.setReportId(reportId);
			report.setReportDate(reportDate);
			report.setReportDetails(reportDetails);
			report.setStatus(Status.valueOf(status.toUpperCase()));
			report.setIncidentId(id);
			reports.add(report);
		}
		DBConnection.dbClose();
		return reports;
	}
}
