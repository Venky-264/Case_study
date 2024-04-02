package com.tests;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Test;

import com.model.Report;
import com.service.ReportService;

import org.junit.Assert;

import com.enums.*;
import com.exceptions.InvalidReportDataException;

public class ReportServiceTest {
	
	@Test
	public void addReportTest() {
		
		ReportService reportService=new ReportService();
		Report report=new Report();
		report.setReportDate(LocalDate.parse("2024-03-27"));
		report.setReportDetails("investigation done and finalized");
		report.setStatus(Status.valueOf("CLOSED"));
		report.setIncidentId(2);
		/* adding report usecase1 */
		try {
			Assert.assertEquals(1,reportService.addReport(report) );
		} catch (ClassNotFoundException | SQLException | InvalidReportDataException e) {
		}
		
		/* report details  empty usecase1 */
		report.setReportDetails("");
		try {
			Assert.assertEquals(1,reportService.addReport(report) );
		} catch (ClassNotFoundException | SQLException | InvalidReportDataException e) {
			Assert.assertEquals("report details can not be empty".toUpperCase(), e.getMessage().toUpperCase());
		}
		
	}

}
