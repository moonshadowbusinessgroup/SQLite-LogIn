package com.hadimusthfa.prolog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUp extends AppCompatActivity {

    DBHelper dbHelper;

    CardView signup;
    EditText username, email, moblie, dob, password, repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        dbHelper = new DBHelper(this);

        signup = findViewById(R.id.idSignUp);

        username = findViewById(R.id.idUsrnm);
        email = findViewById(R.id.idEml);
        moblie = findViewById(R.id.idMob);
        dob = findViewById(R.id.idDob);
        password = findViewById(R.id.idPass);
        repass = findViewById(R.id.idRePass);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = username.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Mobile = moblie.getText().toString().trim();
                String Dob =  dob.getText().toString().trim();
                String Password =password.getText().toString().trim();
                String Repassword = repass.getText().toString().trim();

                Log.e("-----------", String.valueOf(Mobile.length()));

                if(Username.isEmpty())
                {
                    username.setError("User Name cannot be empty");

                }

                else if(Email.isEmpty())
                {
                    email.setError("Email cannot be empty");
                }
                else if(Mobile.isEmpty())
                {
                    moblie.setError("Mobile Number cannot be empty");

                }
                else if(!(Mobile.length() >=8))
                {
                    moblie.setError("Mobile Number must 8 Char");
                }
                else if(Dob.isEmpty())
                {
                    dob.setError("Date of Birth cannot be empty");
                }
                else if(Password.isEmpty() || Repassword.isEmpty())
                {
                    password.setError("Password cannot be empty");
                    repass.setError("Password cannot be empty");
                }


                if(!Username.isEmpty() && !Email.isEmpty() && !Mobile.isEmpty() && !Dob.isEmpty() && !Password.isEmpty())
                {

                    if(Password.equals(Repassword))
                    {
                        if(Password.length() >= 8)
                        {
                            long val = dbHelper.addUser(Username, Email, Mobile, Dob, Password);

                            if(val>0)
                            {
                                Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, MainActivity.class);
                                startActivity(intent);
                                username.setText(null);
                                email.setText(null);
                                moblie.setText(null);
                                dob.setText(null);
                                password.setText(null);
                                repass.setText(null);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(SignUp.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(SignUp.this, "Password must to be 8 characters or more!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(SignUp.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });
    }
}
