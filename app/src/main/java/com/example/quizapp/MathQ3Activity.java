package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MathQ3Activity extends AppCompatActivity {

    private TextView timerTextView, numIndicator, questionTextView;
    private Button option1, option2, option3, option4;
    private ImageView btnNext, btnBack;
    private DatabaseReference databaseReference;
    private List<Question> questions;
    private CountDownTimer countDownTimer;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int correctCount = 0;
    private int incorrectCount = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_q3);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.question));
        }


        FirebaseApp.initializeApp(this);


        timerTextView = findViewById(R.id.timer);
        numIndicator = findViewById(R.id.numindicator);
        questionTextView = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);



        databaseReference = FirebaseDatabase.getInstance().getReference().child("Third").child("Envorment3");


        questions = new ArrayList<>();


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Question question = snapshot.getValue(Question.class);
                        questions.add(question);
                    }

                    Collections.shuffle(questions);

                    questions = questions.subList(0, Math.min(15, questions.size()));

                    setQuestion(0);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentQuestionIndex++;
                setQuestion(currentQuestionIndex);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex--;
                setQuestion(currentQuestionIndex);
            }
        });

        startTimer();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

                currentQuestionIndex++;
                setQuestion(currentQuestionIndex);
            }
        }.start();

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option1.getText().toString());

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option2.getText().toString());

            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option3.getText().toString());

            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option4.getText().toString());

            }
        });
    }

    private void checkAnswer(String selectedOption) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        String correctAnswer = currentQuestion.getAnswer();

        if (selectedOption.equals(correctAnswer)) {
            score++;
            correctCount++;
        } else {
            incorrectCount++;
        }

        // Increment the question index
        currentQuestionIndex++;

        // Check if there are more questions
        if (currentQuestionIndex < questions.size()) {
            setQuestion(currentQuestionIndex);
        } else {
            // If no more questions, show the final score
            showFinalScore();
        }
    }

    private void setQuestion(int index) {
        if (index < questions.size()) {
            Question currentQuestion = questions.get(index);


            questionTextView.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());


            numIndicator.setText((index + 1) + "/" + questions.size());


            countDownTimer.cancel();
            startTimer();
        } else {

            showFinalScore();
        }
    }



    private void showFinalScore() {
        countDownTimer.cancel();

        option1.setVisibility(View.INVISIBLE);
        option2.setVisibility(View.INVISIBLE);
        option3.setVisibility(View.INVISIBLE);
        option4.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.INVISIBLE);

        // Create an Intent to start the ResultActivity
        Intent intent = new Intent(MathQ3Activity.this, ScoreActivity.class);

        // Pass the score, correct count, and incorrect count as extras
        intent.putExtra("score", score);
        intent.putExtra("correctCount", correctCount);
        intent.putExtra("incorrectCount", incorrectCount);

        // Start the ResultActivity
        startActivity(intent);
        finish(); // Optional: Finish the current activity if needed
    }
}
