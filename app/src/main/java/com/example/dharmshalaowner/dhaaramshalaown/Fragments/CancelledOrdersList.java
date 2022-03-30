package com.example.dharmshalaowner.dhaaramshalaown.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


public class CancelledOrdersList extends Fragment implements OrdersListAdapter.OnOrderLitener {
    RecyclerView recyclerViewForOrderList;
    TextView txtOrdersList;


    public static ArrayList<BookingOrders> cancelledOrdersList = new ArrayList<>();
    public static ArrayList<String> listOfOrderUid = new ArrayList<>();




    public CancelledOrdersList() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orders_list, container, false);

        View viewForEmpty = inflater.inflate(R.layout.empty_list_placeholder, container, false);

        txtOrdersList = view.findViewById(R.id.txtOrdersList);
        txtOrdersList.setText("Cancelled orders");
        txtOrdersList.setTextColor(getResources().getColor(R.color.pinkred));


        recyclerViewForOrderList = view.findViewById(R.id.id_recyclerViewForOrderList);
        recyclerViewForOrderList.setLayoutManager(new LinearLayoutManager(getContext()));
        Toast.makeText(getContext(), "ongoing"+ cancelledOrdersList.size(), Toast.LENGTH_SHORT).show();

        OrdersListAdapter ordersListAdapter = new OrdersListAdapter(getContext(), cancelledOrdersList, CancelledOrdersList.this::onOrderClick);
        recyclerViewForOrderList.setAdapter(ordersListAdapter);
        ordersListAdapter.notifyDataSetChanged();



        // Inflate the layout for this fragment
        if(cancelledOrdersList.isEmpty()){
            return viewForEmpty;
        }
        else {
            return view;
        }
    }

    @Override
    public void onOrderClick(int position) {
        Intent intent = new Intent(getActivity(), DetailedOrderActivity.class);
        intent.putExtra("orderUid", listOfOrderUid.get(position));
        startActivity(intent);

    }
}