package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class FirstClassActivity extends AppCompatActivity {

    private CardView cardEng1, cardMath1, cardGk1 , card14, card15;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_class);

       cardEng1 = findViewById(R.id.Eng1);
       cardMath1 = findViewById(R.id.Math1);
       cardGk1 = findViewById(R.id.Gk1);
       card14 = findViewById(R.id.card14);
       card15 = findViewById(R.id.card15);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.question));
        }

        cardEng1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Eng1intent = new Intent(FirstClassActivity.this, EnglishQ1Activity.class);
                startActivity(Eng1intent);
            }
        });

        cardMath1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Math1intent = new Intent(FirstClassActivity.this, MathQ1Activity.class);
                startActivity(Math1intent);
            }
        });

        cardGk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Gk1intent = new Intent(FirstClassActivity.this, GkQ1Activity.class);
                startActivity(Gk1intent);
            }
        });

        card14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card14in = new Intent(FirstClassActivity.this, ArtQ1Activity.class);
                startActivity(card14in);
            }
        });

        card15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card15in = new Intent(FirstClassActivity.this, SportQ1Activity.class);
                startActivity(card15in);
            }
        });
    }
}