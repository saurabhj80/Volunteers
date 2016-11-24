package itp341.jain.saurabh.volunteers.Model;

import java.io.Serializable;

/**
 * Created by saurabhj80 on 11/23/16.
 */
public class Volunteer implements Serializable
{
    // Data
    private String title;
    private String date;
    private String organization;
    private String description;

    // Location
    private double latitude;
    private double longitude;

    // Necessary for Firebase
    public Volunteer() {}

    // Constructor
    public Volunteer(String title, String date, String organization, String description) {
        this.title = title;
        this.date = date;
        this.organization = organization;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
