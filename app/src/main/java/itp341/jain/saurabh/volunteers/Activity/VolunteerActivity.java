package itp341.jain.saurabh.volunteers.Activity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import itp341.jain.saurabh.volunteers.Fragments.VolunteerFragment;
import itp341.jain.saurabh.volunteers.Manager.VLocationManager;
import itp341.jain.saurabh.volunteers.Model.Volunteer;
import itp341.jain.saurabh.volunteers.R;

public class VolunteerActivity extends AppCompatActivity implements VolunteerFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

//        VLocationManager
//                .getInstance()
//                .initialize(this)
//                .getLocation(this, new LocationListener() {
//                    @Override
//                    public void onLocationChanged(Location location) {
//                        Log.d("Tag", location.getLatitude() + "");
//                    }
//
//                    @Override
//                    public void onStatusChanged(String s, int i, Bundle bundle) {
//
//                    }
//
//                    @Override
//                    public void onProviderEnabled(String s) {
//
//                    }
//
//                    @Override
//                    public void onProviderDisabled(String s) {
//
//                    }
//                });
    }

    // VolunteerFragment.OnListFragmentInteractionListener
    // Callbacks when items are clicked
    @Override
    public void onListFragmentInteraction(Volunteer item) {
        Intent intent = new Intent(this, DetailedVolunteerActivity.class);
        intent.putExtra(DetailedVolunteerActivity.INTENT_VOLUNTEER, item);
        startActivity(intent);
    }
}
