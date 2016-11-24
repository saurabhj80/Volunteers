package itp341.jain.saurabh.volunteers.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import itp341.jain.saurabh.volunteers.Model.Volunteer;
import itp341.jain.saurabh.volunteers.R;
import itp341.jain.saurabh.volunteers.Fragments.VolunteerFragment.OnListFragmentInteractionListener;

public class VolunteerAdapter extends FirebaseRecyclerAdapter<Volunteer, VolunteerAdapter.ViewHolder> {

    // Passing clicks
    private OnListFragmentInteractionListener mListener;
    public void setListener(OnListFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    // Constructor
    public VolunteerAdapter(Class<Volunteer> modelClass,
                            int modelLayout,
                            Query ref) {
        super(modelClass, modelLayout, ViewHolder.class, ref);
    }

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, final Volunteer model, int position) {

        // Tell the fragment that an item was clicked
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolunteerAdapter.this.mListener.onListFragmentInteraction(model);
            }
        });
    }

    //    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.fragment_volunteer, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
//        //holder.mIdView.setText(mValues.get(position).id);
//        //holder.mContentView.setText(mValues.get(position).content);
//
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    //mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mValues.size();
//    }

    // The cell class
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        // UI
        public final View mView;
        public final TextView textViewTitle;
        public final TextView textViewDate;
        public final TextView textViewOrg;
        public final TextView textViewDes;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewTitle = (TextView) view.findViewById(R.id.volunteer_title);
            textViewDate = (TextView) view.findViewById(R.id.volunteer_date);
            textViewOrg = (TextView) view.findViewById(R.id.volunteer_org);
            textViewDes = (TextView) view.findViewById(R.id.volunteer_des);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewTitle.getText() + "'";
        }
    }
}
