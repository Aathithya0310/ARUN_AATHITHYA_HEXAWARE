package dao;

import entity.Appointment;
import exception.PatientNumberNotFoundException;
import util.DBConnUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalServiceImpl implements HospitalService {
    private Connection connection;

    public HospitalServiceImpl() {
        try {
            connection = DBConnUtil.getConnection();
        } catch (Exception e) {
            System.err.println("Error establishing database connection: " + e.getMessage());
        }
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws Exception {
        String query = "SELECT * FROM Appointment WHERE appointmentId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, appointmentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Appointment(
                        resultSet.getInt("appointmentId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getTimestamp("appointmentDate"),
                        resultSet.getString("description")
                );
            } else {
                throw new Exception("Appointment not found with ID: " + appointmentId);
            }
        } catch (SQLException e) {
            throw new Exception("Error retrieving appointment: " + e.getMessage());
        }
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM Appointment WHERE patientId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getInt("appointmentId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getTimestamp("appointmentDate"),
                        resultSet.getString("description")
                ));
            }

            if (appointments.isEmpty()) {
                throw new PatientNumberNotFoundException("No appointments found for patient ID: " + patientId);
            }

            return appointments;
        } catch (SQLException e) {
            throw new Exception("Error retrieving patient appointments: " + e.getMessage());
        }
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM Appointment WHERE doctorId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getInt("appointmentId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getTimestamp("appointmentDate"),
                        resultSet.getString("description")
                ));
            }

            if (appointments.isEmpty()) {
                throw new Exception("No appointments found for doctor ID: " + doctorId);
            }

            return appointments;
        } catch (SQLException e) {
            throw new Exception("Error retrieving doctor appointments: " + e.getMessage());
        }
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) throws Exception {
        String query = "INSERT INTO Appointment (patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setTimestamp(3, new java.sql.Timestamp(appointment.getAppointmentDate().getTime()));
            statement.setString(4, appointment.getDescription());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("Creating appointment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    appointment.setAppointmentId(generatedKeys.getInt(1));
                } else {
                    throw new Exception("Creating appointment failed, no ID obtained.");
                }
            }

            return true;
        } catch (SQLException e) {
            throw new Exception("Error scheduling appointment: " + e.getMessage());
        }
    }

    @Override
    public boolean updateAppointment(Appointment appointment) throws Exception {
        String query = "UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setTimestamp(3, new java.sql.Timestamp(appointment.getAppointmentDate().getTime()));
            statement.setString(4, appointment.getDescription());
            statement.setInt(5, appointment.getAppointmentId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("No appointment found with ID: " + appointment.getAppointmentId());
            }

            return true;
        } catch (SQLException e) {
            throw new Exception("Error updating appointment: " + e.getMessage());
        }
    }

    @Override
    public boolean cancelAppointment(int appointmentId) throws Exception {
        String query = "DELETE FROM Appointment WHERE appointmentId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, appointmentId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("No appointment found with ID: " + appointmentId);
            }

            return true;
        } catch (SQLException e) {
            throw new Exception("Error canceling appointment: " + e.getMessage());
        }
    }
}