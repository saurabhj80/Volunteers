package itp341.jain.saurabh.volunteers.Model;

import java.io.Serializable;

/**
 * Created by saurabhj80 on 11/23/16.
 */
public class Volunteer implements Serializable
{
    // Basic Information
    private String title;
    private String organization;
    private String description;

    // Time and date
    private String timeStart;
    private String timeEnd;
    private String date;

    // Location
    private double latitude;
    private double longitude;
    private Address address;

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


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public static class Address implements Serializable {

        private String street;
        private String city;
        private String state;
        private int zip;

        public Address() {}

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getZip() {
            return zip;
        }

        public void setZip(int zip) {
            this.zip = zip;
        }
    }
}
