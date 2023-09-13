package com.abhijeet.ganpatiapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.abhijeet.ganpatiapp.R;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;

    TextView nameTextView, emailTextView, phoneTextView, addressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        nameTextView = findViewById(R.id.textView10);
        emailTextView = findViewById(R.id.textView11);
        phoneTextView = findViewById(R.id.textView12);
        addressTextView = findViewById(R.id.textView13);

        CardView back = findViewById(R.id.materialCardView6);
        back.setOnClickListener(view -> back());

//        DatabaseReference ref = database.getReference().child(firebaseAuth.getUid());
        DatabaseReference ref = database.getReference().child("Users").child(firebaseAuth.getUid());



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Toast.makeText(Profile.this, dataSnapshot.child("Name").getValue(String.class), Toast.LENGTH_SHORT).show();
                nameTextView.setText(dataSnapshot.child("Name").getValue(String.class));
                emailTextView.setText(dataSnapshot.child("Email").getValue(String.class));
                phoneTextView.setText(dataSnapshot.child("Phone").getValue(String.class));
                addressTextView.setText(dataSnapshot.child("Address").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void back(){
        Intent intent = new Intent(Profile.this, MainActivity.class);
        startActivity(intent);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(50);
            }
        }
    }
}