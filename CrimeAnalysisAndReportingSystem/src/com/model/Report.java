package com.model;

import java.time.LocalDate;

import com.enums.Status;

public class Report {

	private int reportId;
	private LocalDate reportDate;
	private String reportDetails;
	private Status status;
	private int incidentId;
	
	public Report() { }

	public Report(LocalDate reportDate, String reportDetails, Status status, int incidentId) {
	
		this.reportDate = reportDate;
		this.reportDetails = reportDetails;
		this.status = status;
		this.incidentId = incidentId;
	}

	public Report(int reportId, LocalDate reportDate, String reportDetails, Status status, int incidentId) {
		
		this.reportId = reportId;
		this.reportDate = reportDate;
		this.reportDetails = reportDetails;
		this.status = status;
		this.incidentId = incidentId;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public LocalDate getReportDate() {
		return reportDate;
	}

	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportDetails() {
		return reportDetails;
	}

	public void setReportDetails(String reportDetails) {
		this.reportDetails = reportDetails;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}

	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", reportDate=" + reportDate + ", reportDetails=" + reportDetails
				+ ", status=" + status + ", incidentId=" + incidentId + "]";
	}
}
