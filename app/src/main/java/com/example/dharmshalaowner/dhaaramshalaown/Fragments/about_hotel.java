package com.example.dharmshalaowner.dhaaramshalaown.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dharmshalaowner.Domain.DataClass;
import com.example.dharmshalaowner.Domain.HotelData;
import com.example.dharmshalaowner.Domain.ImageDataClass;
import com.example.dharmshalaowner.R;
import com.example.dharmshalaowner.dhaaramshalaown.Adapters.GetPlaceDescriptionAdapter;
import com.example.dharmshalaowner.dhaaramshalaown.Adapters.GetPlaceOffersAdapter;
import com.example.dharmshalaowner.dhaaramshalaown.Adapters.GetPlaceQualityAdapter;
import com.example.dharmshalaowner.dhaaramshalaown.Adapters.GetPlaceSecurityAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class about_hotel extends Fragment {
    String  uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


    TextView txtEdit, txtHotelName, txtRent, txtPlaceDescription0, txtPlaceDescription1;
    TextView txtGuestBeds, txtGuestBedrooms,txtGuestBathrooms,txtGuests,txtGuestPrivateBathroom;
    RecyclerView recyclerViewForPlaceDescriptionList, recyclerViewForPlaceOffers, recyclerViewForPlaceQualities, recyclerViewForPlaceSecurity ;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5;

    HotelData hotelData;

    ListView listViewForPlaceQuality, listViewForPlaceSecurity ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_hotel,container,false);

        txtEdit= view.findViewById(R.id.txtEdit);

        txtHotelName = view.findViewById(R.id.txtHotelName);
        txtRent = view.findViewById(R.id.txtRent);


        txtGuestBeds = view.findViewById(R.id.txtGuestBeds);
        txtGuestBathrooms = view.findViewById(R.id.txtGuestBathrooms);
        txtGuestBedrooms = view.findViewById(R.id.txtGuestBedrooms);
        txtGuests = view.findViewById(R.id.txtGuests);
        txtGuestPrivateBathroom= view.findViewById(R.id.txtGuestPrivateBathroom);

        imageView1 = view.findViewById(R.id.image1);
        imageView2 = view.findViewById(R.id.image2);
        imageView3 = view.findViewById(R.id.image3);
        imageView4 = view.findViewById(R.id.image4);
        imageView5 = view.findViewById(R.id.image5);


        // new




        recyclerViewForPlaceDescriptionList = view.findViewById(R.id.recylerViewForPlaceDescription);
        recyclerViewForPlaceDescriptionList.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewForPlaceOffers = view.findViewById(R.id.recyclerViewForPlaceOffers);
        recyclerViewForPlaceOffers.setLayoutManager(new GridLayoutManager(getContext(), 2));

        recyclerViewForPlaceQualities = view.findViewById(R.id.recyclerViewForPlaceQualities);
        recyclerViewForPlaceQualities.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewForPlaceSecurity = view.findViewById(R.id.recyclerViewForPlaceSecurity);
        recyclerViewForPlaceSecurity.setLayoutManager(new LinearLayoutManager(getContext()));

//        listViewForPlaceQuality = view.findViewById(R.id.listViewForPlaceQuality);
//        listViewForPlaceSecurity = view.findViewById(R.id.listViewForPlaceSecurity);

        //


        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Toolbar()).commit();

            }
        });



         FirebaseFirestore dbStore = FirebaseFirestore.getInstance();
         dbStore.collection("Dharamsalas")
                 .document(uid)
                 .get()
                 .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                     @Override
                     public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                         hotelData = task.getResult().toObject(HotelData.class);

                         txtHotelName.setText(hotelData.getPlaceName());
                         txtRent.setText(hotelData.getPlaceRent());

                         // for guestslist
                         ArrayList<String> numberOfGuestsList = hotelData.getNumberOfGuestsList();

                         txtGuests.setText(numberOfGuestsList.get(0));
                         txtGuestBeds.setText(numberOfGuestsList.get(1));
                         txtGuestBedrooms.setText(numberOfGuestsList.get(2));
                         txtGuestBathrooms.setText(numberOfGuestsList.get(3));

                         // condition for private bathrooms
                         if (numberOfGuestsList.get(4).charAt(19) == '1')
                             txtGuestPrivateBathroom.setText("PRIVATE BATHROOMS? :  " + "Yes");
                         else
                             txtGuestPrivateBathroom.setText("PRIVATE BATHROOMS? :  " + "No");


                         // code for fetching quality list
                         ArrayList<String> placeQualityList = hotelData.getPlaceQualityList();
                         GetPlaceQualityAdapter qualityAdapter = new GetPlaceQualityAdapter(placeQualityList);
                         recyclerViewForPlaceQualities.setAdapter(qualityAdapter);

                         // code for fetching security list
                         ArrayList<String> placeSecurityList = hotelData.getPlaceSecurityList();
                         GetPlaceSecurityAdapter securityAdapter = new GetPlaceSecurityAdapter(placeSecurityList);
                         recyclerViewForPlaceSecurity.setAdapter(securityAdapter);

                         //hotel image fetching
                         ArrayList<String> hotelImageList = hotelData.getHotelImages();
                         Glide.with(getContext()).load(hotelImageList.get(0)).into(imageView1);
                         Glide.with(getContext()).load(hotelImageList.get(1)).into(imageView2);
                         Glide.with(getContext()).load(hotelImageList.get(2)).into(imageView3);

                         //room image fetching
                         ArrayList<String> hotelRoomList = hotelData.getHotelRoomImages();
                         Glide.with(getContext()).load(hotelRoomList.get(0)).into(imageView4);
                         Glide.with(getContext()).load(hotelRoomList.get(1)).into(imageView5);

                         //hotel description fetching
                        ArrayList<DataClass> placeDescriptionList = hotelData.getPlaceDescriptionList();
                         GetPlaceDescriptionAdapter descriptionAdapter = new GetPlaceDescriptionAdapter(placeDescriptionList);
                         recyclerViewForPlaceDescriptionList.setAdapter(descriptionAdapter);

                         //hotel offers fetching
                         ArrayList<DataClass> placeOffersList = hotelData.getPlaceOffersList();
                         GetPlaceOffersAdapter offersAdapter = new GetPlaceOffersAdapter(placeOffersList);
                         recyclerViewForPlaceOffers.setAdapter(offersAdapter);

                     }
                 });


        return view;

    }



}
