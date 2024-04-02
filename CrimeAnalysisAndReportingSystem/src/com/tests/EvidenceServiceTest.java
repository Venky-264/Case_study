package com.tests;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.exceptions.InvalidEvidenceException;
import com.model.Evidence;
import com.service.EvidenceService;

public class EvidenceServiceTest {

	@Test
	public void assignEvidenceToIncident() {
		
		EvidenceService evidenceService=new EvidenceService();
		Evidence evidence=new Evidence("eye evidence by x person"," Near Mumbai Railway Station",1);
		
		/* assigned evidence usecase1 */
		try {
			Assert.assertEquals(1, evidenceService.assignEvidenceToIncident(evidence));
		} catch (ClassNotFoundException | SQLException | InvalidEvidenceException e) {
			
		}
		
		/* empty description usecase2 */
		evidence.setDescription("");
		try {
			Assert.assertEquals(1, evidenceService.assignEvidenceToIncident(evidence));
		} catch (ClassNotFoundException | SQLException | InvalidEvidenceException e) {
			Assert.assertEquals("description can not be empty".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* invalid location usecase3 */
		evidence.setDescription("eye evidence by x person");
		evidence.setLocation("");
		try {
			Assert.assertEquals(1, evidenceService.assignEvidenceToIncident(evidence));
		} catch (ClassNotFoundException | SQLException | InvalidEvidenceException e) {
			Assert.assertEquals("location can not be empty".toUpperCase(), e.getMessage().toUpperCase());
		}
		
	}
}
