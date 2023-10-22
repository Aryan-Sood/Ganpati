package com.abhijeet.ganpatiapp.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.abhijeet.ganpatiapp.R;
import com.abhijeet.ganpatiapp.adapters.Aarti_List_Adapter;
import com.abhijeet.ganpatiapp.modelclass.Aarti_List_Model_Class;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aarti_list extends AppCompatActivity {
    CardView backButton;
    FirebaseDatabase database;
    DatabaseReference ref;
    RecyclerView aartiRecyclerView;
    LinearLayoutManager layoutManager;
    List<Aarti_List_Model_Class> list;
    Aarti_List_Adapter adapter;

    StorageReference reference;
    Bitmap bitmap;

    ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aarti_list);

        backButton = findViewById(R.id.materialCardView4);
        aartiRecyclerView = findViewById(R.id.aartiRecyclerView);

        database = FirebaseDatabase.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Aarti");
        reference = FirebaseStorage.getInstance().getReference("images/vishnu.png");
//        image2 = findViewById(R.id.imageView2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


//        fetchImages();
        initData();
    }

    public void initRecyclerView() {
        aartiRecyclerView = findViewById(R.id.aartiRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        aartiRecyclerView.setLayoutManager(layoutManager);
        adapter = new Aarti_List_Adapter(list);
        aartiRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void initData() {
        list = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    list.add(new Aarti_List_Model_Class(convertToBitmap(),ds.getKey().toString()));
                    Log.d(TAG, "onDataChange: " + ds.getKey().toString());
                }
                initRecyclerView();
//                Toast.makeText(Aarti_list.this, "done", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        // Vibrate for 100 milliseconds
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(50);
        }

        super.onBackPressed();
    }

    public void fetchImages() {
        try {
            File file = File.createTempFile("vishnu", ".png");
            reference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                     bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                     image2.setImageBitmap(bitmap);
                    Log.d(TAG, "onSuccess: " + bitmap.toString());

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Aarti_list.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap convertToBitmap() {
        // this function converts the drawable image to bitmap
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spotify);
        Bitmap finalimage = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.2),(int)(bitmap.getHeight()*0.2),true);
        return finalimage;
    }


}
