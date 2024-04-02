package com.tests;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.enums.Status;

import org.junit.Assert;
import org.junit.Test;

import com.enums.IncidentType;
import com.exceptions.IncidentNotFoundException;
import com.exceptions.InvalidOfficerDataException;
import com.exceptions.OfficerNotFoundException;
import com.model.Incident;
import com.model.Officer;
import com.service.OfficerService;

public class OfficerServiceTest {
	
	@Test
	public void insertOfficerTest() {
		OfficerService officerService=new OfficerService();
		Officer officer=new Officer("abhishek","gupta","ESG765",19,"9900076655",3);
		/* insertion success usecase1 */
		try {
			Assert.assertEquals(1, officerService.insertOfficer(officer));
		} catch (ClassNotFoundException | SQLException | InvalidOfficerDataException e) { }
		
		/* firstName empty usecase2 */
		officer.setFirstName("");
		try {
			Assert.assertEquals(1, officerService.insertOfficer(officer));
		} catch (ClassNotFoundException | SQLException | InvalidOfficerDataException e) { 
			Assert.assertEquals("FirstName can not be empty".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* lastName empty usecase3 */
		officer.setFirstName("abhishek");
		officer.setLastName("");
		try {
			Assert.assertEquals(1, officerService.insertOfficer(officer));
		} catch (ClassNotFoundException | SQLException | InvalidOfficerDataException e) { 
			Assert.assertEquals("LastName can not be empty".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		
		/* invalid rank usecase4 */
		officer.setLastName("gupta");
		officer.setRank(-1);
		try {
			Assert.assertEquals(1, officerService.insertOfficer(officer));
		} catch (ClassNotFoundException | SQLException | InvalidOfficerDataException e) { 
			Assert.assertEquals("invalid rank".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* invalid badge number usecase5 */
		officer.setRank(20);
		officer.setBadgeNumber("");
		try {
			Assert.assertEquals(1, officerService.insertOfficer(officer));
		} catch (ClassNotFoundException | SQLException | InvalidOfficerDataException e) { 
			Assert.assertEquals("badgeNumber can not be null".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* invalid phone number usecase6 */
		officer.setBadgeNumber("EVC123");
		officer.setPhoneNumber("997788");
		try {
			Assert.assertEquals(1, officerService.insertOfficer(officer));
		} catch (ClassNotFoundException | SQLException | InvalidOfficerDataException e) { 
			Assert.assertEquals("invalid phone number".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* invalid agency id usecase7 */
		officer.setPhoneNumber("9977886688");
		officer.setAgencyId(11);
		try {
			Assert.assertEquals(1, officerService.insertOfficer(officer));
		} catch (ClassNotFoundException | SQLException | InvalidOfficerDataException e) { 
			Assert.assertEquals("invalid agency id".toUpperCase(), e.getMessage().toUpperCase());
		}
	}
	
	@Test
	public void validateOfficerTest(){
		OfficerService officerService=new OfficerService();
		List<Officer> officers=new ArrayList<>();
		Officer officer1=new Officer(1,"abhishek","gupta","ESG765",19,"9900076655",3);
		officers.add(officer1);
		Officer officer2=new Officer(2,"abhishek","sharma","ESG765",19,"9900076655",3);
		officers.add(officer2);
		Officer officer3=new Officer(3,"eesha","gupta","ESG765",19,"9900076655",3);
		officers.add(officer3);
		
		/* officer valid usecase1 */
		try {
			Assert.assertEquals(true,officerService.validateOfficer(officers, 2));
		} catch (OfficerNotFoundException e) { }
		
		/* officer invalid usecase2 */
		try {
			Assert.assertEquals(true,officerService.validateOfficer(officers, 7));
		} catch (OfficerNotFoundException e) {
			Assert.assertEquals("Invalid officerId".toUpperCase(), e.getMessage().toUpperCase());
		}

	}
	
	@Test
	public void validateIncidentTest() {
		OfficerService officerService=new OfficerService();
		List<Incident> incidents=new ArrayList<>();
		Incident incident1=new Incident(1,IncidentType.valueOf("Homicide".toUpperCase()), LocalDate.parse("2024-02-20"),"vzm","murder by a man",Status.valueOf("open".toUpperCase()), 1);
		Incident incident2=new Incident(2,IncidentType.valueOf("Homicide".toUpperCase()), LocalDate.parse("2024-02-10"),"vzm","murder by a woman",Status.valueOf("open".toUpperCase()), 2);
		Incident incident3=new Incident(3,IncidentType.valueOf("robbery".toUpperCase()), LocalDate.parse("2024-03-30"),"vzm","bank roberry",Status.valueOf("open".toUpperCase()), 1);
		incidents.add(incident1);incidents.add(incident2);incidents.add(incident3);
		
		/* incident valid usecase1 */
		try {
			Assert.assertEquals(true,officerService.validateIncident(incidents, 2));
		} catch (IncidentNotFoundException e) { }
		
		/* incident invalid usecase2 */
		try {
			Assert.assertEquals(true,officerService.validateIncident(incidents, 7));
		} catch (IncidentNotFoundException e) {
			Assert.assertEquals("invalid incidentId".toUpperCase(), e.getMessage().toUpperCase());
		}
	}
	
	@Test
	public void deleteOfficerTest() {
		
		OfficerService officerService=new OfficerService();
		
		/* deleting officer usecase1 */
		try {
			Assert.assertEquals(0, officerService.deleteOfficer(24));
		} catch (ClassNotFoundException | SQLException e) {
			
		}
	}
	
	@Test
	public void assignOfficerToIncidentTest() {
		
		OfficerService officerService=new OfficerService();
		
		/*assigning officer who exists usecase1 */
		try {
			Assert.assertEquals(1, officerService.assignOfficerToIncident(2,1));
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		
		/*assigning officer who does not exists usecase2 */
		try {
			Assert.assertEquals(0, officerService.assignOfficerToIncident(2,50));
		} catch (ClassNotFoundException | SQLException e) {
			
		}
	}

}
