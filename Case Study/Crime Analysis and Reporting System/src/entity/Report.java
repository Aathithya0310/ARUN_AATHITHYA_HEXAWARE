package entity;

import java.util.Date;

public class Report {
    private int reportID;
    private int incidentID;
    private int reportingOfficerID;
    private Date reportDate;
    private String reportDetails;
    private String status;

    public Report() {}

    public Report(int reportID, int incidentID, int reportingOfficerID, Date reportDate, String reportDetails, String status) {
        this.reportID = reportID;
        this.incidentID = incidentID;
        this.reportingOfficerID = reportingOfficerID;
        this.reportDate = reportDate;
        this.reportDetails = reportDetails;
        this.status = status;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public int getReportingOfficerID() {
        return reportingOfficerID;
    }

    public void setReportingOfficerID(int reportingOfficerID) {
        this.reportingOfficerID = reportingOfficerID;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters and Setters
}
