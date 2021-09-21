package com.hadimusthfa.prolog;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;



public class Profile extends AppCompatActivity {

    public static String USER_NAME = "";

    TextView username1, email1, mobile1, dob1;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        dbHelper = new DBHelper(this);


        username1 = findViewById(R.id.idUsrnm1);
        email1 = findViewById(R.id.idEml1);
        mobile1 = findViewById(R.id.idMob1);
        dob1 = findViewById(R.id.idDob1);


        USER_NAME = MainActivity.USER_NAME;


        ActivityCompat.requestPermissions(Profile.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);


        String[] s = dbHelper.readUser(getApplicationContext(), MainActivity.USER_NAME);

        username1.setText(s[0]);
        email1.setText(s[1]);
        mobile1.setText(s[2]);
        dob1.setText(s[3]);


    }







    }

