package itp341.jain.saurabh.volunteers.Utility;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import itp341.jain.saurabh.volunteers.Model.Volunteer;

/**
 * Created by saurabhj80 on 11/22/16.
 */
public class Utilities {

    // Displays a toast
    static public void DisplayToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public final static class Analytics {
        public static Bundle basicInformation(Volunteer data) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, Long.toString(data.getId()));
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, data.getTitle());
            if (data.getAddress() != null) {
                bundle.putString(FirebaseAnalytics.Param.LOCATION, data.getAddress().toString());
            }
            bundle.putString(FirebaseAnalytics.Param.START_DATE, data.getDate());
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, data.getContentType());
            return bundle;
        }
    }

    // Analytic Events
    public final static class Event {
        public static final String CONTACT_US = "contact_us";
        public static final String REGISTER = "register";
    }

    public final static class Params {
        public static final String PHONE = "phone";
        public static final String PHONE_REACHABLE = "phone_reachable";
    }

}
