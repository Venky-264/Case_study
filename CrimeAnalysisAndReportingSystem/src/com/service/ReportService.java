package com.service;

import java.sql.SQLException;
import java.util.List;

import com.exceptions.IncidentNotFoundException;
import com.exceptions.InvalidReportDataException;
import com.dao.ReportDao;
import com.dao.ReportDaoImpl;
import com.model.Incident;
import com.model.Report;

public class ReportService {

	public void validateIncident(List<Incident> incidents, int incidentId) throws IncidentNotFoundException {
		boolean validate = false;

		for (Incident i : incidents) {
			if (i.getIncidentId() == incidentId)
				validate = true;
		}
		if (!validate)
			throw new IncidentNotFoundException("Invalid incidentId");
	}

	public int  addReport(Report report)
			throws ClassNotFoundException, SQLException, InvalidReportDataException {
		
		ReportDao dao=new ReportDaoImpl();
		return dao.addReport(report);
		
	}

	public List<Report> fetchReportForId(int incidentIdForDetailedReport) throws ClassNotFoundException, SQLException {
		ReportDao dao = new ReportDaoImpl();
		return dao.fetchReportForId(incidentIdForDetailedReport);
	}

}
