package com.example.dharmshalaowner.dhaaramshalaown.Activities;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dharmshalaowner.R;

public class Settings extends AppCompatActivity {

    SwitchCompat switchCompat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){

            setTheme(R.style.Theme_DharmshalaDark);
        }else{
            setTheme(R.style.Theme_DharmshalaOwner);
        }



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);





        switchCompat=findViewById(R.id.bt_switch);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else{


                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


                }






            }
        });






    }
}