package com.tests;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.enums.IncidentType;
import com.enums.Status;
import com.exceptions.InvalidIncidentDataException;
import com.model.Incident;
import com.service.IncidentService;

public class IncidentServiceTest {
	
	@Test
	public void createIncidentTest() {
		
		IncidentService incidentService=new IncidentService();
		
		Incident incident=new Incident(IncidentType.valueOf("THEFT"),LocalDate.parse("2024-02-25"),"Near mumbai railway station","The whole jwellery and the money was taken away",Status.valueOf("OPEN"),3);
		
		/* incident creation usecase1 */
		try {
			Assert.assertEquals(1,incidentService.createIncident(incident) );
		} catch (ClassNotFoundException | SQLException | InvalidIncidentDataException e) {
			
		}
		
		/* Invalid location usecase2 */
		incident.setLocation("");
		try {
			Assert.assertEquals(1, incidentService.createIncident(incident));
		} catch (ClassNotFoundException | SQLException | InvalidIncidentDataException e) {
			Assert.assertEquals("location cant be empty".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* Invalid description usecase3 */
		incident.setLocation("Near mumbai railway station");
		incident.setDescription("");
		try {
			Assert.assertEquals(1, incidentService.createIncident(incident));
		} catch (ClassNotFoundException | SQLException | InvalidIncidentDataException e) {
			Assert.assertEquals("description cant be empty".toUpperCase(), e.getMessage().toUpperCase());
		}
		
	}
	
	@Test
	public void UpdateIncidentTest() {
		
		IncidentService incidentService=new IncidentService();
		
		/* updating done usecase1 */
		try {
			Assert.assertEquals(1, incidentService.UpdateIncident(1,Status.valueOf("CLOSED")));
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		
		/* updating not done usecase2 */
		try {
			Assert.assertEquals(0, incidentService.UpdateIncident(20,Status.valueOf("CLOSED")));
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		
	}

}


