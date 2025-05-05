package dao;

import java.util.Date;
import java.util.Collection;
import entity.Incident;
import entity.Report;

public interface ICrimeAnalysisService {

    // Create a new incident
    boolean createIncident(Incident incident);

    // Update the status of an incident
    boolean updateIncidentStatus(String newStatus, int incidentId);

    // Get a list of incidents within a date range
    Collection<Incident> getIncidentsInDateRange(Date startDate, Date endDate);

    // Search for incidents based on incident type
    Collection<Incident> searchIncidents(String incidentType);

    // Generate incident report
    Report generateIncidentReport(Incident incident);
}
