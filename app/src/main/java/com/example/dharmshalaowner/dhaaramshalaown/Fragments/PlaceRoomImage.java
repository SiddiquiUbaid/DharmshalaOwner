package com.example.dharmshalaowner.dhaaramshalaown.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dharmshalaowner.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class PlaceRoomImage extends Fragment {
    ImageView image_ROOM1,image_ROOM2;
    Button UPLOAD1,UPLOAD2;

    /* Ubaid code */
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    FirebaseStorage storage ;
    StorageReference uploader ;
    /* Ubaid code */


    private  static ArrayList<String> placeimagelistroom = new ArrayList<>();
    private  static ArrayList<String> placeimagelistroomFirestore = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_room_image, container, false);

        image_ROOM1=view.findViewById(R.id.img_room_1);
        image_ROOM2=view.findViewById(R.id.img_room_2);

        UPLOAD1=view.findViewById(R.id.upload1);
        UPLOAD2=view.findViewById(R.id.upload2);


        UPLOAD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(PlaceRoomImage.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	 //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(1);

            }
        });

        UPLOAD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(PlaceRoomImage.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	 //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(2);
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1)
        {
            Uri uri= data.getData();

            String imageName = "imageRoom1"; // ubaid  code
            uploadImage(uri, imageName); // ubaid code

            image_ROOM1.setImageURI(uri);

            placeimagelistroom = PlaceRoomImage.getPlaceimagelistroom();
            placeimagelistroom.add(String.valueOf(uri));




        }else if (requestCode==2) {

            Uri uri= data.getData();

            String imageName = "imageRoom2"; // ubaid  code
            uploadImage(uri, imageName); // ubaid code

            image_ROOM2.setImageURI(uri);

            placeimagelistroom = PlaceRoomImage.getPlaceimagelistroom();
            placeimagelistroom.add(String.valueOf(uri));



        }
}




    /* Ubaid code */

    // UploadImage method
    public void uploadImage(Uri uri, String imageName) {
        if (uri != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            storage = FirebaseStorage.getInstance();
            uploader = storage.getReference().child(uid).child("HotelImages/").child(imageName);


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

                                            placeimagelistroomFirestore = PlaceRoomImage.getPlaceimagelistroomFirestore();
                                            placeimagelistroomFirestore.add(String.valueOf(uri));



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
    /* Ubaid code */





    public static ArrayList<String> getPlaceimagelistroom() {

        return placeimagelistroom;
    }

    public static ArrayList<String> getPlaceimagelistroomFirestore() {

        return placeimagelistroomFirestore;
    }
}
