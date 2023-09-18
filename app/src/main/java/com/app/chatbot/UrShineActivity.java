package com.app.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.app.chatbot.Adapter.UrShineAdapter;

import com.app.chatbot.Model.UserDetails;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UrShineActivity extends AppCompatActivity {

    ArrayList<UserDetails> list;
    private RecyclerView messagesRv;
    private UrShineAdapter urShineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ur_shine);

        list = new ArrayList<>();

        // Set Up Recycler View
        urShineAdapter = new UrShineAdapter(this, list);
        messagesRv = findViewById(R.id.rvDoctor);
        messagesRv.setAdapter(urShineAdapter);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));

        if (equals("docter")){
            user();
        } else {
            docter();
        }
    }

    private void user() {
        Query query = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("role")
                .equalTo("user");
        query.addListenerForSingleValueEvent(valueEventListener1);
    }

    private void docter() {
        Query query = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("role")
                .equalTo("doctor");
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener1 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            urShineAdapter.clear();
            if (snapshot.exists()){
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String uid = dataSnapshot.getKey();

                    if (!uid.equals(FirebaseAuth.getInstance().getUid())) {
                        UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                        list.add(userDetails);
                    }
                    urShineAdapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            urShineAdapter.clear();
            if (snapshot.exists()){
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String uid = dataSnapshot.getKey();

                    if (!uid.equals(FirebaseAuth.getInstance().getUid())) {
                        UserDetails uDetail = dataSnapshot.getValue(UserDetails.class);
                        list.add(uDetail);
                    }
                    urShineAdapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}