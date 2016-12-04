package itp341.jain.saurabh.volunteers.Application;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by saurabhj80 on 11/27/16.
 */

public class VApplication extends Application {

    // Analytics
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        // init facebook sdk
        FacebookSdk.sdkInitialize(getApplicationContext());

        // init analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public FirebaseAnalytics getAnalytics() {
        return mFirebaseAnalytics;
    }

    public void LogEvent(String event, Bundle bundle) {
        getAnalytics().logEvent(event, bundle);
    }
}
