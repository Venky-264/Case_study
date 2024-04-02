package com.model;

public class Agency {

	private int agencyId;
	private String agencyName;
	private String jurisdiction;
	private String contactNumber;
	
	public Agency(int agencyId, String agencyName, String jurisdiction, String contactNumber) {
		
		this.agencyId = agencyId;
		this.agencyName = agencyName;
		this.jurisdiction = jurisdiction;
		this.contactNumber = contactNumber;
	}
	public Agency(String agencyName, String jurisdiction, String contactNumber) {
		
		this.agencyName = agencyName;
		this.jurisdiction = jurisdiction;
		this.contactNumber = contactNumber;
	}
	public Agency() {
		
	}
	public int getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Override
	public String toString() {
		return "Agency [agencyId=" + agencyId + ", agencyName=" + agencyName + ", jurisdiction=" + jurisdiction
				+ ", contactNumber=" + contactNumber + "]";
	}
	
	
}
