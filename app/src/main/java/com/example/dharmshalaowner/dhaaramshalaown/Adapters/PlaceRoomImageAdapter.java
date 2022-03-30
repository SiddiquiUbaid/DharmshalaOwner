package com.example.dharmshalaowner.dhaaramshalaown.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dharmshalaowner.R;

import java.util.ArrayList;

public class PlaceRoomImageAdapter extends RecyclerView.Adapter<PlaceRoomImageAdapter.PlaceRoomImageViewHolder> {
    Context context;
    ArrayList<String> placeimagelistroom;

    public PlaceRoomImageAdapter(Context context, ArrayList<String> placeimagelistroom) {
        this.context = context;
        this.placeimagelistroom = placeimagelistroom;
    }

    public PlaceRoomImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_image_items_ui, parent, false);
        return new PlaceRoomImageViewHolder(view);

    }

    @Override
    public void onBindViewHolder( PlaceRoomImageViewHolder holder, int position) {

        String data = placeimagelistroom.get(position);

        switch (position) {

            case 0:
                holder.images.setImageURI(Uri.parse(data));
                break;

            case 1:
                holder.images.setImageURI(Uri.parse(data));
                break;
        }
    }
    @Override
    public int getItemCount() {
        return placeimagelistroom.size();
    }

    public class PlaceRoomImageViewHolder extends RecyclerView.ViewHolder {
        ImageView images;

        public PlaceRoomImageViewHolder( View itemView) {
            super(itemView);

            images = itemView.findViewById(R.id.images);

        }
    }
}

