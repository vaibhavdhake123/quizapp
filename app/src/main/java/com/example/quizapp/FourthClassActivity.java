package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class FourthClassActivity extends AppCompatActivity {


    private CardView cardEng4, cardMath4, cardGk4, card44, card45;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.question));
        }

        cardEng4 = findViewById(R.id.Eng4);
        cardMath4 = findViewById(R.id.Math4);
        cardGk4 = findViewById(R.id.Gk4);
        card44 = findViewById(R.id.card44);
        card45 = findViewById(R.id.card45);


        cardEng4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Eng4intent = new Intent(FourthClassActivity.this, EnglishQ4Activity.class);
                startActivity(Eng4intent);
            }
        });

        cardMath4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Math4intent = new Intent(FourthClassActivity.this, MathQ4Activity.class);
                startActivity(Math4intent);
            }
        });

        cardGk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Gk4intent = new Intent(FourthClassActivity.this, GkQ4Activity.class);
                startActivity(Gk4intent);
            }
        });

        card44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card44intent = new Intent(FourthClassActivity.this, ArtQ4Activity.class);
                startActivity(card44intent);
            }
        });

        card45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card45intent = new Intent(FourthClassActivity.this, SportQ4Activity.class);
                startActivity(card45intent);
            }
        });

    }
}