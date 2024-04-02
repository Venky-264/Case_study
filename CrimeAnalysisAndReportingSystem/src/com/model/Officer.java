package com.model;

public class Officer {

	private int officerId;
	private String firstName;
	private String lastName;
	private String badgeNumber;
	private int rank;
	private String phoneNumber;
	private int agencyId;
	
	public Officer() {
		
	}
	
	public Officer(String firstName, String lastName, String badgeNumber, int rank, String phoneNumber, int agencyId) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.badgeNumber = badgeNumber;
		this.rank = rank;
		this.phoneNumber = phoneNumber;
		this.agencyId = agencyId;
	}
	
	public Officer(int officerId, String firstName, String lastName, String badgeNumber, int rank, String phoneNumber,
			int agencyId) {
		
		this.officerId = officerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.badgeNumber = badgeNumber;
		this.rank = rank;
		this.phoneNumber = phoneNumber;
		this.agencyId = agencyId;
	}
	
	public int getOfficerId() {
		return officerId;
	}
	public void setOfficerId(int officerId) {
		this.officerId = officerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBadgeNumber() {
		return badgeNumber;
	}
	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}
	@Override
	public String toString() {
		return "Officer [officerId=" + officerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", badgeNumber=" + badgeNumber + ", rank=" + rank + ", phoneNumber=" + phoneNumber + ", agencyId="
				+ agencyId + "]";
	}
	
}
