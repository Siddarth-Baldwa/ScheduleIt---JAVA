package com.example.register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    TextView updatetext, updatever;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        progressDialog = new ProgressDialog(com.example.register.UpdateActivity.this);
        progressDialog.setTitle("Update");
        progressDialog.setMessage("Checking for updates...");
        progressDialog.show();
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                updatetext = findViewById(R.id.updatetext);
                updatetext.setText("You are up to date! Check again later");
                updatever = findViewById(R.id.updatever);
                updatever.setText("Current version v1.0.1");
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 2500);
    }
}
