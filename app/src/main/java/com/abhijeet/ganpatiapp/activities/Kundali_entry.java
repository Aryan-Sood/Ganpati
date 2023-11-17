package com.abhijeet.ganpatiapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.abhijeet.ganpatiapp.R;

public class Kundali_entry extends AppCompatActivity {

    CardView backButton, Send_Btn;
    EditText Child_Name, Birth_Date, Birth_Time, Birth_Location, Father_Name, Mother_Name, Current_Location, Phone_Number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundali_entry);

        backButton = findViewById(R.id.backButton);
        Child_Name = findViewById(R.id.editText3);
        Birth_Date = findViewById(R.id.editText4);
        Birth_Time = findViewById(R.id.editText5);
        Birth_Location = findViewById(R.id.editText6);
        Father_Name = findViewById(R.id.editText8);
        Mother_Name = findViewById(R.id.editText9);
        Current_Location = findViewById(R.id.editText10);
        Phone_Number = findViewById(R.id.editText11);
        Send_Btn = findViewById(R.id.send_btn);



        Send_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from other EditText views
                String input1 = Child_Name.getText().toString();
                String input2 = Birth_Date.getText().toString();
                String input3 = Birth_Time.getText().toString();
                String input4 = Birth_Location.getText().toString();
                String input5 = Father_Name.getText().toString();
                String input6 = Mother_Name.getText().toString();
                String input7 = Current_Location.getText().toString();
                String input8 = Phone_Number.getText().toString();

                // Combine inputs into a single message
                String messagestr = "DETAIL FOR KUNDALI" + "\n\nChild_Name: " + input1 + "\nBirth_Date: " + input2 + "\nBirth_Time: " + input3 + "\nBirth_Location: " + input4 + "\nFather_Name: " + input5 + "\nMother_Name: " + input6 + "\nCurrent_Location: " + input7 + "\nPhone_Number: " + input8;
                // Combine more inputs into the message if needed

                if (!input1.isEmpty() && !input2.isEmpty() && !input3.isEmpty() && !input4.isEmpty() && !input5.isEmpty() && !input6.isEmpty() && !input7.isEmpty() && !input8.isEmpty()) {

                    if (iswhatsappInstalled()) {

                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=+917255017217&text=" + messagestr));
                        startActivity(i);

                        Child_Name.setText("");
                        Birth_Date.setText("");
                        Birth_Time.setText("");
                        Birth_Location.setText("");
                        Father_Name.setText("");
                        Mother_Name.setText("");
                        Current_Location.setText("");
                        Phone_Number.setText("");

                    } else {
                        Toast.makeText(Kundali_entry.this, "Whatsapp is not installed", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Kundali_entry.this, "Write your Query in the box and try again.", Toast.LENGTH_LONG).show();
                }

                // Vibration code...
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(50);
                    }
                }
            }
        });

//onClickListner for Back Button.
        backButton.setOnClickListener(view -> home());


    }



    private boolean iswhatsappInstalled() {
        PackageManager packageManager = getPackageManager();
        boolean whatsappInstalled;

        try {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            whatsappInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            whatsappInstalled = false;
        }
        // Toast.makeText(this, String.valueOf(whatsappInstalled), Toast.LENGTH_SHORT).show();
        return whatsappInstalled;
    }

//for back button.
    public void home(){
        Intent intent = new Intent(Kundali_entry.this, MainActivity.class);
        startActivity(intent);
// for animation
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
// for vibration
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