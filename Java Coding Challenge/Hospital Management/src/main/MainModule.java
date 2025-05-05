package main;

import dao.HospitalService;
import dao.HospitalServiceImpl;
import entity.Appointment;
import exception.PatientNumberNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class MainModule {
    private static HospitalService hospitalService = new HospitalServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\nHospital Management System");
            System.out.println("1. Get Appointment by ID");
            System.out.println("2. Get Appointments for Patient");
            System.out.println("3. Get Appointments for Doctor");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. Update Appointment");
            System.out.println("6. Cancel Appointment");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        getAppointmentById();
                        break;
                    case 2:
                        getAppointmentsForPatient();
                        break;
                    case 3:
                        getAppointmentsForDoctor();
                        break;
                    case 4:
                        scheduleAppointment();
                        break;
                    case 5:
                        updateAppointment();
                        break;
                    case 6:
                        cancelAppointment();
                        break;
                    case 7:
                        running = false;
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (PatientNumberNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void getAppointmentById() throws Exception {
        System.out.print("Enter Appointment ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Appointment appointment = hospitalService.getAppointmentById(id);
        System.out.println("Appointment Details:");
        System.out.println(appointment);
    }

    private static void getAppointmentsForPatient() throws Exception {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        List<Appointment> appointments = hospitalService.getAppointmentsForPatient(patientId);
        System.out.println("Appointments for Patient " + patientId + ":");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    private static void getAppointmentsForDoctor() throws Exception {
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();

        List<Appointment> appointments = hospitalService.getAppointmentsForDoctor(doctorId);
        System.out.println("Appointments for Doctor " + doctorId + ":");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    private static void scheduleAppointment() throws Exception {
        System.out.println("Enter Appointment Details:");
        System.out.print("Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Appointment Date and Time (yyyy-MM-dd HH:mm): ");
        String dateTimeStr = scanner.nextLine();
        Date appointmentDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateTimeStr);

        System.out.print("Description: ");
        String description = scanner.nextLine();

        Appointment appointment = new Appointment(0, patientId, doctorId, appointmentDate, description);
        boolean success = hospitalService.scheduleAppointment(appointment);

        if (success) {
            System.out.println("Appointment scheduled successfully with ID: " + appointment.getAppointmentId());
        }
    }

    private static void updateAppointment() throws Exception {
        System.out.print("Enter Appointment ID to update: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();

        // Get existing appointment
        Appointment existing = hospitalService.getAppointmentById(appointmentId);
        System.out.println("Current Appointment Details:");
        System.out.println(existing);

        System.out.println("Enter new details (leave blank to keep current value):");

        System.out.print("Patient ID (" + existing.getPatientId() + "): ");
        String patientIdStr = scanner.nextLine();
        int patientId = patientIdStr.isEmpty() ? existing.getPatientId() : Integer.parseInt(patientIdStr);

        System.out.print("Doctor ID (" + existing.getDoctorId() + "): ");
        String doctorIdStr = scanner.nextLine();
        int doctorId = doctorIdStr.isEmpty() ? existing.getDoctorId() : Integer.parseInt(doctorIdStr);

        System.out.print("Appointment Date and Time (" + existing.getAppointmentDate() + "): ");
        String dateTimeStr = scanner.nextLine();
        Date appointmentDate = dateTimeStr.isEmpty() ? existing.getAppointmentDate() :
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateTimeStr);

        System.out.print("Description (" + existing.getDescription() + "): ");
        String description = scanner.nextLine();
        if (description.isEmpty()) {
            description = existing.getDescription();
        }

        Appointment updated = new Appointment(appointmentId, patientId, doctorId, appointmentDate, description);
        boolean success = hospitalService.updateAppointment(updated);

        if (success) {
            System.out.println("Appointment updated successfully.");
        }
    }

    private static void cancelAppointment() throws Exception {
        System.out.print("Enter Appointment ID to cancel: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();

        boolean success = hospitalService.cancelAppointment(appointmentId);
        if (success) {
            System.out.println("Appointment canceled successfully.");
        }
    }
}