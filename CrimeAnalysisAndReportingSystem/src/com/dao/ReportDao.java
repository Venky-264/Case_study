package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exceptions.InvalidReportDataException;
import com.model.Report;

public interface ReportDao {

	public int addReport(Report report)
			throws ClassNotFoundException, SQLException ,InvalidReportDataException;

	public List<Report> fetchReportForId(int incidentIdForDetailedReport)
			throws ClassNotFoundException, SQLException;

}
