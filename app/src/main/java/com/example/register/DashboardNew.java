package com.example.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardNew extends AppCompatActivity implements View.OnClickListener {

    public CardView card1 , card2 , card3 , card4 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_new);
        card1 = findViewById(R.id.todo);
        card2 = findViewById(R.id.impdocuments);
        card3 = findViewById(R.id.settings);
        card4 = findViewById(R.id.logout1);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent i ;

        switch (v.getId()){
            case R.id.todo :
                i = new Intent(this,ToDo.class);
                startActivity(i);
                break;

            case R.id.impdocuments :
                i = new Intent(this,ImpDocuments.class);
                startActivity(i);
                break;

            case R.id.settings :
                i = new Intent(this,Settings1.class);
                startActivity(i);
                break;

            case R.id.logout1 :
                FirebaseAuth.getInstance().signOut();
                i = new Intent(this, Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
        }

    }
}