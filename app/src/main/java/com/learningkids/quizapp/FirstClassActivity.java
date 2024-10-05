package com.learningkids.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.learningkids.quizapp.test.ArtQ1Activity;
import com.learningkids.quizapp.test.EnglishQ1Activity;
import com.learningkids.quizapp.test.EnvQ1Activity;
import com.learningkids.quizapp.test.GkQ1Activity;

public class FirstClassActivity extends AppCompatActivity {

    private CardView cardEng1, cardEvn1, cardGk1 , cardArt1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_class);

        cardEng1 = findViewById(R.id.Eng1);
        cardEvn1 = findViewById(R.id.Evn1);
        cardGk1 = findViewById(R.id.Gk1);
        cardArt1 = findViewById(R.id.Art1);


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

        cardEvn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Math1intent = new Intent(FirstClassActivity.this, EnvQ1Activity.class);
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

        cardArt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card14in = new Intent(FirstClassActivity.this, ArtQ1Activity.class);
                startActivity(card14in);
            }
        });


    }}