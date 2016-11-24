package itp341.jain.saurabh.volunteers.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import itp341.jain.saurabh.volunteers.Model.Volunteer;
import itp341.jain.saurabh.volunteers.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailedVolunteerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailedVolunteerFragment extends android.support.v4.app.Fragment {

    private Volunteer data;



    // Constructor
    public DetailedVolunteerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detailed_volunteer, container, false);



        return view;
    }

    public void setVolunteerOpportunity(Volunteer data) {
        this.data = data;
        updateUI();
    }

    private void updateUI() {

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
