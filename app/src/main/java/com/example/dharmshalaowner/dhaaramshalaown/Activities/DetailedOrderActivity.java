package com.example.dharmshalaowner.dhaaramshalaown.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dharmshalaowner.Domain.BookingOrders;
import com.example.dharmshalaowner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DetailedOrderActivity extends AppCompatActivity {
    String orderUid;
    BookingOrders orderData;
    FirebaseAuth fAuth;
    String uid;
    TextView checkin, checkout, guests, amount, amountType,
            rooms, nights, userName, userPhone, bookingId;
    FloatingActionButton fabCallUser;

    private int CALL_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_order_activity);

        checkin = findViewById(R.id.checkinDate);
        checkout = findViewById(R.id.checkoutDate);
        guests = findViewById(R.id.numberOfGuests);
        rooms = findViewById(R.id.numberOfRooms);
        amount = findViewById(R.id.totalAmount);
        amountType = findViewById(R.id.paymentType);
        nights = findViewById(R.id.totalNights);
        userName = findViewById(R.id.txtUserName);
        userPhone = findViewById(R.id.userPhone);
        bookingId = findViewById(R.id.bookingId);
        fabCallUser = findViewById(R.id.fabCallUser);







        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getCurrentUser().getUid();


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            orderUid = bundle.getString("orderUid");
        }
        else{
            orderUid = "nothing";
        }

        // getting hotel data from firebase
        FirebaseFirestore mstore = FirebaseFirestore.getInstance();
        mstore.collection("Dharamsalas")
                .document(uid)
                .collection("bookingOrders")
                .document(orderUid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.isSuccessful()){
                            orderData = task.getResult().toObject(BookingOrders.class);

                            userName.setText(orderData.getUserName());
                            userPhone.setText(orderData.getUserPhone());
                            checkin.setText(orderData.getCheckin());
                            checkout.setText(orderData.getCheckout());
                            nights.setText(orderData.getTotalNights());
                            amount.setText("₹"+orderData.getPaymentAmount());
                            amountType.setText(orderData.getPaymentType());
                            rooms.setText(orderData.getRooms());
                            guests.setText(orderData.getGuests());
                            bookingId.setText("Booking id: "+orderData.getCustomerUid().substring(0, 14));






                        }
                        else{

                            Toast.makeText(getApplicationContext(), "Error getting data", Toast.LENGTH_SHORT).show();
                            Log.w("TAG", "Error getting data", task.getException());
                        }

                    }
                });



        fabCallUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(DetailedOrderActivity.this,
                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(DetailedOrderActivity.this, "calling your guest...",
                            Toast.LENGTH_SHORT).show();
                    makePhoneCall();

                } else {
                    requestStoragePermission();
                }




            }
        });












    }


    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CALL_PHONE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to make a call with your guest")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(DetailedOrderActivity.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, CALL_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                makePhoneCall();

            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void makePhoneCall(){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+orderData.getUserPhone()));
        startActivity(callIntent);

    }



}