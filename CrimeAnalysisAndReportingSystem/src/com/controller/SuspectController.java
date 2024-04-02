
package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.exceptions.IncidentNotFoundException;
import com.exceptions.InvalidSuspectDataException;
import com.exceptions.SuspectNotFoundException;
import com.model.Incident;
import com.model.Suspect;
import com.service.IncidentService;
import com.service.ReportService;
import com.service.SuspectService;

public class SuspectController {
	public static void main(String[] args)  {
		
		Scanner sc=new Scanner(System.in);
		SuspectService suspectService=new SuspectService();
		IncidentService incidentService=new IncidentService();
		ReportService reportService=new ReportService();
		
		while(true) {
			System.out.println("Press 1. To Add a Suspect");
			System.out.println("Press 2. To Delete a Suspect");
			System.out.println("Press 3. To Display Suspects by Incident Id");
			System.out.println("Press 4. To Search Suspect By Name");
			System.out.println("Press 0. To Exit");
			int choice=sc.nextInt();
			if(choice==0)
			{
				System.out.println("Exiting.... Thank You");
				break;
			}
			switch(choice)
			{
			case 1:
				
				sc.nextLine();
				System.out.println("Enter firstName:");
				String firstName=sc.nextLine();
				System.out.println("Enter lastName:");
				String lastName=sc.nextLine();
				System.out.println("Enter gender:");
				String gender=sc.nextLine();
				System.out.println("Enter phone no:");
				String contactInfo=sc.nextLine();
				System.out.println("Enter incident id:");
				int incidentId=sc.nextInt();
				try {
					sc.nextLine();
					System.out.println("Enter dob: format should be YYYY-MM-DD");
					String dob=sc.nextLine();
					LocalDate dateOfBirth=LocalDate.parse(dob);
					List<Incident> incidents = incidentService.getAllIncidents();
					reportService.validateIncident(incidents,incidentId);
					Suspect suspect=new Suspect(firstName, lastName, dateOfBirth, gender, contactInfo, incidentId);
					suspectService.addRecord(suspect);
					System.out.println("Suspect inserted successfully");
				} catch (ClassNotFoundException | SQLException | DateTimeParseException | IncidentNotFoundException | InvalidSuspectDataException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				
				System.out.println("Deleting Suspect Record");
				System.out.println("Enter Suspect id:");
				int suspectIdForDeletion=sc.nextInt();
				try {
					suspectService.deleteSuspect(suspectIdForDeletion);
					System.out.println("Suspect deleted successfully");
				} catch (ClassNotFoundException | SQLException | SuspectNotFoundException e) {
					System.out.println(e.getMessage());
					
				}

				break;
			case 3:
				System.out.println("Displaying Suspect details by using Incident Id");
				System.out.println("Enter incident id:");
				int incidentIdForSuspect=sc.nextInt();
				
				try {
					List<Incident> incidents = incidentService.getAllIncidents();
					reportService.validateIncident(incidents,incidentIdForSuspect);
					List<Suspect> suspects= suspectService.getSuspect(incidentIdForSuspect);
					
					if(suspects.isEmpty()) {
						System.out.println("no Suspects for this incident");
						continue;
					}
					
					System.out.println("all the Suspects for the above incident are");
					for(Suspect sus:suspects){
						System.out.println(sus);
					}
						
				}  catch (ClassNotFoundException | SQLException | IncidentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 4:
				sc.nextLine();
				System.out.println("Displaying Suspect details by using name");
				System.out.println("Enter name:");
				String name=sc.nextLine();
				if(name.equals(null) || name.equals("")) {
					System.out.println("name cant be null");
					continue;
				}
				try {
					List<Suspect> suspects = suspectService.getSuspectByName(name);
					if(suspects.isEmpty()) {
						System.out.println("no Suspect for this incident");
						continue;
					}
					System.out.println("all the Suspect for the above name are");
					for(Suspect sus:suspects){
						System.out.println(sus);
					}
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				System.out.println("Invalid Input");
			}
		}
		sc.close();
		System.out.println("\n");
}

}
