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

public class PlaceImageAdapter extends RecyclerView.Adapter<PlaceImageAdapter.PlaceImageViewHolder> {
    Context context;
    ArrayList<String> placeimagelist;

    public PlaceImageAdapter(Context context, ArrayList<String> placeimagelist) {
        this.context = context;
        this.placeimagelist = placeimagelist;
    }

    public PlaceImageViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_image_items_ui, parent, false);
        return new PlaceImageViewHolder(view);

    }

    @Override
    public void onBindViewHolder( PlaceImageViewHolder holder, int position) {

        String data = placeimagelist.get(position);

    /*   if (position== 0){
           holder.images.setImageURI(Uri.parse(placeimagelist.get(position)));
       }
       else if(position ==1){
           holder.images.setImageURI(Uri.parse(placeimagelist.get(position)));
       }
       else if(position ==2){
           holder.images.setImageURI(Uri.parse(placeimagelist.get(position)));
       }
       else if(position ==3){
           holder.images.setImageURI(Uri.parse(placeimagelist.get(position)));
       }
       else if(position ==4){
           holder.images.setImageURI(Uri.parse(placeimagelist.get(position)));
       }*/

        switch (position) {

            case 0:
                holder.images.setImageURI(Uri.parse(placeimagelist.get(position)));
                break;

            case 1:
                holder.images.setImageURI(Uri.parse(placeimagelist.get(position)));
                break;

            case 2:
                holder.images.setImageURI(Uri.parse(placeimagelist.get(position)));
                break;

        }

        /*switch (position){
            case 0:*/
/*                holder.img_cover.setImageURI(Uri.parse(placeimagelist[position].toString()));*/
               /* break;
            case 1:
                holder.img_cover.setImageURI(Uri.parse(result.toString()));
                break;
            case 2:
                holder.img_cover.setImageURI(Uri.parse(result.toString()));
                break;
            case 3:
                holder.img_cover.setImageURI(Uri.parse(result.toString()));
                break;
            case 4:
                holder.img_cover.setImageURI(Uri.parse(result.toString()));
                break;
        }
*/
    }

    @Override
    public int getItemCount() {
        return placeimagelist.size();
    }

    public class PlaceImageViewHolder extends RecyclerView.ViewHolder {
        ImageView images;

        public PlaceImageViewHolder( View itemView) {
            super(itemView);

            images = itemView.findViewById(R.id.images);

        }
    }
}
