package com.learningkids.quizapp.screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.learningkids.quizapp.R;

public class HomeActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CardView EngQ, EnvQ, GkQ, ArtQ;


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EngQ = findViewById(R.id.EnglishQ);
        EnvQ = findViewById(R.id.EnvQ);
        GkQ = findViewById(R.id.GKQ);
        ArtQ = findViewById(R.id.ArtQ);

        EngQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EngQusetion = new Intent(HomeActivity.this, SelectScreen.class);
                startActivity(EngQusetion);
            }
        });

        EnvQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EnvQuestion = new Intent(HomeActivity.this, SelectScreen.class);
                startActivity(EnvQuestion);
            }
        });

        GkQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GkQuestion = new Intent(HomeActivity.this, SelectScreen.class);
                startActivity(GkQuestion);
            }
        });

        ArtQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ArtQuestion = new Intent(HomeActivity.this, SelectScreen.class);
                startActivity(ArtQuestion);
            }
        });

    }
}