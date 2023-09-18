package com.app.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.chatbot.Model.UserDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    ImageView profile;
    TextView tvName, tvEmail, tvGender;
    FirebaseUser user;
    FirebaseDatabase fData;
    DatabaseReference dReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_user);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_user:
                    return true;
                case R.id.bottom_home:
                    startActivity(new Intent(ProfileActivity.this, HomeMenuActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return true;
                case R.id.bottom_reminder:
                    startActivity(new Intent(ProfileActivity.this, AlarmActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return true;
            }
            return false;
        });

        auth = FirebaseAuth.getInstance();
        fData = FirebaseDatabase.getInstance();
        dReference = fData.getReference("Registered Users");

        button = findViewById(R.id.logout1);
        tvName = findViewById(R.id.nameProfile1);
        tvGender = findViewById(R.id.tvGender);
        profile = findViewById(R.id.imgProfile);
        tvEmail = findViewById(R.id.emailProfile1);
        user = auth.getCurrentUser();
        
        if (user == null) {
            Intent intent = new Intent(ProfileActivity.this, Login_user.class);
            startActivity(intent);
            finish();
        } else {
            tvEmail.setText(user.getEmail());
            showUserProfile(user);
        }
        button.setOnClickListener(view -> {
            auth.signOut();
            Intent intent = new Intent(ProfileActivity.this, Login_user.class);
            startActivity(intent);
            finish();
        });
    }

    private void showUserProfile(FirebaseUser fUser) {
        String userId = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                  UserDetails uDetail = snapshot.getValue(UserDetails.class);
                if (uDetail != null){
                    String test = uDetail.getUserName();
                    String gender = uDetail.getGender();
                    tvName.setText(test);
                    tvGender.setText(gender);

                    if (gender.equals("male")) {
                        profile.setImageResource(R.drawable.docman);
                    } else {
                        profile.setImageResource(R.drawable.docwomen);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
            public void getGenderProfile(TextView tvName) {

            }
        });
    }
}