package entity;

public class Officer {
    private int officerID;
    private String firstName;
    private String lastName;
    private String badgeNumber;
    private String ranked;
    private String contactInfo;
    private int agencyID;

    public Officer() {}

    public Officer(int officerID, String firstName, String lastName, String badgeNumber,
                   String ranked, String contactInfo, int agencyID) {
        this.officerID = officerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.badgeNumber = badgeNumber;
        this.ranked = ranked;
        this.contactInfo = contactInfo;
        this.agencyID = agencyID;
    }

    public int getOfficerID() {
        return officerID;
    }

    public void setOfficerID(int officerID) {
        this.officerID = officerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getRanked() {
        return ranked;
    }

    public void setRank(String ranked) {
        this.ranked = ranked;
    }

    public int getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(int agencyID) {
        this.agencyID = agencyID;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
}
