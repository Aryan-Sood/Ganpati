package com.abhijeet.ganpatiapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abhijeet.ganpatiapp.R;
import com.abhijeet.ganpatiapp.adapters.Aarti_List_Adapter;
import com.abhijeet.ganpatiapp.modelclass.Aarti_List_Model_Class;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aarti_list);

        backButton = findViewById(R.id.materialCardView4);
        aartiRecyclerView = findViewById(R.id.aartiRecyclerView);

        database = FirebaseDatabase.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Aarti");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        initData();
        initRecyclerView();


    }

    public void initRecyclerView(){
        aartiRecyclerView = findViewById(R.id.aartiRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        aartiRecyclerView.setLayoutManager(layoutManager);
        adapter = new Aarti_List_Adapter(list);
        aartiRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void initData(){
        list = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    list.add(new Aarti_List_Model_Class(ds.getKey().toString()));
                    Toast.makeText(Aarti_list.this, ds.getKey().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}