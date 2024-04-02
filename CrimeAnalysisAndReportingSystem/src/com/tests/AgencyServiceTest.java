package com.tests;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exceptions.AgencyNotFoundException;
import com.exceptions.InvalidAgencyDataException;
import com.model.Agency;
import com.service.AgencyService;

public class AgencyServiceTest {
	
	@Test
	public void addAgencyTest() {
		
		AgencyService agencyservice=new AgencyService();
		Agency agency=new Agency("FBI-bangalore","bangalore","9988776655");
		
		/* Adding agency usecase1 */
		try {
			Assert.assertEquals(1, agencyservice.addAgency(agency));
		} catch (ClassNotFoundException | SQLException | InvalidAgencyDataException e) {
		
		}
		
		/* agency name empty usecase2 */
		agency.setAgencyName("");
		try {
			Assert.assertEquals(1, agencyservice.addAgency(agency));
		} catch (ClassNotFoundException | SQLException | InvalidAgencyDataException e) {
		Assert.assertEquals("agency name can not be empty".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* Jurisdiction empty usecase3 */
		agency.setAgencyName("FBI-bangalore");
		agency.setJurisdiction("");
		try {
			Assert.assertEquals(1, agencyservice.addAgency(agency));
		} catch (ClassNotFoundException | SQLException | InvalidAgencyDataException e) {
		Assert.assertEquals("invalid jurisdiction".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* invalid phone number usecase3 */
		
		agency.setJurisdiction("bangalore");
		agency.setContactNumber("978088");
		try {
			Assert.assertEquals(1, agencyservice.addAgency(agency));
		} catch (ClassNotFoundException | SQLException | InvalidAgencyDataException e) {
		Assert.assertEquals("invalid phone number".toUpperCase(), e.getMessage().toUpperCase());
		}
	
	}
	
	@Test
	public void validateAgencyTest() {
		
		AgencyService agencyservice=new AgencyService();
		List<Agency> agencies=new ArrayList<>();
		Agency agency1=new Agency(1,"FBI-bangalore","bangalore","9988776655");
		Agency agency2=new Agency(2,"FBI-mumbai","mumbai","9988776655");
		Agency agency3=new Agency(3,"FBI-hyderabad","hyderabad","9988776655");
		agencies.add(agency1);agencies.add(agency2);agencies.add(agency3);
		
		/* valid agency id usecase1 */
		try {
			Assert.assertEquals(true, agencyservice.validateAgency(agencies, 1));
		} catch (AgencyNotFoundException e) {
			
		}
		
		/* invalid agency id usecase2 */
		try {
			Assert.assertEquals(true, agencyservice.validateAgency(agencies, 1));
		} catch (AgencyNotFoundException e) {
			Assert.assertEquals("Inavlid agency id".toUpperCase(), e.getMessage().toUpperCase());
		}
		
	}
	
	

}
