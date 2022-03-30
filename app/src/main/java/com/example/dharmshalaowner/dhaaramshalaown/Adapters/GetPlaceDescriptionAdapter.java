package com.example.dharmshalaowner.dhaaramshalaown.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dharmshalaowner.Domain.DataClass;
import com.example.dharmshalaowner.R;

import java.util.ArrayList;
import java.util.Locale;

public class GetPlaceDescriptionAdapter extends RecyclerView.Adapter<GetPlaceDescriptionAdapter.ViewHolder> {

    private ArrayList<DataClass> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        //private final TextView textViewDescription;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textViewPlaceDescription);
           // textViewDescription = (TextView) view.findViewById(R.id.textViewPlaceDescription);
        }


        public TextView getTextView() {
            return textView;
        }
      /*  public TextView getTextViewDescription() {
            return textViewDescription;
        } */
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public GetPlaceDescriptionAdapter(ArrayList<DataClass> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.get_place_description_items, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        //viewHolder.getTextViewHeading().setText(localDataSet.get(position).getHeading());  //works
        viewHolder.getTextView().setText(localDataSet.get(position).getHeading().toUpperCase(Locale.ROOT)
                + "\n" +localDataSet.get(position).getDescription());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
