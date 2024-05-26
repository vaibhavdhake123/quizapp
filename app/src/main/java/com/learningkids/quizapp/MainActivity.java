package com.learningkids.quizapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseStorage storage;

    CardView card1, card2, card3, card4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = findViewById(R.id.Eng1);
        card2 = findViewById(R.id.Math1);
        card3 = findViewById(R.id.cardGk4);
        card4 = findViewById(R.id.card44);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.question));
        }


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card1intent = new Intent(MainActivity.this,FirstClassActivity.class);
                startActivity(card1intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card2intent = new Intent(MainActivity.this,SecondClassActivity.class);
                startActivity(card2intent);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card3intent = new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(card3intent);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card4intent = new Intent(MainActivity.this,FourthClassActivity.class);
                startActivity(card4intent);
            }
        });

    }
}