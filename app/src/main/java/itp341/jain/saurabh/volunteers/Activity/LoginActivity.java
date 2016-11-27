package itp341.jain.saurabh.volunteers.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import itp341.jain.saurabh.volunteers.Fragments.LoginFragment;
import itp341.jain.saurabh.volunteers.R;

public class LoginActivity extends FragmentActivity {

    // Fragment contains all the login UI
    LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.login_fragment);

        // check if user is signed in already
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

        }

        // DEBUGGING
        startActivity(new Intent(this, VolunteerActivity.class));

    }

}
