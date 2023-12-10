package com.abhijeet.ganpatiapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abhijeet.ganpatiapp.R;
import com.abhijeet.ganpatiapp.adapters.Aarti_List_Adapter;
import com.abhijeet.ganpatiapp.adapters.PujaListViewAdapter;
import com.abhijeet.ganpatiapp.modelclass.Aarti_List_Model_Class;
import com.abhijeet.ganpatiapp.modelclass.Puja_List_View_model_Class;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class puja_list_view extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    List<Puja_List_View_model_Class> list;

    RecyclerView puja_list_recycler_view;
    LinearLayoutManager layoutManager;
    PujaListViewAdapter adapter;
    CardView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puja_list_view);

        list = new ArrayList<>();
        backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Puja").child(name);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    String x = ds.getKey();
                    String y = ds.getValue().toString();
                    list.add(new Puja_List_View_model_Class(x,y));
                }

                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void initRecyclerView() {
        puja_list_recycler_view = findViewById(R.id.puja_view_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        puja_list_recycler_view.setLayoutManager(layoutManager);
        adapter = new PujaListViewAdapter(list);
        puja_list_recycler_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}