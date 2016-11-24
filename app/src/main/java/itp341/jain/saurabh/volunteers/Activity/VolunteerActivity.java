package itp341.jain.saurabh.volunteers.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import itp341.jain.saurabh.volunteers.Fragments.VolunteerFragment;
import itp341.jain.saurabh.volunteers.Fragments.dummy.DummyContent;
import itp341.jain.saurabh.volunteers.R;

public class VolunteerActivity extends AppCompatActivity implements VolunteerFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
    }

    /*
        Need to be implemented since we contain VolunteerFragment.
        Method is called when the user presses on one event
        Can use the item passed in to start another activity
     */

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
