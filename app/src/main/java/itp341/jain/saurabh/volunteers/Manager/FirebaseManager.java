package itp341.jain.saurabh.volunteers.Manager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import itp341.jain.saurabh.volunteers.Model.Volunteer;

/**
 * Created by saurabhj80 on 11/23/16.
 */
public class FirebaseManager
{
    // Node point for the beginning for all volunteer opportunities
    public final static String FIRMANAGER_VOLUNTEER = "volunteers";

    // Returns the reference to the database
    public static DatabaseReference Reference(String s) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        if (s != null) {
            return ref.child(s);
        }
        return ref;
    }

    // Fetch the data from the server
    public static void QueryData(String id, final NotificationCallback callback) {
        Query ref = Reference(FIRMANAGER_VOLUNTEER + "/" + id);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Volunteer data = dataSnapshot.getValue(Volunteer.class);
                if (callback != null && data != null) {
                    callback.DataFromNotification(data);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (callback != null) {
                    callback.DataFromNotification(null);
                }
            }
        });
    }

    /*********** NOTIFICATIONS *************/

    // Call the method when the notification data is processed
    public interface NotificationCallback {
        void DataFromNotification(Volunteer volunteer);
    }

    // keys to extract data from the payload
    private final static String NOTIFICATION_VALID = "notification";
    private final static String NOTIFICATION__ID = "id";

    // Parses the notification
    public static void ProcessNotification(Bundle notification, NotificationCallback callback) {
        if (notification != null) {
            String valid = notification.getString(NOTIFICATION_VALID);
            if (valid != null) {
                String vid = notification.getString(NOTIFICATION__ID);
                // we have an event
                if (vid != null) {
                    QueryData(vid, callback);
                } else {
                    callback.DataFromNotification(null);
                }
            } else {
                callback.DataFromNotification(null);
            }
        } else {
            callback.DataFromNotification(null);
        }
    }

}
