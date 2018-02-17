package com.example.gilangprambudi.myvhisix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OtherProfileActivity extends AppCompatActivity {


    TextView fullNameText, emailText, addressText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();


        fullNameText = findViewById(R.id.full_name_text);
        fullNameText.setText(intent.getStringExtra("full_name"));

        emailText = findViewById(R.id.email_text);
        emailText.setText(intent.getStringExtra("email_text"));

        addressText = findViewById(R.id.address_text);
        addressText.setText(intent.getStringExtra("address_text"));

    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
