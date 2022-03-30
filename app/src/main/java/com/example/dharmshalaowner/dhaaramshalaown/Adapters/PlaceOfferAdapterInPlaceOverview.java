package com.example.dharmshalaowner.dhaaramshalaown.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dharmshalaowner.Domain.DataClass;
import com.example.dharmshalaowner.R;

import java.util.ArrayList;

public class PlaceOfferAdapterInPlaceOverview extends RecyclerView.Adapter<PlaceOfferAdapterInPlaceOverview.ChildPlaceOffersViewHolder> {

    Context context;
    ArrayList<DataClass> placeOfferSelectedData;

    public PlaceOfferAdapterInPlaceOverview(Context context, ArrayList<DataClass> placeOfferSelectedData) {
        this.context = context;
        this.placeOfferSelectedData = placeOfferSelectedData;
    }

    @NonNull
    @Override
    public ChildPlaceOffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_place_offers_items_ui, parent, false);
        return new ChildPlaceOffersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildPlaceOffersViewHolder holder, int position) {
        DataClass data = placeOfferSelectedData.get(position);

        holder.heading.setText(data.getHeading());
        holder.icon.setImageResource(data.getDrawableImageId());
    }

    @Override
    public int getItemCount() {
        return placeOfferSelectedData.size();
    }


    public class ChildPlaceOffersViewHolder extends RecyclerView.ViewHolder {
        TextView heading;
        ImageView icon;

        public ChildPlaceOffersViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.id_offers);
            icon = itemView.findViewById(R.id.id_offersIcon);
        }
    }
}
