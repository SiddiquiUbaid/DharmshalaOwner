package com.example.dharmshalaowner.dhaaramshalaown.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.dharmshalaowner.R;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.Toolbar;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.about_hotel;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class NavHome extends AppCompatActivity {

    //String uid;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new profile()).commit();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.Home:




                        String uid = FirebaseAuth.getInstance().getUid();
                        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
                        rootRef.collection("Dharamsalas").document(uid)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if(task.isSuccessful()){

                                            DocumentSnapshot document = task.getResult();
                                            if(document.exists()){
                                                Fragment selectedFragment = null;
                                                selectedFragment = new about_hotel();

                                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                selectedFragment).commit();

                                            }
                                            else{

                                                Toast.makeText(getApplicationContext(), "Add your hotel details here", Toast.LENGTH_LONG).show();
                                                Fragment selectedFragment = null;
                                                selectedFragment =  new Toolbar();

                                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                        selectedFragment).commit();


                                            }

                                        }
                                        else {
                                            Log.e("Operation failed: ", task.getException().getMessage());
                                        }

                                    }
                                });

                        break;


                    case R.id.Profile:
                        selectedFragment = new profile();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        break;
                }


                return true;
            };
}
