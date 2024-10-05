package com.learningkids.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.learningkids.quizapp.test.ArtQ4Activity;
import com.learningkids.quizapp.test.EnglishQ4Activity;
import com.learningkids.quizapp.test.EnvQ4Activity;
import com.learningkids.quizapp.test.GkQ4Activity;

public class FourthClassActivity extends AppCompatActivity {


    private CardView cardEng4, cardEvn4, cardGk4, cardArt4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.question));
        }

        cardEng4 = findViewById(R.id.Eng4);
        cardEvn4 = findViewById(R.id.Evn4);
        cardGk4 = findViewById(R.id.Gk4);
        cardArt4 = findViewById(R.id.Art4);



        cardEng4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Eng4intent = new Intent(FourthClassActivity.this, EnglishQ4Activity.class);
                startActivity(Eng4intent);
            }
        });

        cardEvn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Math4intent = new Intent(FourthClassActivity.this, EnvQ4Activity.class);
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

        cardArt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card44intent = new Intent(FourthClassActivity.this, ArtQ4Activity.class);
                startActivity(card44intent);
            }
        });



    }
}