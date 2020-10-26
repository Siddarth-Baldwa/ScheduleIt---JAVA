package com.example.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button callSignUp, login_btn;
    TextView logotext, slogantext;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.signup_screen);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        slogantext = findViewById(R.id.slogan_name);
        logotext = findViewById(R.id.logo_name);
        login_btn = findViewById(R.id.login_btn);

        callSignUp.setOnClickListener((view) -> {
            Intent intent = new Intent(Login.this, SignUp.class);

            Pair[] pairs = new Pair[6];

            pairs[0] = new Pair<View, String>(logotext, "logo_name");
            pairs[1] = new Pair<View, String>(slogantext, "logo_slog");
            pairs[2] = new Pair<View, String>(username, "logo_username");
            pairs[3] = new Pair<View, String>(password, "tran_password");
            pairs[4] = new Pair<View, String>(login_btn, "button_tran");
            pairs[5] = new Pair<View, String>(callSignUp, "login_signup_tran");

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });

    }

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        //Validate Login Info
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        Log.i("MYLOGS","IN ISUSERMETHOD");

        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    Log.i("MY PASSWORD","before calling db");
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    Log.i("MY PASSWORD","after calling db");



                    if (passwordFromDB.equals(userEnteredPassword)) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                      /*  String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneno").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String professionFromDB = dataSnapshot.child(userEnteredUsername).child("profession").getValue(String.class);*/
                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                        /*.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneno", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);
                        intent.putExtra("profession", professionFromDB);*/
                        startActivity(intent);
                    } else {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
                    username.setError("No such User exist");
                    username.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){


                Log.e("ERROR OCCURED IN CODE","Error occured while retreiving data from DB");
            }
        });
    }
}
