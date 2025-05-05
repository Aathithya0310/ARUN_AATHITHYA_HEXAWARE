package dao;

import entity.Incident;
import exception.IncidentNumberNotFoundException;
import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CrimeAnalysisServiceImplTest {

    private static ICrimeAnalysisService service;

    @BeforeAll
    static void setup() {
        service = new CrimeAnalysisServiceImpl();
    }

    @Test
    @DisplayName("Test creating a new incident")
    void testCreateIncident() {
        Incident incident = new Incident(
                0, // Assuming ID is auto-generated
                "Burglary",
                new Date(),
                "Green Street",
                "Break-in at a local store",
                "Open",
                101, // Victim ID
                201, // Suspect ID
                301  // Officer ID
        );

        boolean created = service.createIncident(incident);
        assertTrue(created, "Incident should be created successfully");
    }

    @Test
    @DisplayName("Test updating status of an existing incident")
    void testUpdateIncidentStatusValid() {
        boolean updated = service.updateIncidentStatus("Closed", 1); // Assume ID 1 exists
        assertTrue(updated, "Incident status should be updated");
    }

    @Test
    @DisplayName("Test updating status with invalid incident ID")
    void testUpdateIncidentStatusInvalid() {
        assertThrows(IncidentNumberNotFoundException.class, () -> {
            service.updateIncidentStatus("Closed", -99); // Invalid ID
        }, "Should throw IncidentNumberNotFoundException for invalid ID");
    }
}

