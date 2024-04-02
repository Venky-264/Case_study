package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exceptions.IncidentNotFoundException;
import com.exceptions.InvalidOfficerDataException;
import com.exceptions.OfficerNotFoundException;
import com.model.Incident;
import com.model.Officer;
import com.service.IncidentService;
import com.service.OfficerService;

public class OfficerController {

	public static void main(String[] args) {
		//OfficerController with all operations
	
		OfficerService officerService=new OfficerService();
		
		Scanner sc=new Scanner(System.in);
		
		while (true) {
			System.out.println("press 1.for displaying all Officers ");
			System.out.println("press 2.to insert a new Officer with a rank");
			System.out.println("press 3.to assign officer to an incident");
			System.out.println("press 4. for deleting an officer");
			System.out.println("press 5. for displaying all incidents for an officer");
			System.out.println("press 0. to exit...");
			int choice = sc.nextInt();

			if (choice == 0) {
				System.out.println("EXITING....");
				break;
			}
			switch (choice) {
			case 1:
				try {
					List<Officer> officers=officerService.fetchAllOfficers();
					System.out.println("Id  fName   lname   badgeNUmber    rank    phoneNumber    agencyId");
					for(Officer o:officers) {
						System.out.println(o.getOfficerId()+"    "+o.getFirstName()+"     "+
					o.getLastName()+"    "+o.getBadgeNumber()+"    "+o.getRank()+"      "+
								o.getPhoneNumber()+"     "+o.getAgencyId());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				sc.nextLine();
				System.out.println("enter the firstName");
				String firstName=sc.nextLine();
				System.out.println("enter the lastName");
				String lastName=sc.nextLine();
				System.out.println("enter the badge number");
				String badgeNumber=sc.nextLine();
				System.out.println("enter the phone number");
				String phoneNumber=sc.nextLine();
				System.out.println("enter the rank");
				int rank=sc.nextInt();
				System.out.println("enter the agencyId");
				int agencyId=sc.nextInt();
				
				Officer officer=new Officer(firstName, lastName, badgeNumber, rank, phoneNumber, agencyId);
				try {
					officerService.insertOfficer(officer);
					System.out.println("officer inserted successfully");
				} catch (ClassNotFoundException | SQLException | InvalidOfficerDataException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 3:
				System.out.println("enter the officerID");
				int officerId=sc.nextInt();
				try {
					List<Officer> officers=officerService.fetchAllOfficers();
					officerService.validateOfficer(officers,officerId);
					System.out.println("enter the incidentID to which the officer is going to be assigned");
					int incidentId=sc.nextInt();
					IncidentService incidentService = new IncidentService();
					List<Incident> allIncidents = incidentService.getAllIncidents();
					officerService.validateIncident(allIncidents,incidentId);
					
					officerService.assignOfficerToIncident(officerId,incidentId);
					
					System.out.println("officer assigned to incident successfully");
					
				} catch (ClassNotFoundException | SQLException | OfficerNotFoundException | IncidentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("enter the officer id to delete");
				int officerIdToDelete=sc.nextInt();
				 
				try {
					List<Officer> officers = officerService.fetchAllOfficers();
					officerService.validateOfficer(officers,officerIdToDelete);
					officerService.deleteOfficer(officerIdToDelete);
					System.out.println("officer deleted successfully");
				} catch (ClassNotFoundException | SQLException | OfficerNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 5:
				System.out.println("enter the officer id to display all incidents assigned to him");
				int officerIdToListIncidents=sc.nextInt();
				
				List<Officer> officers;
				try {
					officers = officerService.fetchAllOfficers();
					officerService.validateOfficer(officers,officerIdToListIncidents);
					List<Incident> incidents=officerService.listIncidents(officerIdToListIncidents);
					System.out.println("ID  IncidentType   IncidentDate   Loaction   Status");
					for(Incident i:incidents) {
						System.out.println(i.getIncidentId()+"  "+i.getIncidentType()+"   "+
					i.getIncidentDate()+"    "+i.getLocation()+"     "+i.getStatus());
					}
				} catch (ClassNotFoundException | SQLException | OfficerNotFoundException e) {
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
