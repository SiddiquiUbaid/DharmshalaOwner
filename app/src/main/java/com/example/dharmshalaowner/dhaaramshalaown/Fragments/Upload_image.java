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
import android.widget.ProgressBar;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class Upload_image extends Fragment {
    ImageView image_COVER,image_HOTEL1,image_HOTEL2;
    Button UPLOAD1,UPLOAD2,UPLOAD3;
    private int CurrentProgress = 0;
    private ProgressBar progressBar ;


    /* Ubaid code */
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    FirebaseStorage storage ;
    StorageReference uploader ;
    /* Ubaid code */


    private  static ArrayList<String> placeimagelist = new ArrayList<>();
    private  static ArrayList<String> placeimagelistFirestore = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload_image, container, false);
        image_COVER=view.findViewById(R.id.img_cover);
        image_HOTEL1=view.findViewById(R.id.img_hotel_1);
        image_HOTEL2=view.findViewById(R.id.img_hotel_2);

        UPLOAD1=view.findViewById(R.id.upload1);
        UPLOAD2=view.findViewById(R.id.upload2);
        UPLOAD3=view.findViewById(R.id.upload3);

        /*progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentProgress = CurrentProgress+10;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(50);
            }
        });*/


        UPLOAD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Upload_image.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	 //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(1);



            }
        });

        UPLOAD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Upload_image.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	 //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(2);

            }
        });

        UPLOAD3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Upload_image.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	 //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(3);

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


            image_COVER.setImageURI(uri);


            String imageName = "imageCover"; // ubaid  code
            uploadImage(uri, imageName); // ubaid code

            placeimagelist = Upload_image.getPlaceimagelist();
            placeimagelist.add(String.valueOf(uri));






        }else if (requestCode==2) {

            Uri uri= data.getData();

            String imageName = "imageHotel1";  //ubaid code
            uploadImage(uri, imageName);  // ubaid code

            image_HOTEL1.setImageURI(uri);

            placeimagelist = Upload_image.getPlaceimagelist();
            placeimagelist.add(String.valueOf(uri));



        }
        else if (requestCode==3) {

            Uri uri= data.getData();

            String imageName = "imageHotel2"; // ubaid code
            uploadImage(uri, imageName); // ubaid code

            image_HOTEL2.setImageURI(uri);

            placeimagelist = Upload_image.getPlaceimagelist();
            placeimagelist.add(String.valueOf(uri));



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
                                            placeimagelistFirestore = Upload_image.getPlaceimagelistFirestore();
                                            placeimagelistFirestore.add(String.valueOf(uri));




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





    public static ArrayList<String> getPlaceimagelist() {

        return placeimagelist;
    }

    public static ArrayList<String> getPlaceimagelistFirestore() {

        return placeimagelistFirestore;
    }
}