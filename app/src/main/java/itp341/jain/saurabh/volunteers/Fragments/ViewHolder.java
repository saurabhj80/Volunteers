package itp341.jain.saurabh.volunteers.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import itp341.jain.saurabh.volunteers.R;

/**
 * Created by saurabhj80 on 11/24/16.
 */
// The cell class
public class ViewHolder extends RecyclerView.ViewHolder
{
    // UI
    public final View mView;
    public final TextView textViewTitle;
    public final TextView textViewDate;
    public final TextView textViewOrg;
    public final TextView textViewDes;
    public final ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public ViewHolder(View view) {
        super(view);

        mView = view;
        textViewTitle = (TextView) view.findViewById(R.id.volunteer_title);
        textViewDate = (TextView) view.findViewById(R.id.volunteer_date);
        textViewOrg = (TextView) view.findViewById(R.id.volunteer_org);
        textViewDes = (TextView) view.findViewById(R.id.volunteer_des);
        imageView = (ImageView) view.findViewById(R.id.imageView);
    }

    public void setTitle(String title) {
        textViewTitle.setText(title);
    }

    public void setDate(String date) {
        textViewDate.setText(date);
    }

    public void setOrganization(String org) {
        textViewOrg.setText(org);
    }

    public void setDescription(String description) {
        textViewDes.setText(description);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + textViewTitle.getText() + "'";
    }
}