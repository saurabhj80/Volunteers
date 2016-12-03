package itp341.jain.saurabh.volunteers.Model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by saurabhj80 on 11/23/16.
 */

public class Volunteer extends SugarRecord implements Serializable
{
    // Basic Information
    private String title;
    private String organization;
    private String description;
    private String phone;

    // FireBase id, but used by Sugar ORM for saving on phone
    private long vid;

    // Time and date
    private String timeStart;
    private String timeEnd;
    private String date;

    // Location
    private double latitude = 0;
    private double longitude = 0;
    private Address address = null;

    // Necessary for Firebase
    public Volunteer() {}

    // Constructor
    public Volunteer(String title, String date, String organization, String description) {
        this.title = title;
        this.date = date;
        this.organization = organization;
        this.description = description;
    }

    public boolean ExistsInSugar() {
        Volunteer volunteer = Volunteer.findById(Volunteer.class, getId());
        if (volunteer != null) {
            return true;
        }
        return false;
    }

    /************** GETTERS ********************/

    public String getContentType() {
        return "Volunteer";
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

    public String getOrganization() {
        return organization;
    }

    public String getDescription() {
        return description;
    }

    // Sugar ORM override
    @Override
    public Long getId() {
        return vid;
    }

    public String getPhone() {
        return phone;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Address getAddress() {
        return address;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }


    public static class Address extends SugarRecord implements Serializable {

        private String street = null;
        private String city = null;
        private String state = null;
        private int zip = 0;

        public Address() {}

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public int getZip() {
            return zip;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            String str = getStreet();
            if (str != null) {
                builder.append(str);
                builder.append("\n");
            }
            str = getCity();
            if (str != null) {
                builder.append(str);
                builder.append("\n");
            }
            str = getState();
            if (str != null) {
                builder.append(str);
            }
            if (getZip() != 0) {
                builder.append(" - ");
                builder.append(getZip());
            }

            // if we added something to the builder
            if (builder.length() > 0) {
                return builder.toString();
            }
            return null;
        }
    }
}
