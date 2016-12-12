package itp341.jain.saurabh.volunteers.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import itp341.jain.saurabh.volunteers.Application.VApplication;
import itp341.jain.saurabh.volunteers.Manager.PermissionManager;
import itp341.jain.saurabh.volunteers.Model.Volunteer;
import itp341.jain.saurabh.volunteers.R;
import itp341.jain.saurabh.volunteers.Utility.Utilities;

public class DetailedVolunteerFragment extends android.support.v4.app.Fragment
        implements OnMapReadyCallback {

    // Model
    private Volunteer data;

    // UI
    private TextView mTitle;
    private TextView mOrganization;
    private TextView mDate;
    private TextView mDescription;
    private TextView mLocation;

    // Constructor
    public DetailedVolunteerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detailed_volunteer, container, false);

        // UI
        mTitle = (TextView) view.findViewById(R.id.detailed_volunteer_fragment_title);
        mOrganization = (TextView) view.findViewById(R.id.detailed_volunteer_fragment_org);
        mDate = (TextView) view.findViewById(R.id.detailed_volunteer_fragment_date);
        mDescription = (TextView) view.findViewById(R.id.detailed_volunteer_fragment_des);
        mLocation = (TextView) view.findViewById(R.id.location);

        // Add the map view if we have the location coordinates
        if (data.getLatitude() != 0 && data.getLongitude() != 0) {
            SupportMapFragment frag = SupportMapFragment.newInstance();
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayout, frag)
                    .commit();
            frag.getMapAsync(this);

            // Hide or display the location text view
            Volunteer.Address address = data.getAddress();
            if (address != null) {
                String add = address.toString();
                if (add != null) {
                    mLocation.setText(add);
                } else {
                    mLocation.setVisibility(View.GONE);
                }
            } else {
                mLocation.setVisibility(View.GONE);
            }

        } else {
            // Hide the map
            View location = view.findViewById(R.id.map);
            location.setVisibility(View.GONE);
        }

        // Register button clicked
        Button mRegister = (Button) view.findViewById(R.id.detailed_volunteer_fragment_register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // analytics
                logRegisterEvent();

                /*
                    Future: When we actually integrate with a volunteering organization
                    then the registration information will be directly sent to them when this
                    button is pressed
                 */

            }
        });

        // Phone the organization
        Button mContact = (Button) view.findViewById(R.id.detailed_volunteer_fragment_contact);
        mContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // log that we tried to reach the organization
                logPhoneEvent();

                if (data.getPhone() != null) {
                    // if we have permissions
                    if (PermissionManager.getInstance().isCanMakePhone(getContext())) {
                        makeCall(data.getPhone());
                    } else { // request a permission
                        PermissionManager.getInstance().requestPhonePermission(getActivity());
                    }
                } else {
                    Utilities.DisplayToast(getContext(), "Phone not available");
                }
            }
        });

        // Update the UI based on the data
        updateUI();

        return view;
    }
    
    // Called when the map is ready
    @Override
    public void onMapReady(GoogleMap map) {
        LatLng loc = new LatLng(data.getLatitude(), data.getLongitude());
        MarkerOptions marker = new MarkerOptions()
                .position(loc)
                .title("Marker");
        map.addMarker(marker);

        // Move the map to the marker
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 10), 4000, null);

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                // Location
                Uri location = Uri.parse("google.navigation:q="
                        + data.getLatitude()
                        + "," + data.getLongitude()
                        + "&mode=d");

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                mapIntent.setPackage("com.google.android.apps.maps");
                // if google maps installed
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }

    private void logRegisterEvent() {
        Bundle bundle = Utilities.Analytics.basicInformation(data);
        VApplication app = (VApplication) getActivity().getApplication();
        app.LogEvent(Utilities.Event.REGISTER, bundle);
    }

    private void logPhoneEvent() {
        Bundle bundle = Utilities.Analytics.basicInformation(data);
        if (data.getPhone() != null) {
            bundle.putString(Utilities.Params.PHONE, data.getPhone());
            bundle.putBoolean(Utilities.Params.PHONE_REACHABLE, true);
        } else {
            bundle.putBoolean(Utilities.Params.PHONE_REACHABLE, false);
        }
        VApplication app = (VApplication) getActivity().getApplication();
        app.LogEvent(Utilities.Event.CONTACT_US, bundle);
    }

    private void makeCall(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (PermissionManager
            .getInstance()
            .onRequestPermissionsResult(requestCode, permissions, grantResults))
        {
            if (data.getPhone() != null) {
                makeCall(data.getPhone());
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // The data that is represented by this fragment
    public void setVolunteerOpportunity(Volunteer data) {
        this.data = data;
        updateUI();
    }

    // Update the UI to reflect the data
    private void updateUI() {
        if (data != null) {
            mTitle.setText(data.getTitle());
            mOrganization.setText(data.getOrganization());
            mDate.setText(data.getDate());
            mDescription.setText(data.getDescription());
        }
    }

    private final static String VOLUNTEER = "volunteer";

    public static DetailedVolunteerFragment newInstance(Volunteer data) {
        DetailedVolunteerFragment fragment = new DetailedVolunteerFragment();
        Bundle args = new Bundle();
        args.putSerializable(VOLUNTEER, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.data = (Volunteer) getArguments().getSerializable(VOLUNTEER);
        }
    }
}
