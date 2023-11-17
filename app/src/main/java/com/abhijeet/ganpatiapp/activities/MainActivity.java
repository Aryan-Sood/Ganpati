package com.abhijeet.ganpatiapp.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.abhijeet.ganpatiapp.R;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.abhijeet.ganpatiapp.adapters.ViewPagerAdapter;
import com.abhijeet.ganpatiapp.modelclass.PackageChecker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    CardView chat;
    TextView mainText;

    ImageView image, buttonTest;
    StorageReference reference;
    ScrollView scrollView;
    ViewPager viewPager;
    String personalUID="";

    CardView aarti_spotify, chalisha_spotify, shiv_tandav_spotify, krishna_leela_spotify, shrimadbhgwat_geeta_spotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//whatshapp
        chat = findViewById(R.id.whatshapp_icon);

        mainText = findViewById(R.id.mainText);
        database = FirebaseDatabase.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        reference = FirebaseStorage.getInstance().getReference("Main_Activity_Card/Main_Image.png");
        scrollView = findViewById(R.id.scrollView);
//      buttonTest = findViewById(R.id.buttonTest);

        image = findViewById(R.id.imageView4);
        viewPager = findViewById(R.id.viewPager);

        setCurrentUID();

        checkAndSetProfileImage();

        //Spotify
        aarti_spotify = findViewById(R.id.aarti_spotify);
        shiv_tandav_spotify = findViewById(R.id.shiv_tandav_spotify);
        chalisha_spotify = findViewById(R.id.chalisha_spotify);
        krishna_leela_spotify = findViewById(R.id.krishna_leela_spotify);
        shrimadbhgwat_geeta_spotify = findViewById(R.id.shrimadbhgwat_geeta_spotify);


        DatabaseReference ref = database.getReference().child("Aarti");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),getApplicationContext());
        viewPager.setAdapter(adapter);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem()<2){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
                else{
                    viewPager.setCurrentItem(0);
                }
                handler.postDelayed(this::run,10000);
            }
        },10000);



        //Spotify Song Link
        aarti_spotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://open.spotify.com/playlist/6NltPE7H3tit05CUloVPmW");

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

        //isWhatsappInstalled();

        shiv_tandav_spotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://open.spotify.com/show/7kXTwW1xCVei6efFjQgINx");

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


        chalisha_spotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://open.spotify.com/track/6H7fLdt0AeWpuxUKXuXWrx");

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

        krishna_leela_spotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://open.spotify.com/show/7kXTwW1xCVei6efFjQgINx");

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


        shrimadbhgwat_geeta_spotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://open.spotify.com/episode/7cUOuLoBAEWnUjSuEQT8Zk");

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



        //checkOffset();

        CardView profile = findViewById(R.id.cardView3);
        profile.setOnClickListener(view -> profile());

        CardView setting = findViewById(R.id.cardView);
        setting.setOnClickListener(view -> setting());


//whatshapp
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://wa.me/+917255017217");

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


        try {
            File file = File.createTempFile("tempfile", ".png");
            reference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    image.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void profile(){
        Intent intent = new Intent(MainActivity.this, Profile.class);
        startActivity(intent);

        // for animation
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(50);
            }
        }
    }

    public void setting(){
        Intent intent = new Intent(MainActivity.this, settings.class);
        startActivity(intent);

        // for animation
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(50);
            }
        }
    }




    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    /* public void checkOffset(){
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) scrollView.getChildAt(scrollView.getChildCount()-1);
                int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

                if (diff==0){
                    Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                }
            }
        });
    } */

    public void gotourl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void setCurrentUID() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        try {
            personalUID = firebaseUser.getUid();
        }
        catch (Exception e){
            Log.d(TAG, "setCurrentUID: " + e.getMessage());
        }
    }

    public void checkAndSetProfileImage(){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference().child("profileData");

//        Log.d("TAG", "checkAndSetProfileImage: yayyy");

        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
//                Log.d("TAG", "onSuccess: step");
//                Toast.makeText(MainActivity.this, "size is: " + listResult.getPrefixes().size(), Toast.LENGTH_SHORT).show();
                for (StorageReference item: listResult.getPrefixes()){
                    Log.d("TAG", item.getName());
                    Log.d("TAG", "token is :" + personalUID);
                    if (item.getName().equals(personalUID)){
                        Log.d("TAG", "onSuccess: user  found");
                        item.child("profileimage.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                SharedPreferences sharedPreferences = getSharedPreferences("userData",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("profilePicture",UriToString(uri));
                                editor.commit();
                            }
                        });
                    }

                    else{
                        Log.d("TAG", "onSuccess: user not found");
                    }
                }
//                Log.d("TAG", "final");
            }
        });

    }


    public String UriToString(Uri uri){
        try {
            InputStream imageStream = getApplicationContext().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            Log.e("ImageUtils", "File not found: " + e.getMessage());
            return null;
        }
    }

    private boolean isWhatsappInstalled() {
        PackageManager packageManager = getPackageManager();
        boolean whatsappInstalled;

        if (PackageChecker.isPackageInstalled(this, "com.whatsapp")){
            Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}