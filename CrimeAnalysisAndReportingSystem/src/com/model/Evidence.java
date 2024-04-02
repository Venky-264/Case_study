package com.model;

public class Evidence {

	private int evidenceId;
	private String description;
	private String location;
	private int incidentId;

	public Evidence() { }

	public Evidence(String description, String location, int incidentId) {
		
		this.description = description;
		this.location = location;
		this.incidentId = incidentId;
	}

	public Evidence(int evidenceId, String description, String location, int incidentId) {
		
		this.evidenceId = evidenceId;
		this.description = description;
		this.location = location;
		this.incidentId = incidentId;
	}

	public int getEvidenceId() {
		return evidenceId;
	}

	public void setEvidenceId(int evidenceId) {
		this.evidenceId = evidenceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}

	@Override
	public String toString() {
		return "Evidence [evidenceId=" + evidenceId + ", description=" + description + ", location=" + location
				+ ", incidentId=" + incidentId + "]";
	}
}
