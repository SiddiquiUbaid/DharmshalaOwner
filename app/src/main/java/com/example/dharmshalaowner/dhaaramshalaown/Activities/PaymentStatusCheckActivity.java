package com.example.dharmshalaowner.dhaaramshalaown.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dharmshalaowner.R;

public class PaymentStatusCheckActivity extends AppCompatActivity {
    ImageView imgPaymentStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_placed_orders_list_items);

        imgPaymentStatus = (ImageView) findViewById(R.id.imgPaymentSatus);

    }

    public void setPaymentStatus(String status){

        if(status.equals("cancelled")){
            imgPaymentStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_person_24));
        }
        else if(status.equals("atHotel")){
            imgPaymentStatus.setImageDrawable(getResources().getDrawable(R.drawable.air_conditioner));

        }
        else{
            imgPaymentStatus.setImageDrawable(getResources().getDrawable(R.drawable.add));

        }


    }

}
