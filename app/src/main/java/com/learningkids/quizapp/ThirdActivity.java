package com.learningkids.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.learningkids.quizapp.test.ArtQ3Activity;
import com.learningkids.quizapp.test.EnglishQ3Activity;
import com.learningkids.quizapp.test.EnvQ3Activity;
import com.learningkids.quizapp.test.GkQ3Activity;

public class ThirdActivity extends AppCompatActivity {
    private CardView cardEng3, cardEvn3, cardGk3, cardArt3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.question));
        }

        cardEng3 = findViewById(R.id.Eng3);
        cardEvn3 = findViewById(R.id.Evn3);
        cardGk3 =findViewById(R.id.Gk3);
        cardArt3 = findViewById(R.id.Art3);


        cardEng3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Eng3intent = new Intent(ThirdActivity.this, EnglishQ3Activity.class);
                startActivity(Eng3intent);
            }
        });

        cardEvn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Math3intent = new Intent(ThirdActivity.this, EnvQ3Activity.class);
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

        cardArt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card34intent = new Intent(ThirdActivity.this, ArtQ3Activity.class);
                startActivity(card34intent);
            }
        });


    }
}