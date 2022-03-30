package com.example.dharmshalaowner.dhaaramshalaown.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dharmshalaowner.Domain.DataClass;
import com.example.dharmshalaowner.Domain.ImageDataClass;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.Hotel_name;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.NumberOfGuests;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.PlaceQuality;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.PlaceRent;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.PlaceRoomImage;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.PlaceSecurity;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.Upload_image;
import com.example.dharmshalaowner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PlaceOverviewAdapter extends RecyclerView.Adapter<PlaceOverviewAdapter.PlaceOverviewViewHolder> {
    Context context;
    private ArrayList<String> fragmentNames;
    private ArrayList<DataClass> placeDescriptionList;
    private ArrayList<DataClass> placeOffersList;
    ArrayList<String> placeSecurityList;
    private Integer[] numberOfGuestsList;
    private String rent;

    private  ArrayList<String> placequalitylist;
    private  String[] placenamelist;


    private ArrayList<String> placeimagelist;
    private ArrayList<String> placeimagelistroom;


    public PlaceOverviewAdapter(Context context, ArrayList<String> fragmentNames) {
        this.context = context;
        this.fragmentNames = fragmentNames;
    }

    @NonNull
    @Override
    public PlaceOverviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_overview_main_recyclerview_items_ui, parent, false);
        return new PlaceOverviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceOverviewViewHolder holder, int position) {
        String data = fragmentNames.get(position);

        holder.fragmentName.setText(data);

        if(position == 0) {
            placenamelist = Hotel_name.getPlacenamelist();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceNameAdaptor(context, placenamelist));
        }

        else if (position== 1){

            placequalitylist = PlaceQuality.getPlacequalitylist();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new GridLayoutManager(context, 3));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceQualityAdaptor(context, placequalitylist));
        }

        else if(position == 2) {
            placeDescriptionList = PlaceDescriptionAdapter.getSelectedPlaceList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceDescriptionAdapterInPlaceOverview(context, placeDescriptionList));
        }

        else if(position == 3) {
            placeOffersList = ChildPlaceOffersAdapter.getPlaceOfferList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new GridLayoutManager(context, 2));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceOfferAdapterInPlaceOverview(context, placeOffersList));
        }
        else if(position == 4) {
            numberOfGuestsList = NumberOfGuests.getNumberOfGuestsList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new NumberOfGuestsAdapterInPlaceOverview(context, numberOfGuestsList));
        }
        else if(position == 5) {
            rent = PlaceRent.getPlaceRent();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceRentAdapterInPlaceOverview(rent));
        }

        else if(position == 6) {
             placeSecurityList = PlaceSecurity.getPlaceSecurityList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceSecurityAdapterInPlaceOverview(context, placeSecurityList));
        }
        else if (position == 7){

            placeimagelist = Upload_image.getPlaceimagelist();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new GridLayoutManager(context,3));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceImageAdapter(context, placeimagelist));
        }
        else if (position == 8){

            placeimagelistroom = PlaceRoomImage.getPlaceimagelistroom();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new GridLayoutManager(context,3));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceRoomImageAdapter(context, placeimagelistroom));
        }
        else {

        }

    }

    @Override
    public int getItemCount() {
        return fragmentNames.size();
    }

    public class PlaceOverviewViewHolder extends RecyclerView.ViewHolder {
        TextView fragmentName;
        RecyclerView childRecyclerViewPlaceOverview;

        public PlaceOverviewViewHolder(@NonNull View itemView) {
            super(itemView);

            fragmentName = itemView.findViewById(R.id.id_fragmentNamePlaceOverview);
            childRecyclerViewPlaceOverview = itemView.findViewById(R.id.id_childRecyclerViewPlaceOverview);
        }
    }

    public boolean saveTheData() {

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // creating object to get instance of whole database
        FirebaseFirestore dbStore = FirebaseFirestore.getInstance();

        // sending data on firestore
        ArrayList<String> numberOfGuestsList = new ArrayList<>();
        numberOfGuestsList.add("Guests: "+ NumberOfGuests.getNumberOfGuestsList()[0]);
        numberOfGuestsList.add("Beds: "+ NumberOfGuests.getNumberOfGuestsList()[1]);
        numberOfGuestsList.add("Bedrooms: "+ NumberOfGuests.getNumberOfGuestsList()[2]);
        numberOfGuestsList.add("Bathrooms: "+ NumberOfGuests.getNumberOfGuestsList()[3]);
        numberOfGuestsList.add("Private Bathrooms? "+ NumberOfGuests.getNumberOfGuestsList()[4]);

        HashMap<String, Object> hashMapForAllData = new HashMap<>();
        hashMapForAllData.put("PlaceName", placenamelist[0]);
        hashMapForAllData.put("PlaceRent", PlaceRent.getPlaceRent());
        hashMapForAllData.put("PlaceDescriptionList", placeDescriptionList);
        hashMapForAllData.put("PlaceOffersList", ChildPlaceOffersAdapter.getPlaceOfferList());
        hashMapForAllData.put("numberOfGuestsList", numberOfGuestsList);
        hashMapForAllData.put("PlaceQualityList", placequalitylist);
        hashMapForAllData.put("PlaceSecurityList", PlaceSecurity.getPlaceSecurityList());
        hashMapForAllData.put("HotelImages", Upload_image.getPlaceimagelistFirestore());
        hashMapForAllData.put("HotelRoomImages", PlaceRoomImage.getPlaceimagelistroomFirestore());

        dbStore.collection("Dharamsalas")
                .document(uid)
                .set(hashMapForAllData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "Data Saved Successfully!", Toast.LENGTH_SHORT).show();
                    }
                });


        boolean result = true;

        if(!false) result = false;

        if(!result) {

            /* TODO: Write the code here for redirecting user to the home page after saving the data. */
            return true;
        }
        return false;
        //return dataSaved;

    }
}