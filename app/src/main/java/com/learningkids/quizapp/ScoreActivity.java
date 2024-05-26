package com.learningkids.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ScoreActivity extends AppCompatActivity {

    private ImageView  Rimg;
    private TextView scoreTextView, correctCountTextView, incorrectCountTextView;
    private ImageView Home;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.question));
        }


        scoreTextView = findViewById(R.id.scoreTextView);
        correctCountTextView = findViewById(R.id.correct);
        incorrectCountTextView = findViewById(R.id.incorrect);
        Rimg = findViewById(R.id.Rimg);
        Home = findViewById(R.id.Home);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeIntent = new Intent(ScoreActivity.this,MainActivity.class);
                startActivity(HomeIntent);
            }
        });

        int score = getIntent().getIntExtra("score", 0);
        int correctCount = getIntent().getIntExtra("correctCount", 0);
        int incorrectCount = getIntent().getIntExtra("incorrectCount", 0);


        scoreTextView.setText("" + score);
        correctCountTextView.setText("" + correctCount);
        incorrectCountTextView.setText("" + incorrectCount);


        if (score >= 10) {

            Rimg.setImageResource(R.drawable.best);

        } else if(score >=4){

            Rimg.setImageResource(R.drawable.trophy);

        }else{

            Rimg.setImageResource(R.drawable.fail);
        }

    }


}