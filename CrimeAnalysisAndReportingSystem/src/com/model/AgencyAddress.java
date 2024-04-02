package com.model;

public class AgencyAddress {

	private int addressId;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private int agencyId;
	
	
	
	public AgencyAddress() { }



	public AgencyAddress(String city, String state, String country, int pincode, int agencyId) {
	
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.agencyId = agencyId;
	}



	public AgencyAddress(int addressId, String city, String state, String country, int pincode, int agencyId) {
		
		this.addressId = addressId;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.agencyId = agencyId;
	}



	public int getAddressId() {
		return addressId;
	}



	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public int getPincode() {
		return pincode;
	}



	public void setPincode(int pincode) {
		this.pincode = pincode;
	}



	public int getAgencyId() {
		return agencyId;
	}



	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}



	@Override
	public String toString() {
		return "AgencyAddress [addressId=" + addressId + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", pincode=" + pincode + ", agencyId=" + agencyId + "]";
	}
}
