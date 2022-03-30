package com.example.dharmshalaowner.dhaaramshalaown.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.dharmshalaowner.Domain.BookingOrders;
import com.example.dharmshalaowner.Domain.User;
import com.example.dharmshalaowner.R;
import com.example.dharmshalaowner.dhaaramshalaown.Activities.LoginActivity;
import com.example.dharmshalaowner.dhaaramshalaown.Activities.OrdersFilteringActivity;
import com.example.dharmshalaowner.dhaaramshalaown.Activities.Settings;
import com.example.dharmshalaowner.dhaaramshalaown.Activities.TabActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class profile extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView nav_view;
    androidx.appcompat.widget.Toolbar toolbar;

    Button btnUpdate;
    ImageView imagePro;
    TextView name, email, phone;

    String uid;
    Uri uri;
    String downloadUrl;
   // boolean newUserAnswer;

    FirebaseDatabase db;
    DatabaseReference node;
    FirebaseStorage storage;
    StorageReference uploader;

    private static ArrayList<String> userData = new ArrayList<>();
    private android.app.FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.pName);
        email = view.findViewById(R.id.pMail);
        phone = view.findViewById(R.id.pPhn);


        drawerLayout = view.findViewById(R.id.drawerLayout);
        nav_view = view.findViewById(R.id.nav_view);
        toolbar = view.findViewById(R.id.toolbar);


        filterOrders();


        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

//      Navigation drawer menu
        nav_view.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//      for back pressing activity
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

//      itemclick listner
        nav_view.setNavigationItemSelectedListener(this);


        // profile data fetching
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


        // creating object to get instance of whole database
         db = FirebaseDatabase.getInstance();

         // getting reference of a node from database
        node = db.getReference().child("Owners").child(uid);
        node.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);


                name.setText("NAME :  " +user.getName());
                phone.setText("PH-NO :  " +user.getNumber());
                email.setText("EMAIL :  " +user.getEmail());

                // fetching image from firebase
                Glide.with(getContext()).load(user.getDpUrl()).placeholder(R.drawable.personicon).into(imagePro);

                Toast.makeText(getContext(), "Hello " + user.getName() + " !", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /* profile data fetching*/

        imagePro = view.findViewById(R.id.imagePro);
        imagePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(profile.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(480)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(480, 480)     //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(1);



            }
        });



//        Intent filterIntent = new Intent(getActivity(), OrdersFilteringActivity.class);
//        startActivity(filterIntent);






        return view;
    }









    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
             uri = data.getData();

            uploadImage(); // ubaid code
            imagePro.setImageURI(uri);


        }
    }





    /* ubaid code */
    // UploadImage method
    public void uploadImage() {
        if (uri != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
             storage = FirebaseStorage.getInstance();
             uploader = storage.getReference().child(uid);


            // adding listeners on upload
            // or failure of image
            uploader.putFile(uri)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();

                                    uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            node.child("dpUrl").setValue(uri.toString());


                                        }
                                    });

                                    Toast
                                            .makeText(getContext(),
                                                    "Image Uploaded !!",
                                                    Toast.LENGTH_SHORT)
                                            .show();

                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(getContext(),
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();


                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }

    }
    /* ubaid code */


    public void filterOrders(){



            String  uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            ArrayList<BookingOrders> upcomingOrdersList = new ArrayList<>();
            ArrayList<BookingOrders> ongoingOrdersList = new ArrayList<>();
            ArrayList<BookingOrders> completedOrdersList = new ArrayList<>();
            ArrayList<BookingOrders> cancelledOrdersList = new ArrayList<>();

            ArrayList<String> upcomingOrdersUidList = new ArrayList<>();
            ArrayList<String> ongoingOrdersUidList = new ArrayList<>();
            ArrayList<String> completedOrdersUidList = new ArrayList<>();
            ArrayList<String> cancelledOrdersUidList = new ArrayList<>();




                FirebaseFirestore dbStore = FirebaseFirestore.getInstance();
                dbStore.collection("Dharamsalas")
                        .document(uid)
                        .collection("bookingOrders")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    BookingOrders orders = document.toObject(BookingOrders.class);

                                    Long checkoutMillis = orders.getMillisCheckout();
                                    Long checkinMillis = orders.getMillisCheckin();
                                    Long currentMillis = System.currentTimeMillis();
                                    String bookingStatus = orders.getPaymentType();

                                    // check if booking not cancelled to proceed to filtering.
                                    if( !bookingStatus.equals("cancelled") ) {

                                        if(currentMillis < checkinMillis){
                                            upcomingOrdersList.add(orders);
                                            UpcomingOrdersList.upcomingOrdersList = upcomingOrdersList;

                                            upcomingOrdersUidList.add(document.getId());
                                            UpcomingOrdersList.listOfOrderUid = upcomingOrdersUidList;



                                        }

                                        if( (currentMillis > checkinMillis)  && (currentMillis < checkoutMillis)){
                                            ongoingOrdersList.add(orders);
                                            OngoingOrdersList.ongoingOrdersList = ongoingOrdersList;

                                            ongoingOrdersUidList.add(document.getId());
                                            OngoingOrdersList.listOfOrderUid = ongoingOrdersUidList;

                                        }


                                        if(currentMillis > checkoutMillis){
                                            completedOrdersList.add(orders);
                                            CompletedOrdersList.completedOrdersList = completedOrdersList;

                                            completedOrdersUidList.add(document.getId());
                                            CompletedOrdersList.listOfOrderUid = completedOrdersUidList;

                                        }

                                    }
                                    else{

                                        cancelledOrdersList.add(orders);
                                        CancelledOrdersList.cancelledOrdersList = cancelledOrdersList;

                                        cancelledOrdersUidList.add(document.getId());
                                        CancelledOrdersList.listOfOrderUid = cancelledOrdersUidList;

                                    }









                                }






                            }
                        });









        }






    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:


//

                FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
                rootRef.collection("Dharamsalas").document(uid)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful()){
                                    DocumentSnapshot document = task.getResult();
                                    if(document.exists()){

                                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new about_hotel()).commit();

                                    }
                                    else{

                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        int count = fragmentManager.getBackStackEntryCount();
                                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Toolbar()).commit();


                                    }
                                }
                                else{
                                    Log.e("Operation failed: ", task.getException().getMessage());
                                }
                            }
                        });



                break;



            case R.id.nav_profile:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new profile()).commit();
                break;

            case R.id.nav_share:

                String share = "Hi! download this App using this link ";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT,share);
                startActivity(sendIntent);
                break;

            case R.id.nav_settings:

                Intent ints = new Intent(getActivity(), Settings.class);
                startActivity(ints);


                break;

            case R.id.nav_help:
                Toast.makeText(getActivity()," help! ",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_about_us:
                Toast.makeText(getActivity()," about us! ",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_rate_us:
                Toast.makeText(getActivity()," rate us! ",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.booking_orders:

                Intent tabIntent = new Intent(getActivity(), TabActivity.class);
                startActivity(tabIntent);

                //getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ordersList()).commit();
                break;

        }

        return true;
    }






}

