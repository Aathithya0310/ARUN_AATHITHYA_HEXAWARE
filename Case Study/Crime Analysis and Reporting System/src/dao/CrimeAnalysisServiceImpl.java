package dao;

import entity.Incident;
import entity.Report;
import util.DBConnUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {

    private static Connection connection;

    public CrimeAnalysisServiceImpl() {
        try {
            connection = DBConnUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();  // You can replace this with a logger
        }
    }

    @Override
    public boolean createIncident(Incident incident) {
        String sql = "INSERT INTO Incidents (IncidentType, IncidentDate, Location, Description, Status, VictimID, SuspectID, OfficerID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, incident.getIncidentType());
            stmt.setDate(2, new java.sql.Date(incident.getIncidentDate().getTime()));
            stmt.setString(3, incident.getLocation());
            stmt.setString(4, incident.getDescription());
            stmt.setString(5, incident.getStatus());
            stmt.setInt(6, incident.getVictimID());
            stmt.setInt(7, incident.getSuspectID());
            stmt.setInt(8, incident.getOfficerID());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateIncidentStatus(String newStatus, int incidentId) {
        String sql = "UPDATE Incidents SET Status = ? WHERE IncidentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, incidentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Collection<Incident> getIncidentsInDateRange(Date startDate, Date endDate) {
        Collection<Incident> incidents = new ArrayList<>();
        String sql = "SELECT * FROM Incidents WHERE IncidentDate BETWEEN ? AND ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Incident inc = new Incident(
                        rs.getInt("IncidentID"),
                        rs.getString("IncidentType"),
                        rs.getDate("IncidentDate"),
                        rs.getString("Location"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getInt("VictimID"),
                        rs.getInt("SuspectID"),
                        rs.getInt("OfficerID")
                );
                incidents.add(inc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }

    @Override
    public Collection<Incident> searchIncidents(String incidentType) {
        Collection<Incident> incidents = new ArrayList<>();
        String sql = "SELECT * FROM Incidents WHERE IncidentType = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, incidentType);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Incident inc = new Incident(
                        rs.getInt("IncidentID"),
                        rs.getString("IncidentType"),
                        rs.getDate("IncidentDate"),
                        rs.getString("Location"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getInt("VictimID"),
                        rs.getInt("SuspectID"),
                        rs.getInt("OfficerID")
                );
                incidents.add(inc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incidents;
    }

    @Override
    public Report generateIncidentReport(Incident incident) {
        // You can customize this more, here’s a simple stub
        Report report = new Report();
        report.setIncidentID(incident.getIncidentID());
        report.setReportingOfficerID(incident.getOfficerID());
        report.setReportDate(new Date());
        report.setReportDetails("Auto-generated report for incident ID: " + incident.getIncidentID());
        report.setStatus("Draft");

        // Logic to save to DB can go here if needed
        return report;
    }
}
