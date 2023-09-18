package com.app.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeMenuActivity extends AppCompatActivity {

    Intent intent1, intent2, intent3, intent4, intent5;
    Button btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        intent1 = new Intent(HomeMenuActivity.this, UrShineActivity.class);
        btn1 = findViewById(R.id.btnUrShine);
        btn1.setOnClickListener(view -> startActivity(intent1));

        intent2 = new Intent(HomeMenuActivity.this, VideoMoodActivity.class);
        btn2 = findViewById(R.id.btnUrMovie);
        btn2.setOnClickListener(view -> startActivity(intent2));

        intent3 = new Intent(HomeMenuActivity.this, DiaryActivity.class);
        btn3 = findViewById(R.id.btnDiary);
        btn3.setOnClickListener(view -> startActivity(intent3));

        intent4 = new Intent(HomeMenuActivity.this, UrApotekActivity.class);
        btn4 = findViewById(R.id.btnApotek);
        btn4.setOnClickListener(view -> startActivity(intent4));

        intent5 = new Intent(HomeMenuActivity.this, ChatBotActivity.class);
        btn5 = findViewById(R.id.btnChatBot);
        btn5.setOnClickListener(view -> startActivity(intent5));





        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_home:
                    return true;
                case R.id.bottom_user:
                    startActivity(new Intent(HomeMenuActivity.this, ProfileActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return true;
                case R.id.bottom_reminder:
                    startActivity(new Intent(HomeMenuActivity.this, AlarmActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return true;

            }
            return false;
        });
    }
}