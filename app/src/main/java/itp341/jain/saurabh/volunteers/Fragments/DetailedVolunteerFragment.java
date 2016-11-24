package itp341.jain.saurabh.volunteers.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

import itp341.jain.saurabh.volunteers.Model.Volunteer;
import itp341.jain.saurabh.volunteers.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailedVolunteerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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

            }
        });

        // Phone the organization
        mContact = (Button) view.findViewById(R.id.detailed_volunteer_fragment_contact);
        mContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
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
