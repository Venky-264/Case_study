package com.tests;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.exceptions.InvalidSuspectDataException;
import com.exceptions.SuspectNotFoundException;
import com.model.Suspect;
import com.service.SuspectService;

public class SuspectServiceTest {
	
	
	@Test
	public void addRecordTest() {
		
		SuspectService suspectService=new SuspectService();
		
		Suspect suspect=new Suspect("venky", "ram", LocalDate.parse("2002-12-20"), "male", "9988776655", 3);
		
		/* Suspect added usecase1 */
		try {
			Assert.assertEquals(1, suspectService.addRecord(suspect));
		} catch (ClassNotFoundException | SQLException | InvalidSuspectDataException e) {
		
		}
		
		/* invalid firstName usecase2 */
		suspect.setFirstName("");
		try {
			Assert.assertEquals(1,suspectService.addRecord(suspect));
		} catch (ClassNotFoundException | SQLException | InvalidSuspectDataException e) {
		Assert.assertEquals("Invalid firstName".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* invalid lastName usecase3 */
		suspect.setFirstName("venky");
		suspect.setLastName("");
		try {
			Assert.assertEquals(1, suspectService.addRecord(suspect));
		} catch (ClassNotFoundException | SQLException | InvalidSuspectDataException e) {
		Assert.assertEquals("Invalid lastName".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* invalid gender usecase4 */
		suspect.setLastName("ram");
		suspect.setGender("");
		try {
			Assert.assertEquals(1, suspectService.addRecord(suspect));
		} catch (ClassNotFoundException | SQLException | InvalidSuspectDataException e) {
		Assert.assertEquals("Invalid gender".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* invalid phoneNumber usecase5 */
		suspect.setGender("male");
		suspect.setContactNumber("");
		try {
			Assert.assertEquals(1, suspectService.addRecord(suspect));
		} catch (ClassNotFoundException | SQLException | InvalidSuspectDataException e) {
		Assert.assertEquals("invalid phoneNumber".toUpperCase(), e.getMessage().toUpperCase());
		}
		
	}
	
	@Test
	public void deleteVictimTest() {
		
		SuspectService victimService=new SuspectService();
		
		/* delete suspect which is  present usecase1 */
		try {
			Assert.assertEquals(1, victimService.deleteSuspect(11));
		} catch (ClassNotFoundException | SQLException | SuspectNotFoundException e) {
			
		}
		
		/* delete suspect which is not present usecase2 */
		try {
			Assert.assertEquals(1, victimService.deleteSuspect(34));
		} catch (ClassNotFoundException | SQLException | SuspectNotFoundException e) {
			Assert.assertEquals("Invalid SuspectId".toUpperCase(), e.getMessage().toUpperCase());
		}
	}

}
