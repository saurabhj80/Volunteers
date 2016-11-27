package itp341.jain.saurabh.volunteers.Fragments;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.analytics.FirebaseAnalytics;

import itp341.jain.saurabh.volunteers.Application.VApplication;
import itp341.jain.saurabh.volunteers.Manager.PermissionManager;
import itp341.jain.saurabh.volunteers.Model.Volunteer;
import itp341.jain.saurabh.volunteers.R;
import itp341.jain.saurabh.volunteers.Utility.Utilities;

public class DetailedVolunteerFragment extends android.support.v4.app.Fragment {

    // Model
    private Volunteer data;

    // UI
    private TextView mTitle;
    private TextView mOrganization;
    private TextView mDate;
    private TextView mDescription;

    private Button mRegister;
    private Button mContact;

    // Constructor
    public DetailedVolunteerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detailed_volunteer, container, false);

        android.support.v4.app.Fragment frag = SupportMapFragment.newInstance();
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout, frag)
                .commit();

        // UI
        mTitle = (TextView) view.findViewById(R.id.detailed_volunteer_fragment_title);
        mOrganization = (TextView) view.findViewById(R.id.detailed_volunteer_fragment_org);
        mDate = (TextView) view.findViewById(R.id.detailed_volunteer_fragment_date);
        mDescription = (TextView) view.findViewById(R.id.detailed_volunteer_fragment_des);

        // Register button clicked
        mRegister = (Button) view.findViewById(R.id.detailed_volunteer_fragment_register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logRegisterEvent();
            }
        });

        // Phone the organization
        mContact = (Button) view.findViewById(R.id.detailed_volunteer_fragment_contact);
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

        return view;
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
            .onRequestPermissionsResult(requestCode, permissions, grantResults)
                )
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

    public static DetailedVolunteerFragment newInstance() {
        DetailedVolunteerFragment fragment = new DetailedVolunteerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
}
