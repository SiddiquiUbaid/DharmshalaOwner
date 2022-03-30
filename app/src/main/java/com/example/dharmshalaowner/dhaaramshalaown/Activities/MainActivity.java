package com.example.dharmshalaowner.dhaaramshalaown.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dharmshalaowner.dhaaramshalaown.Fragments.Hotel_name;
import com.example.dharmshalaowner.R;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.Toolbar;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.about_hotel;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth Fauth;
    FirebaseAuth.AuthStateListener FauthListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ImageView imageView = (ImageView) findViewById(R.id.imageView3);

        imageView.animate().alpha(0f).setDuration(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Fauth = FirebaseAuth.getInstance();
                if (Fauth.getCurrentUser() != null) {
                    Intent intent = new Intent(MainActivity.this, NavHome.class);
                    //Intent intent = new Intent(MainActivity.this,LoginActivity.class);

                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

            }

        },100);

    }








}



