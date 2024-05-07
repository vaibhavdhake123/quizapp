package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class ThirdActivity extends AppCompatActivity {
    private CardView cardEng3, cardMath3, cardGk3, card34, card35;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.question));
        }

        cardEng3 = findViewById(R.id.Eng3);
        cardMath3 = findViewById(R.id.Math3);
        cardGk3 =findViewById(R.id.Gk3);
        card34 = findViewById(R.id.card34);
        card35 = findViewById(R.id.card35);

        cardEng3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Eng3intent = new Intent(ThirdActivity.this, EnglishQ3Activity.class);
                startActivity(Eng3intent);
            }
        });

        cardMath3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Math3intent = new Intent(ThirdActivity.this, MathQ3Activity.class);
                startActivity(Math3intent);
            }
        });

        cardGk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Gk3intent = new Intent(ThirdActivity.this, GkQ3Activity.class);
                startActivity(Gk3intent);
            }
        });

        card34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card34intent = new Intent(ThirdActivity.this, ArtQ3Activity.class);
                startActivity(card34intent);
            }
        });

        card35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card35intent = new Intent(ThirdActivity.this, SportQ3Activity.class);
                startActivity(card35intent);
            }
        });
    }
}