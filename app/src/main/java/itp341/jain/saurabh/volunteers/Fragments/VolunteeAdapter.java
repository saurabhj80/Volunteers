package itp341.jain.saurabh.volunteers.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import itp341.jain.saurabh.volunteers.R;
import itp341.jain.saurabh.volunteers.Fragments.VolunteerFragment.OnListFragmentInteractionListener;
import itp341.jain.saurabh.volunteers.Fragments.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class VolunteeAdapter extends RecyclerView.Adapter<VolunteeAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public VolunteeAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_volunteer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    // The cell class
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        // UI
        public final View mView;
        public final TextView textViewTitle;
        public final TextView textViewDate;
        public final TextView textViewOrg;
        public final TextView textViewDes;

        public DummyItem mItem;

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
