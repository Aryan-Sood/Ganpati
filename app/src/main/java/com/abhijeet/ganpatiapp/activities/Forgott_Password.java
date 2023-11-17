package com.abhijeet.ganpatiapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abhijeet.ganpatiapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

public class Forgott_Password extends AppCompatActivity {

    private CardView forgetBtn;
    private EditText txtEmail;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgott_password);


        auth= FirebaseAuth.getInstance();

        txtEmail = findViewById(R.id.forgot_email);
        forgetBtn = findViewById(R.id.forgot_button);

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });

    }

    private void validateData(){
        email = txtEmail.getText().toString();
        if (email.isEmpty()){
            txtEmail.setError("Required");
        }else {
            forgetPass();
        }
    }

    private void forgetPass() {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Forgott_Password.this, "Check your Email.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Forgott_Password.this, Login.class));
                            finish();
                        }else {
                            Toast.makeText(Forgott_Password.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}