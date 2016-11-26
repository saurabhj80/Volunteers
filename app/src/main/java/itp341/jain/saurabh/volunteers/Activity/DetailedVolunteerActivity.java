package itp341.jain.saurabh.volunteers.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import itp341.jain.saurabh.volunteers.Fragments.DetailedVolunteerFragment;
import itp341.jain.saurabh.volunteers.Model.Volunteer;
import itp341.jain.saurabh.volunteers.R;

public class DetailedVolunteerActivity extends AppCompatActivity {

    // Data passed through intents
    public final static String INTENT_VOLUNTEER = "INTENT_VOLUNTEER";

    // The data passed in
    private Volunteer data;

    // Fragment
    private DetailedVolunteerFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_volunteer);

        // Volunteer opportunity that is passed in
        Intent intent = getIntent();
        if (intent != null) {
            data = (Volunteer) intent.getSerializableExtra(INTENT_VOLUNTEER);
        }

        // Access to the detailed fragment
        fragment = (DetailedVolunteerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detailed_volunteer_fragment);

        // Will cause the fragment to refresh its UI
        if (data != null) {
            fragment.setVolunteerOpportunity(data);
        }
    }

}
