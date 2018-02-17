package com.example.gilangprambudi.myvhisix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gilangprambudi.myvhisix.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UserInfoActivity extends AppCompatActivity {

    private EditText email_text, full_name_text, address_text;
    private Button btnSaveInfo;

    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        email_text = findViewById(R.id.email_text);
        full_name_text = findViewById(R.id.full_name_text);
        address_text = findViewById(R.id.address_text);

        btnSaveInfo = findViewById(R.id.btnSaveInfo);

        auth = FirebaseAuth.getInstance();
        // Write a message to the database
        database = FirebaseDatabase.getInstance();

        fillEmail();

        btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference myRef = database.getReference("users");

                User myUser = new User();
                myUser.setName(full_name_text.getText().toString());
                myUser.setAddress(address_text.getText().toString());
                myUser.setEmail(auth.getCurrentUser().getEmail());

                myRef.child(auth.getCurrentUser().getUid()).setValue(myUser);
                startActivity(new Intent(UserInfoActivity.this, MainActivity.class));
            }
        });

    }

    private void fillEmail(){
        email_text.setText(auth.getCurrentUser().getEmail());
    }
}
