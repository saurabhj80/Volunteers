package itp341.jain.saurabh.volunteers.Fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;
import android.net.Uri;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import itp341.jain.saurabh.volunteers.Model.Volunteer;
import itp341.jain.saurabh.volunteers.Fragments.VolunteerFragment.OnListFragmentInteractionListener;

public class VolunteerAdapter extends FirebaseRecyclerAdapter<Volunteer, ViewHolder> {

    // Passing clicks
    private OnListFragmentInteractionListener mListener;
    public void setListener(OnListFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    // Constructor
    public VolunteerAdapter(Class<Volunteer> modelClass,
                            int modelLayout,
                            Class<ViewHolder> holderClass,
                            Query ref) {
        super(modelClass, modelLayout, holderClass, ref);
    }

    private Fragment parent;
    public VolunteerAdapter setParentFragment(Fragment fragment) {
        parent = fragment;
        return this;
    }

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, final Volunteer model, int position) {

        // Update the view holder
        viewHolder.setTitle(model.getTitle());
        viewHolder.setDate(model.getDate());
        viewHolder.setDescription(model.getDescription());
        viewHolder.setOrganization(model.getOrganization());

        // Load the image
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference ref = storage.getReference("images");
        StorageReference imageref = ref.child(model.getId() + ".jpg");
        Glide.with(parent)
                .using(new FirebaseImageLoader())
                .load(imageref)
                .centerCrop()
                .into(viewHolder.getImageView());

        // Tell the fragment that an item was clicked
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VolunteerAdapter.this.mListener != null) {
                    VolunteerAdapter.this.mListener.onListFragmentInteraction(model);
                }
            }
        });
    }
}
