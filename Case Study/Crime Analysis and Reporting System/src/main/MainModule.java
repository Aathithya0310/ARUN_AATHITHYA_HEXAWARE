package main;

import dao.CrimeAnalysisServiceImpl;
import dao.ICrimeAnalysisService;
import entity.Incident;
import entity.Report;
import exception.IncidentNumberNotFoundException;

import java.util.*;
import java.text.SimpleDateFormat;

public class MainModule {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ICrimeAnalysisService service = new CrimeAnalysisServiceImpl();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Crime Analysis and Reporting System (C.A.R.S.) ===");
            System.out.println("1. Create Incident");
            System.out.println("2. Update Incident Status");
            System.out.println("3. List Incidents in Date Range");
            System.out.println("4. Search Incidents by Type");
            System.out.println("5. Generate Incident Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> createIncident();
                case 2 -> updateStatus();
                case 3 -> listByDateRange();
                case 4 -> searchByType();
                case 5 -> generateReport();
                case 6 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    private static void createIncident() {
        try {
            System.out.print("Incident Type: ");
            String type = scanner.nextLine();

            System.out.print("Incident Date (yyyy-MM-dd): ");
            String dateStr = scanner.nextLine();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

            System.out.print("Location: ");
            String location = scanner.nextLine();

            System.out.print("Description: ");
            String desc = scanner.nextLine();

            System.out.print("Status: ");
            String status = scanner.nextLine();

            System.out.print("Victim ID: ");
            int victimId = Integer.parseInt(scanner.nextLine());

            System.out.print("Suspect ID: ");
            int suspectId = Integer.parseInt(scanner.nextLine());

            System.out.print("Officer ID: ");
            int officerId = Integer.parseInt(scanner.nextLine());

            Incident incident = new Incident(0, type, date, location, desc, status, victimId, suspectId, officerId);
            boolean success = service.createIncident(incident);

            System.out.println(success ? "Incident created successfully!" : "Failed to create incident.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateStatus() {
        System.out.print("Enter Incident ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new status: ");
        String status = scanner.nextLine();

        boolean updated = service.updateIncidentStatus(status, id);
        System.out.println(updated ? "Status updated successfully." : "Failed to update status.");
    }

    private static void listByDateRange() {
        try {
            System.out.print("Start Date (yyyy-MM-dd): ");
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());

            System.out.print("End Date (yyyy-MM-dd): ");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());

            Collection<Incident> incidents = service.getIncidentsInDateRange(start, end);
            if (incidents.isEmpty()) {
                System.out.println("No incidents found in this range.");
            } else {
                for (Incident i : incidents) {
                    System.out.println(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchByType() {
        System.out.print("Enter incident type to search: ");
        String type = scanner.nextLine();
        Collection<Incident> results = service.searchIncidents(type);
        if (results.isEmpty()) {
            System.out.println("No incidents found.");
        } else {
            for (Incident i : results) {
                System.out.println(i);
            }
        }
    }

    private static void generateReport() {
        System.out.print("Enter Incident ID for report: ");
        int id = Integer.parseInt(scanner.nextLine());

        // You'd typically load this from DB. Here's a placeholder for demo:
        Incident dummyIncident = new Incident(id, "Robbery", new Date(), "Downtown", "ATM Robbery", "Open", 101, 201, 301);
        Report report = service.generateIncidentReport(dummyIncident);
        System.out.println("Report Generated:");
        System.out.println("Incident ID: " + report.getIncidentID());
        System.out.println("Officer ID: " + report.getReportingOfficerID());
        System.out.println("Date: " + report.getReportDate());
        System.out.println("Details: " + report.getReportDetails());
        System.out.println("Status: " + report.getStatus());
    }
}
