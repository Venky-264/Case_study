package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.enums.IncidentType;
import com.enums.Status;
import com.exceptions.IncidentNotFoundException;
import com.exceptions.InvalidIncidentDataException;
import com.exceptions.OfficerNotFoundException;
import com.model.Incident;
import com.model.Officer;
import com.model.Report;
import com.service.IncidentService;
import com.service.OfficerService;
import com.service.ReportService;

public class IncidentController {

	public static void main(String[] args) {

		IncidentService incidentService = new IncidentService();

		Scanner sc = new Scanner(System.in);
		OfficerService officerService=new OfficerService();
		
		ReportService reportService=new ReportService();

		while (true) {
			System.out.println("press 1.for displaying all incidents ");
			System.out.println("press 2.to insert a new incident");
			System.out.println("press 3.for updating the status of a specific incident");
			System.out.println("press 4. for displaying incidents withing specific dates");
			System.out.println("press 5. to search an incident of a specific type");
			System.out.println("press 6. generate report for an incident");
			System.out.println("press 0. to exit...");
			int choice = sc.nextInt();

			if (choice == 0) {
				System.out.println("EXITING....");
				break;
			}
			switch (choice) {
			case 1:
				
				try {
					List<Incident> incidents=incidentService.getAllIncidents();
					if(incidents.isEmpty()) {
						System.out.println("No incidents present");
						continue;
					}
					System.out.println("ID  IncidentType   IncidentDate   Loaction   Status    officerId");
					for(Incident i:incidents) {
						System.out.println(i.getIncidentId()+"  "+i.getIncidentType()+"   "+
					i.getIncidentDate()+"    "+i.getLocation()+"     "+i.getStatus()+
					"     "+i.getOfficerId());
					}
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:

				Incident incident = new Incident();
				System.out.println("Choose the Incident Type");
				System.out.println("press 1. For HOMICIDE");
				System.out.println("press 2. For ROBBERY");
				System.out.println("press 3. For THEFT");
				int x = sc.nextInt();
				if(x == 1)
					incident.setIncidentType(IncidentType.HOMICIDE);
				else if(x == 2)
					incident.setIncidentType(IncidentType.ROBBERY);
				else if(x == 3)
					incident.setIncidentType(IncidentType.THEFT);
				else{
					System.out.println("invalid type");
					continue;
				}
				sc.nextLine();
				
				System.out.println("Enter the Incident Location: ");
				incident.setLocation(sc.nextLine());
				System.out.println("Enter  a description of the incident description: ");
				incident.setDescription(sc.nextLine());
				System.out.println("Choose the Status of the Incident/Case");
				System.out.println("press 1. For Open");
				System.out.println("press 2. For Closed");
				System.out.println("press 3. For Investigating");
				System.out.println("press 4. For Pending");
				int y = sc.nextInt();
				if(y == 1)
					incident.setStatus(Status.OPEN);
				else if(y == 2)
					incident.setStatus(Status.CLOSED);
				else if(y == 3)
					incident.setStatus(Status.INVESTIGATION);
				else if(y == 4)
					incident.setStatus(Status.PENDING);
				else{
					System.out.println("invalid status");
					continue;
				}
				
				System.out.println("Enter the Officer In-Charger of the Incident");
				int officerId=sc.nextInt();
				incident.setOfficerId(officerId);
				
				try {
					List<Officer> officers = officerService.fetchAllOfficers();
					officerService.validateOfficer(officers,officerId);
					sc.nextLine();
					System.out.println("enter Incident Date -- formate should be YYYY-MM-DD");
					String date = sc.nextLine();
			        
					LocalDate Incidentdate=LocalDate.parse(date);
					incident.setIncidentDate(Incidentdate);
					incidentService.createIncident(incident);
					System.out.println("incident created successfully");
				} catch (ClassNotFoundException | SQLException | OfficerNotFoundException | InvalidIncidentDataException | DateTimeParseException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 3:
				
				System.out.println("enter the incidentId to get the report");
				int incidentIdToUpdateStatus=sc.nextInt();
				
				
				try {
					List<Incident> incidents = incidentService.getAllIncidents();
					reportService.validateIncident(incidents,incidentIdToUpdateStatus);
					System.out.println("Choose the Status of the Incident/Case to update");
					System.out.println("press 1. For Open");
					System.out.println("press 2. For Closed");
					System.out.println("press 3. For Investigating");
					System.out.println("press 4. For Pending");
					int choose = sc.nextInt();
					
					String status=null;
					
					if(choose == 1)
						status="OPEN";
					else if(choose == 2)
						status="CLOSED";
	
					else if(choose == 3)
						status="INVESTIGATION";
					else if(choose == 4)
						status="PENDING";
					else{
						System.out.println("invalid status");
						continue;
					}
					
					incidentService.UpdateIncident(incidentIdToUpdateStatus,Status.valueOf(status));
					
					System.out.println("status updated successfully");
					
				} catch (ClassNotFoundException | SQLException | IncidentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 4:
				sc.nextLine();
				System.out.println("Enter Start Date: ");
				String fromdate = sc.nextLine();
				System.out.println("Enter End Date: ");
				String todate = sc.nextLine(); 
		        try {
		            LocalDate fromDate = LocalDate.parse(fromdate);
		            LocalDate toDate = LocalDate.parse(todate);
		            List <Incident> incidents = incidentService.getIncidentsInDateRange(fromDate,toDate);
		            if(incidents.isEmpty()) {
						System.out.println("No incidents between this date range");
						continue;
					}
		            for(Incident incidentInRange: incidents)
		            	System.out.println(incidentInRange);
		        }
		        catch(ClassNotFoundException | SQLException | DateTimeParseException e) {
		        	System.out.println(e.getMessage());
		        }
			
				break;
			case 5:
				String incidentType=null;
				System.out.println("choose the Incident type to search by: ");
				System.out.println("Choose the Incident Type");
				System.out.println("press 1. For HOMICIDE");
				System.out.println("press 2. For ROBBERY");
				System.out.println("press 3. For THEFT");
				
				int select=sc.nextInt();
				
				if( select== 1)
					incidentType="HOMICIDE";
				else if(select == 2)
					incidentType="ROBBERY";
				else if(select == 3)
					incidentType="THEFT";
				else{
					System.out.println("invalid type");
					continue;
				} 
				
				try {
					
					List<Incident> incidents = incidentService.searchIncidents(IncidentType.valueOf(incidentType));
					
					
					if(incidents.isEmpty()) {
						System.out.println("No incidents of this type");
						continue;
					}
					
					for(Incident incidentByType : incidents)
						System.out.println(incidentByType);
					
					}catch (ClassNotFoundException | SQLException  e)
					{
						System.out.println(e.getMessage());
					}
				
				break;
			case 6:
				
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
			}
			
		System.out.println("\n");
		}
		sc.close();

	}

}
