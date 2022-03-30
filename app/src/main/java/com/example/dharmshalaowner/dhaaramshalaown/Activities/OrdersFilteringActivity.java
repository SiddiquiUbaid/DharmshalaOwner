package com.example.dharmshalaowner.dhaaramshalaown.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dharmshalaowner.Domain.BookingOrders;
import com.example.dharmshalaowner.R;
import com.example.dharmshalaowner.dhaaramshalaown.Activities.DetailedOrderActivity;
import com.example.dharmshalaowner.dhaaramshalaown.Adapters.OrdersListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;


public class OrdersFilteringActivity extends AppCompatActivity {

    String  uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    BookingOrders orders;
    ArrayList<BookingOrders> bookingOrdersList = new ArrayList<>();

    ArrayList<BookingOrders> upcomingOrdersList = new ArrayList<>();
    ArrayList<BookingOrders> ongoingOrdersList = new ArrayList<>();
    ArrayList<BookingOrders> completedOrdersList = new ArrayList<>();

    ArrayList<String> listOfOrderUid = new ArrayList<>();







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getApplicationContext(), "ighgjsjgs'agja'sla;ga'", Toast.LENGTH_SHORT).show();






        FirebaseFirestore dbStore = FirebaseFirestore.getInstance();
        dbStore.collection("Dharamsalas")
                .document(uid)
                .collection("bookingOrders")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        for (QueryDocumentSnapshot document : task.getResult()) {

                            orders = document.toObject(BookingOrders.class);

                            Long checkoutMillis = orders.getMillisCheckout();
                            Long checkinMillis = orders.getMillisCheckin();
                            Long currentMillis = System.currentTimeMillis();

                            if(currentMillis < checkinMillis){
                                upcomingOrdersList.add(orders);
                                Toast.makeText(getApplicationContext(), "upcoming: "+ orders.getCheckin(), Toast.LENGTH_SHORT).show();

                            }

                            if( (currentMillis > checkinMillis)  && (currentMillis < checkoutMillis)){
                                ongoingOrdersList.add(orders);
                                Toast.makeText(getApplicationContext(), "ongoing: "+ orders.getCheckin(), Toast.LENGTH_SHORT).show();
                            }

                            if(currentMillis > checkoutMillis){
                                completedOrdersList.add(orders);
                                Toast.makeText(getApplicationContext(), "completed: "+ orders.getCheckout(), Toast.LENGTH_SHORT).show();
                            }



                            bookingOrdersList.add(orders);

                            // getting uids of every doc
                            listOfOrderUid.add(document.getId());







                        }









                    }
                });





    }



}