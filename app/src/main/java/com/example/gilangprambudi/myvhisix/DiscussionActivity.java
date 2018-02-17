package com.example.gilangprambudi.myvhisix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gilangprambudi.myvhisix.adapter.ChatDiscussionAdapter;
import com.example.gilangprambudi.myvhisix.model.Message;
import com.example.gilangprambudi.myvhisix.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DiscussionActivity extends AppCompatActivity {
    List<Message> messages = new ArrayList<>();
    RecyclerView recyclerView;
    ChatDiscussionAdapter mAdapter;
    FirebaseDatabase database;
    DatabaseReference myRef;

    Button sendBtn;
    EditText messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        mAdapter = new ChatDiscussionAdapter(messages);
        recyclerView = findViewById(R.id.chat_recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        messageText = findViewById(R.id.message_text);
        sendBtn = findViewById(R.id.send_btn);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messages");


        final Intent intent = getIntent();
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message(intent.getStringExtra("name"), messageText.getText().toString(), intent.getStringExtra("email"));

                String uid = myRef.push().getKey();

                myRef.child(uid).setValue(message);
            }
        });



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                messages.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Message message = postSnapshot.getValue(Message.class);
                    messages.add(message);
                }

                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
        mAdapter.notifyDataSetChanged();

    }
}
