package com.hadimusthfa.prolog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    public static String USER_NAME = "";
    EditText username, password;
    CardView signIn;
    TextView signUp, txtDont;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);

        signUp = findViewById(R.id.signUp);
        txtDont = findViewById(R.id.txtDont);


        dbHelper = new DBHelper(this);


        signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String usrnm = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                Boolean res = dbHelper.checkUser(usrnm, pass);

                if(usrnm.isEmpty())
                {
                    username.setError("User Name cannot be empty");

                }

                else if(pass.isEmpty())
                {
                    password.setError("Password cannot be empty");
                }


                if (res==true)
                {
                    USER_NAME = usrnm;
                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Profile.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Username or password is incorrect", Toast.LENGTH_SHORT).show();
                }


            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        txtDont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });





    }




}