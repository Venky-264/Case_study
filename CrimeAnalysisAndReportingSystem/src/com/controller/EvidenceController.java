package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exceptions.IncidentNotFoundException;
import com.exceptions.InvalidEvidenceException;
import com.model.Evidence;
import com.model.Incident;
import com.service.EvidenceService;
import com.service.IncidentService;

public class EvidenceController {

	public static void main(String[] args) {
		
		EvidenceService evidenceService=new EvidenceService();
		
		IncidentService incidentService = new IncidentService();
		
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			
			System.out.println("press 1.add evidence to an incident");
			System.out.println("press 2.to display all evidences for an incident");
			System.out.println("press 0. to exit...");
			int choice = sc.nextInt();

			if (choice == 0) {
				System.out.println("EXITING....");
				break;
			}
			
			switch(choice) {
			case 1:
				System.out.println("enter the incidentId to which the evidence is linked");
				int incidentId=sc.nextInt();
				
				try {
					List<Incident> incidents=incidentService.getAllIncidents();
					evidenceService.validateIncident(incidents,incidentId);
					sc.nextLine();
					System.out.println("enter the evidence description");
					String description=sc.nextLine();
					System.out.println("enter the location where evidence found");
					String location=sc.nextLine();
					Evidence evidence=new Evidence();
					evidence.setDescription(description);
					evidence.setLocation(location);
					evidence.setIncidentId(incidentId);
					evidenceService.assignEvidenceToIncident(evidence);
					System.out.println("evidence added to the incident successfully");
				} catch (ClassNotFoundException | SQLException | IncidentNotFoundException | InvalidEvidenceException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 2:
				System.out.println("enter the incidentId to display its evidences");
				int incidentIdForEvidences=sc.nextInt();
				
				try {
					List<Incident> incidents = incidentService.getAllIncidents();
					evidenceService.validateIncident(incidents,incidentIdForEvidences);
					List<Evidence> evidences=evidenceService.getAllEvidences(incidentIdForEvidences);
					System.out.println("evidenceId    location         description");
					for(Evidence e:evidences) {
						System.out.println(e.getEvidenceId()+"    "+e.getLocation()+"           "+e.getDescription());
					}
				} catch (ClassNotFoundException | SQLException | IncidentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				
				break;
			default:
				System.out.println("invalid option");
				break;
			}
			System.out.println("\n");
		}
		sc.close();
	}

}
