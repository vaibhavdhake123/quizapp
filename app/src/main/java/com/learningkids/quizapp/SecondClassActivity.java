package com.learningkids.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class SecondClassActivity extends AppCompatActivity {

   private CardView cardEng2, cardEvn2, cardGk2, cardArt2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.question));
        }

        cardEng2 = findViewById(R.id.Eng2);
        cardEvn2 = findViewById(R.id.Evn2);
        cardGk2 = findViewById(R.id.Gk2);
        cardArt2 = findViewById(R.id.Art2);




        cardEng2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Eng2intent = new Intent(SecondClassActivity.this, EnglishQ2Activity.class);
                startActivity(Eng2intent);
            }
        });

        cardEvn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Math2intent = new Intent(SecondClassActivity.this, EnvQ2Activity.class);
                startActivity(Math2intent);
            }
        });

        cardGk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Gk2intent = new Intent(SecondClassActivity.this, GkQ2Activity.class);
                startActivity(Gk2intent);
            }
        });

        cardArt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card24intent = new Intent(SecondClassActivity.this, ArtQ2Activity.class);
                startActivity(card24intent);
            }
        });


    }
}