package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.enums.Status;

import com.exceptions.IncidentNotFoundException;
import com.exceptions.InvalidReportDataException;
import com.model.Incident;
import com.model.Report;
import com.service.IncidentService;
import com.service.ReportService;

public class ReportController {

	public static void main(String[] args) {
		
	ReportService reportService=new ReportService();
	
	Scanner sc=new Scanner(System.in);
	
	IncidentService incidentService = new IncidentService();
	
	while(true) {
		
		System.out.println("press 1.to add report to an incident");
		System.out.println("press 2.to diaplay detailed report for an incident");
		System.out.println("press 0. to exit...");
		int choice = sc.nextInt();

		if (choice == 0) {
			System.out.println("EXITING....");
			break;
		}
		System.out.println("\n");
		
		switch(choice) {
		case 1:
			System.out.println("enter the incidentId to which the report added");
			int incidentId=sc.nextInt();
			
			try {
				List<Incident> incidents=incidentService.getAllIncidents();
				reportService.validateIncident(incidents,incidentId);
				LocalDate presentDate=LocalDate.now();
				sc.nextLine();
				System.out.println("enter the report Details");
				String reportDetails=sc.nextLine();
				System.out.println("enter the status");
				String status=sc.nextLine();
				if(!status.equalsIgnoreCase("OPEN") && !status.equalsIgnoreCase("CLOSED") && !status.equalsIgnoreCase("INVESTIGATION") && !status.equalsIgnoreCase("PENDING"))
					throw new InvalidReportDataException("invalid status");
				Report report=new Report(presentDate, reportDetails,Status.valueOf(status.toUpperCase()), incidentId);
				reportService.addReport(report);
				System.out.println("report added successfully");
				
			} catch (ClassNotFoundException | SQLException | IncidentNotFoundException | InvalidReportDataException e) {
				System.out.println(e.getMessage());
			}
			
			break;
		case 2:
			System.out.println("enter the incidentId to get the report");
			int incidentIdForDetailedReport=sc.nextInt();
			
			
			try {
				List<Incident> incidents = incidentService.getAllIncidents();
				reportService.validateIncident(incidents,incidentIdForDetailedReport);
				List <Report> list = reportService.fetchReportForId(incidentIdForDetailedReport);
				System.out.println("ID  Report_Date   Report_Details                                           Status    Incident_Id");
				for(Report r:list) 
				{
					System.out.println(r.getReportId()+"  "+r.getReportDate()+"   "+r.getReportDetails()+"    "+
				r.getStatus()+"     "+r.getIncidentId());
				}
				
				
			} catch (ClassNotFoundException | SQLException | IncidentNotFoundException e) {
				System.out.println(e.getMessage());
			}
			
			
			break;
		default:
			System.out.println("invalid option");
			break;
		}
	}
	
	sc.close();
	}

}
