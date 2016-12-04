package itp341.jain.saurabh.volunteers.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import itp341.jain.saurabh.volunteers.Fragments.LoginFragment;
import itp341.jain.saurabh.volunteers.Manager.FirebaseManager;
import itp341.jain.saurabh.volunteers.Model.Volunteer;
import itp341.jain.saurabh.volunteers.R;

public class LoginActivity extends FragmentActivity implements LoginFragment.LoginResultInterface {

    // Fragment contains all the login UI
    LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // check if user is signed in already
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // process the notification
            Intent intent = getIntent();
            if (intent != null) {
                Bundle notification = intent.getExtras();
                if (notification != null) {
                    // Load the loading screen
                    final ProgressDialog dialog = ProgressDialog
                            .show(this,
                                    "Loading...",
                                    "Fetching specific details about the volunteering opportunity",
                                    true);
                    FirebaseManager.ProcessNotification(notification, new FirebaseManager.NotificationCallback() {
                        @Override
                        public void DataFromNotification(Volunteer volunteer) {
                            // Stop the loading indicator
                            dialog.dismiss();
                            if (volunteer == null) {
                                startActivity(new Intent(LoginActivity.this, VolunteerActivity.class));
                            } else {
                                Intent intent = new Intent(LoginActivity.this, DetailedVolunteerActivity.class);
                                intent.putExtra(DetailedVolunteerActivity.INTENT_VOLUNTEER, volunteer);
                                startActivity(intent);
                            }
                        }
                    });
                } else {
                    startActivity(new Intent(this, VolunteerActivity.class));
                }
            } else {
                startActivity(new Intent(this, VolunteerActivity.class));
            }
        } else {
            // Show users the UI for logging in
            FragmentManager manager = getSupportFragmentManager();
            loginFragment = (LoginFragment) manager.findFragmentById(R.id.loginActivity_frame);
            if (loginFragment == null) {
                loginFragment = LoginFragment.newInstance();
            }
            LoadFragmgent(R.id.loginActivity_frame, loginFragment);
        }
    }

    private void LoadFragmgent(int id, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment)
                .commit();
    }

    // Login Fragment takes this listener and passes information to us regarding login
    @Override
    public FirebaseAuth.AuthStateListener LoginAuthStateListener() {
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(LoginActivity.this, VolunteerActivity.class));
                } else {
                    //Utilities.DisplayToast(LoginActivity.this, "You signed out!");
                }
            }
        };
    }
}
