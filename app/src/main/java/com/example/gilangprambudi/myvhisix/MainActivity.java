package com.example.gilangprambudi.myvhisix;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gilangprambudi.myvhisix.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private TextView welcomeText;
    private Button btnMyProfile, btnMyClass, btnSignOut;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        welcomeText = findViewById(R.id.welcomeText);
        btnMyProfile = findViewById(R.id.btnMyProfile);
        btnMyClass = findViewById(R.id.btnMyClass);
        btnSignOut = findViewById(R.id.btnSignOut);


        if(auth.getCurrentUser() != null){

            myRef.child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    user = dataSnapshot.getValue(User.class);
                    welcomeText.setText("Welcome " + user.getName());
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

        }else{
            Intent myIntent = new Intent(this, RegisterActivity.class);
            startActivity(myIntent);
        }









        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
            }
        });



        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ProfileActivity.class)
                        .putExtra("uid", auth.getCurrentUser().getUid())
                        .putExtra("email", auth.getCurrentUser().getEmail()));
            }
        });

        btnMyClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), MyClassActivity.class).putExtra("email", auth.getCurrentUser().getEmail())
                                                                                .putExtra("name", user.getName()));
            }
        });





        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

    }




    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
