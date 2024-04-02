package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exceptions.AgencyNotFoundException;
import com.exceptions.InvalidAgencyDataException;
import com.exceptions.OfficerNotFoundException;
import com.model.Agency;
import com.model.Officer;
import com.service.AgencyService;
import com.service.OfficerService;

public class AgencyController {

	public static void main(String[] args) {
		
		//agency controller with all operations
		
		AgencyService agencyService=new AgencyService();
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			
			System.out.println("press 1.display all agencies");
			System.out.println("press 2.to add a new agency");
			System.out.println("press 3.to assign officer to an agency");
			System.out.println("press 4.to display all officers working for an agency");
			System.out.println("press 0. to exit...");
			int choice = sc.nextInt();

			if (choice == 0) {
				System.out.println("EXITING....");
				break;
			}
			
			switch(choice) {
			case 1:
				try {
					List<Agency> agencies=agencyService.getAllAgencies();
					
					System.out.println("agencyId    agencyName    Jurisdiction      contactNumber");
					for(Agency a:agencies) {
						System.out.println(a.getAgencyId()+"    "+a.getAgencyName()+"     "+
					a.getJurisdiction()+"     "+a.getContactNumber());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				sc.nextLine();
				System.out.println("enter the agency name");
				String agencyName=sc.nextLine();
				System.out.println("enter the  Jurisdiction");
				String  jurisdiction=sc.nextLine();
				System.out.println("enter the phone number");
				String phoneNumber=sc.nextLine();
				
				Agency agency=new Agency(agencyName, jurisdiction, phoneNumber);
				
				try {
					agencyService.addAgency(agency);
					System.out.println("agency added successfully");
				} catch (ClassNotFoundException | SQLException | InvalidAgencyDataException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 3:
				
				System.out.println("enter the agencyId");
				int agencyId=sc.nextInt();
				
				try {
					List<Agency> agencies=agencyService.getAllAgencies();
					agencyService.validateAgency(agencies,agencyId);
					System.out.println("enter the officerId who is going to be assigned to this agency");
					int officerId=sc.nextInt();
					OfficerService officerService=new OfficerService();
					List<Officer> allOfficers = officerService.fetchAllOfficers();
					agencyService.validateOfficer(allOfficers,officerId);
					agencyService.assignOfficerToAgency(officerId,agencyId);
					System.out.println("officer assigned to agency successfully");
				} catch (ClassNotFoundException | SQLException | AgencyNotFoundException | OfficerNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 4:
				System.out.println("enter the agencyId");
				int agencyIdToGetAllOfficers=sc.nextInt();
				List<Agency> agencies;
				try {
					agencies = agencyService.getAllAgencies();
					agencyService.validateAgency(agencies,agencyIdToGetAllOfficers);
					List<Officer> officers=agencyService.fetchAllOfficers(agencyIdToGetAllOfficers);
					
					System.out.println("Id  fName   lname   badgeNUmber    rank    phoneNumber");
					for(Officer o:officers) {
						System.out.println(o.getOfficerId()+"    "+o.getFirstName()+"     "+
					o.getLastName()+"    "+o.getBadgeNumber()+"    "+o.getRank()+"      "+
								o.getPhoneNumber());
					}
					
				} catch (ClassNotFoundException | SQLException | AgencyNotFoundException e) {
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
