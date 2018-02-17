package com.example.gilangprambudi.myvhisix.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.gilangprambudi.myvhisix.R;

/**
 * Created by Gilang Prambudi on 16/02/2018.
 */

public class ViewHolderMessageInGoing extends RecyclerView.ViewHolder{
    public TextView senderName, messageText;

    public ViewHolderMessageInGoing(View view){
        super(view);
        senderName = view.findViewById(R.id.sender_name);
        messageText = view.findViewById(R.id.message_text);
    }


}
