package com.tests;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Test;

import com.exceptions.InvalidVictimDataException;
import com.exceptions.VictimNotFoundException;
import com.model.Victim;
import com.service.VictimService;

import org.junit.*;

public class VictimServiceTest {
	
	@Test
	public void addRecordTest() {
		
		VictimService victimService=new VictimService();
		
		Victim victim=new Victim("venky", "ram", LocalDate.parse("2002-12-20"), "male", "9988776655", 3);
		
		/* victim added usecase1 */
		try {
			Assert.assertEquals(1, victimService.addRecord(victim));
		} catch (ClassNotFoundException | SQLException | InvalidVictimDataException e) {
		
		}
		
		/* invalid firstName usecase2 */
		victim.setFirstName("");
		try {
			Assert.assertEquals(1, victimService.addRecord(victim));
		} catch (ClassNotFoundException | SQLException | InvalidVictimDataException e) {
		Assert.assertEquals("Invalid firstName".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* invalid lastName usecase3 */
		victim.setFirstName("venky");
		victim.setLastName("");
		try {
			Assert.assertEquals(1, victimService.addRecord(victim));
		} catch (ClassNotFoundException | SQLException | InvalidVictimDataException e) {
		Assert.assertEquals("Invalid lastName".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* invalid gender usecase4 */
		victim.setLastName("ram");
		victim.setGender("");
		try {
			Assert.assertEquals(1, victimService.addRecord(victim));
		} catch (ClassNotFoundException | SQLException | InvalidVictimDataException e) {
		Assert.assertEquals("Invalid gender".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* invalid phoneNumber usecase5 */
		victim.setGender("male");
		victim.setContactNumber("");
		try {
			Assert.assertEquals(1, victimService.addRecord(victim));
		} catch (ClassNotFoundException | SQLException | InvalidVictimDataException e) {
		Assert.assertEquals("invalid phoneNumber".toUpperCase(), e.getMessage().toUpperCase());
		}
		
	}
	
	@Test
	public void deleteVictimTest() {
		
		VictimService victimService=new VictimService();
		
		/* delete victim which is  present usecase1 */
		try {
			Assert.assertEquals(1, victimService.deleteVictim(14));
		} catch (ClassNotFoundException | SQLException | VictimNotFoundException e) {
			
		}
		/* delete victim which is not present usecase2 */
		try {
			Assert.assertEquals(1, victimService.deleteVictim(34));
		} catch (ClassNotFoundException | SQLException | VictimNotFoundException e) {
			Assert.assertEquals("Invalid victimId".toUpperCase(), e.getMessage().toUpperCase());
		}
	}

}
