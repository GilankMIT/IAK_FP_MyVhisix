package com.example.gilangprambudi.myvhisix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gilangprambudi.myvhisix.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {


    private EditText fullNameText, addressText;
    private Button btnSaveProfile;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fullNameText = findViewById(R.id.full_name_text);
        addressText = findViewById(R.id.address_text);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        final Intent intent = getIntent();


        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User myUser = new User();
                myUser.setName(fullNameText.getText().toString());
                myUser.setAddress(addressText.getText().toString());
                myUser.setEmail(intent.getStringExtra("email"));


                myRef.child(intent.getStringExtra("uid")).setValue(myUser);
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });




        myRef.child(intent.getStringExtra("uid")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                fullNameText.setText(user.getName());
                addressText.setText(user.getAddress());
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
