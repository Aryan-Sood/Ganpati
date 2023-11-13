package com.abhijeet.ganpatiapp.bottomsheets;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.abhijeet.ganpatiapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.ByteArrayOutputStream;
import java.util.Base64;


public class pictureFragment extends BottomSheetDialogFragment {

    ImageView galleryIcon, cameraIcon;

    int SELECT_PICTURE = 200;
    int CAMERA_REQ_CODE = 100;

    byte[] image;
    String images="";

    public pictureFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cameraIcon = view.findViewById(R.id.imageView9);
        galleryIcon = view.findViewById(R.id.imageView10);


        cameraIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQ_CODE);
                dismiss();
            }
        });


        galleryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_PICTURE);
                dismiss();
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri imageUri = data.getData();
                image =convertImageToByteArray(imageUri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    images = Base64.getEncoder().encodeToString(image);
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("profilePicture", images);
                    editor.commit();
                }
            }

            else if (requestCode==CAMERA_REQ_CODE){
                Bitmap img = (Bitmap) (data.getExtras().get("data"));
                image =convertBitmapToByteArray(img);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    images = Base64.getEncoder().encodeToString(image);
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("profilePicture",images);
                    editor.commit();
                }

            }
        }
    }

    public byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // You can change the format and quality as needed
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private byte[] convertImageToByteArray(Uri uri) {
        try {
            // Load the image from URI
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);

            // Compress the image to JPEG format
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

            // Get the binary data from ByteArrayOutputStream
            byte[] imageData = byteArrayOutputStream.toByteArray();

            // Close the ByteArrayOutputStream
            byteArrayOutputStream.close();

            return imageData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}