package com.example.gilangprambudi.myvhisix.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gilangprambudi.myvhisix.R;
import com.example.gilangprambudi.myvhisix.model.User;

import java.util.List;

/**
 * Created by Gilang Prambudi on 16/02/2018.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {


    private List<User> userList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fullNameText, emailText;

        public ViewHolder(View view) {
            super(view);
            fullNameText = view.findViewById(R.id.full_name_text);
            emailText = view.findViewById(R.id.email_text);
        }
    }
    public StudentAdapter(List<User> userList){
        this.userList = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.fullNameText.setText(user.getName());
        holder.emailText.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
