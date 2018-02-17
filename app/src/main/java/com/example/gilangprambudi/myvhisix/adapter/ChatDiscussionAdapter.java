package com.example.gilangprambudi.myvhisix.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gilangprambudi.myvhisix.R;
import com.example.gilangprambudi.myvhisix.model.Message;
import com.example.gilangprambudi.myvhisix.viewholder.ViewHolderMessageInGoing;
import com.example.gilangprambudi.myvhisix.viewholder.ViewHolderMessageOutGoing;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * Created by Gilang Prambudi on 16/02/2018.
 */

public class ChatDiscussionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    // The items to display in your RecyclerView
    private List<Message> messages;

    private final int INGOING = 0, OUTGOING = 1;

    private FirebaseAuth auth;
    // Provide a suitable constructor (depends on the kind of dataset)

    public ChatDiscussionAdapter(List<Message> messages) {
        this.messages = messages;
        auth = FirebaseAuth.getInstance();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.messages.size();
    }

    @Override
    public int getItemViewType(int position) {


        String email = messages.get(position).getSenderEmail();

        if (!email.equals(auth.getCurrentUser().getEmail())) {
            return INGOING;
        } else{
            return OUTGOING;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case INGOING:
                View v1 = inflater.inflate(R.layout.discussion_chat_ingoing_row, viewGroup, false);
                viewHolder = new ViewHolderMessageInGoing(v1);
                break;
            case OUTGOING:
                View v2 = inflater.inflate(R.layout.discussion_chat_outgoing_row, viewGroup, false);
                viewHolder = new ViewHolderMessageOutGoing(v2);
                break;
            default:
                View v3 = inflater.inflate(R.layout.discussion_chat_ingoing_row, viewGroup, false);
                viewHolder = new ViewHolderMessageInGoing(v3);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case INGOING:
                ViewHolderMessageInGoing vh1 = (ViewHolderMessageInGoing) viewHolder;
                configureViewHolderInGoing(vh1, position);
                break;
            case OUTGOING:
                ViewHolderMessageOutGoing vh2 = (ViewHolderMessageOutGoing) viewHolder;
                configureViewHolderOutGoing(vh2, position);
                break;
            default:
                ViewHolderMessageOutGoing vh3 = (ViewHolderMessageOutGoing) viewHolder;
                configureViewHolderOutGoing(vh3, position);
                break;
        }
    }

    private void configureViewHolderOutGoing(ViewHolderMessageOutGoing vh1, int position) {
       Message message = messages.get(position);
       vh1.messageText.setText(message.getMessageText());


       if(position > 0){
           Message messageTemp = messages.get(position - 1);
           if(messageTemp != null){
                if(messageTemp.getSenderEmail().equals(message.getSenderEmail())){
                    vh1.senderName.setText("");
                }else{
                    vh1.senderName.setText("Me");
                }
           }
       }
    }

    private void configureViewHolderInGoing(ViewHolderMessageInGoing vh1, int position) {
        Message message = messages.get(position);
        vh1.messageText.setText(message.getMessageText());
        vh1.senderName.setText(message.getSenderName());

        if(position > 0){
            Message messageTemp = messages.get(position - 1);
            if(messageTemp != null){
                if(messageTemp.getSenderEmail().equals(message.getSenderEmail())){
                    vh1.senderName.setText("");
                }else{
                    vh1.senderName.setText(message.getSenderName());
                }
            }
        }
    }

}
