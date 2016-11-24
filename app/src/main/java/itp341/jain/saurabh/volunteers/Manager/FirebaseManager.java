package itp341.jain.saurabh.volunteers.Manager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by saurabhj80 on 11/23/16.
 */
public class FirebaseManager {

    public final static String FIRMANAGER_VOLUNTEER = "volunteers";

    // Returns the reference to the database
    public static DatabaseReference Reference(String s) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        if (s != null) {
            return ref.child(s);
        }
        return ref;
    }

}
