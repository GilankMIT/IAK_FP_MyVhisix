package com.example.gilangprambudi.myvhisix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gilangprambudi.myvhisix.adapter.StudentAdapter;
import com.example.gilangprambudi.myvhisix.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyClassActivity extends AppCompatActivity {
    List<User> userList = new ArrayList<>();

    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    TextView memberCount;
    Button discussionBtn;

    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        memberCount = findViewById(R.id.memberCount);
        recyclerView = findViewById(R.id.student_recycler);
        studentAdapter = new StudentAdapter(userList);
        discussionBtn = findViewById(R.id.discussionBtn);
        Intent intent = getIntent();

        final String myEmail = intent.getStringExtra("email");
        final String myName = intent.getStringExtra("name");

        discussionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getBaseContext(), DiscussionActivity.class).putExtra("email", myEmail)
                                                                                    .putExtra("name", myName));

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(studentAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                public boolean onSingleTapUp(MotionEvent e){
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)){
                    int position = rv.getChildAdapterPosition(child);

                    User user = userList.get(position);
                    startActivity(new Intent(getBaseContext(), OtherProfileActivity.class).putExtra("full_name", user.getName())
                                                                                            .putExtra("address_text", user.getAddress())
                                                                                            .putExtra("email_text", user.getEmail()));
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                memberCount.setText(dataSnapshot.getChildrenCount() + " Members" );

                userList.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    userList.add(user);
                }

                studentAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });


    }





    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
