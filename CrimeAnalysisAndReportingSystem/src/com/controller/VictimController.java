package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.exceptions.IncidentNotFoundException;
import com.exceptions.InvalidVictimDataException;
import com.exceptions.VictimNotFoundException;
import com.model.Incident;
import com.model.Victim;
import com.service.IncidentService;
import com.service.ReportService;
import com.service.VictimService;

public class VictimController {
	public static void main(String[] args)  {
		
		Scanner sc=new Scanner(System.in);
		VictimService victimService=new VictimService();
		IncidentService incidentService=new IncidentService();
		ReportService reportService=new ReportService();
		
		while(true) {
			System.out.println("Press 1. To Add a Victim");
			System.out.println("Press 2. To Delete a Victim");
			System.out.println("Press 3. To Display Victims by Incident Id");
			System.out.println("Press 4. To Search Victim By Name");
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
					Victim victim=new Victim(firstName, lastName, dateOfBirth, gender, contactInfo, incidentId);
					victimService.addRecord(victim);
					System.out.println("victim inserted successfully");
				} catch (ClassNotFoundException | SQLException | DateTimeParseException | IncidentNotFoundException | InvalidVictimDataException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				
				System.out.println("Deleting Victim Record");
				System.out.println("Enter victim id:");
				int victimIdForDeletion=sc.nextInt();
				try {
					victimService.deleteVictim(victimIdForDeletion);
					System.out.println("victim deleted successfully");
				} catch (ClassNotFoundException | SQLException | VictimNotFoundException e) {
					System.out.println(e.getMessage());
					
				}

				break;
			case 3:
				System.out.println("Displaying Victim details by using Incident Id");
				System.out.println("Enter incident id:");
				int incidentIdForvictim=sc.nextInt();
				
				try {
					List<Incident> incidents = incidentService.getAllIncidents();
					reportService.validateIncident(incidents,incidentIdForvictim);
					List<Victim> victims = victimService.getVictim(incidentIdForvictim);
					
					if(victims.isEmpty()) {
						System.out.println("no victims for this incident");
						continue;
					}
					
					System.out.println("all the victims for the above incident are");
					for(Victim vic:victims){
						System.out.println(vic);
					}
						
				}  catch (ClassNotFoundException | SQLException | IncidentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 4:
				sc.nextLine();
				System.out.println("Displaying Victim details by using name");
				System.out.println("Enter name:");
				String name=sc.nextLine();
				if(name.equals(null) || name.equals("")) {
					System.out.println("name cant be null");
					continue;
				}
				try {
					List<Victim> victims = victimService.getVictimByName(name);
					if(victims.isEmpty()) {
						System.out.println("no victims for this incident");
						continue;
					}
					System.out.println("all the victims for the above name are");
					for(Victim vic:victims){
						System.out.println(vic);
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
