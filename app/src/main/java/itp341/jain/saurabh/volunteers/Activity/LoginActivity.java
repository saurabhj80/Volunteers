package itp341.jain.saurabh.volunteers.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import itp341.jain.saurabh.volunteers.Fragments.LoginFragment;
import itp341.jain.saurabh.volunteers.R;
import itp341.jain.saurabh.volunteers.Utility.Utilities;

public class LoginActivity extends FragmentActivity implements LoginFragment.LoginResultInterface {

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
            startActivity(new Intent(this, VolunteerActivity.class));
        }
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
                    Utilities.DisplayToast(LoginActivity.this, "You signed out!");
                }
            }
        };
    }
}
