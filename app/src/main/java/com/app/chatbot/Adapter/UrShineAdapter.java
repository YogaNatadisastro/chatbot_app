package com.app.chatbot.Adapter;


import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.app.chatbot.DetailChatActivity;
import com.app.chatbot.Model.UserDetails;
import com.app.chatbot.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UrShineAdapter extends RecyclerView.Adapter<UrShineAdapter.ChatViewHolder> {
    private Context context;
    private final ArrayList<UserDetails> list;

    public UrShineAdapter(Context context, ArrayList<UserDetails> list) {
        this.context = context;
        this.list = list;
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UrShineAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor, parent, false);
        return new ChatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UrShineAdapter.ChatViewHolder holder, int position) {
        UserDetails uDetails = list.get(position);

        holder.role.setText(uDetails.getRole());
        holder.nama.setText(uDetails.getUserName());
        holder.gender.setText(uDetails.getUserName());
        holder.gender.setText(uDetails.getGender());
        if (uDetails.getGender().equals("male")) {
            holder.iconDocter.setImageResource(R.drawable.docman);
        } else {
            holder.iconDocter.setImageResource(R.drawable.docwomen);
        }


        //munculkan doctor


        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(context, DetailChatActivity.class);
            intent.putExtra("nama", uDetails.getUserName());
            intent.putExtra("uid", uDetails.getUserId());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        private final TextView gender;
        private final TextView role;
        private final TextView nama;
        private final ImageView iconDocter;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            iconDocter = itemView.findViewById(R.id.iconDocter);
            gender = itemView.findViewById(R.id.lastChat);
            nama = itemView.findViewById(R.id.tvNameDoctor);
            role = itemView.findViewById(R.id.role);
        }
    }
}
