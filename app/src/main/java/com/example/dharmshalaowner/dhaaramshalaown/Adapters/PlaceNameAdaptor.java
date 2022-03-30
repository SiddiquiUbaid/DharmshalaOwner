package com.example.dharmshalaowner.dhaaramshalaown.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dharmshalaowner.R;

import java.util.ArrayList;

public class PlaceNameAdaptor extends RecyclerView.Adapter<PlaceNameAdaptor.PlaceNameViewHolder> {
    Context context;
    String[] placenamelist;


    public PlaceNameAdaptor(Context context, String[] placenamelist) {
        this.context = context;
        this.placenamelist = placenamelist;
    }

    @Override
    public PlaceNameViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_name_item_ui, parent, false);
        return new PlaceNameViewHolder(view);
    }

    @Override
    public void onBindViewHolder( PlaceNameViewHolder holder, int position) {
            String userdata = placenamelist[position].toString();
            holder.txtInput.setText(userdata);


    }

    @Override
    public int getItemCount() {
        return  placenamelist.length;
    }

    public static class PlaceNameViewHolder extends RecyclerView.ViewHolder {
        TextView txtInput;

        public PlaceNameViewHolder( View itemView) {
            super(itemView);

            txtInput = itemView.findViewById(R.id.txtInput);
        }
    }
}
