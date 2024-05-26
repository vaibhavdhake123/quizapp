package com.learningkids.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ArtQ1Activity extends AppCompatActivity {
    private TextView timerTextView, numIndicator, questionTextView;
    private Button option1, option2, option3, option4;
    private ImageView btnNext, btnBack , numimg, questionimg, bookimg,boardimg;
    private DatabaseReference databaseReference;
    private List<Question> questions;
    private CountDownTimer countDownTimer;
    private LottieAnimationView load;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int correctCount = 0;
    private int incorrectCount = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

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
        numimg = findViewById(R.id.Numimg);
        questionimg = findViewById(R.id.questionmarkimg);
        bookimg = findViewById(R.id.bookimg);
        boardimg = findViewById(R.id.bordimg);
        load = findViewById(R.id.load);

        questions = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("First").child("Art1");

        loadQuestions();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    setQuestion(currentQuestionIndex);
                } else {
                    Toast.makeText(ArtQ1Activity.this, "Back To Home", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex++;
                setQuestion(currentQuestionIndex);
            }
        });

        setupOptionListeners();
    }

    private void loadQuestions() {
        showProgressBar();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hideProgressBar();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Question question = snapshot.getValue(Question.class);
                        questions.add(question);
                    }

                    Collections.shuffle(questions);
                    questions = questions.subList(0, Math.min(15, questions.size()));
                    setQuestion(0);
                } else {
                    Toast.makeText(ArtQ1Activity.this, "No questions found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgressBar();
                Toast.makeText(ArtQ1Activity.this, "Failed to load questions.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgressBar() {
        load.setVisibility(View.VISIBLE);
        setComponentsVisibility(View.GONE);
    }

    private void hideProgressBar() {
        load.setVisibility(View.GONE);
        setComponentsVisibility(View.VISIBLE);
    }

    private void setComponentsVisibility(int visibility) {
        timerTextView.setVisibility(visibility);
        numIndicator.setVisibility(visibility);
        questionTextView.setVisibility(visibility);
        option1.setVisibility(visibility);
        option2.setVisibility(visibility);
        option3.setVisibility(visibility);
        option4.setVisibility(visibility);
        btnNext.setVisibility(visibility);
        btnBack.setVisibility(visibility);
        numimg.setVisibility(visibility);
        questionimg.setVisibility(visibility);
        bookimg.setVisibility(visibility);
        boardimg.setVisibility(visibility);
    }

    private void setupOptionListeners() {
        View.OnClickListener optionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                checkAnswer(clickedButton.getText().toString());
            }
        };

        option1.setOnClickListener(optionClickListener);
        option2.setOnClickListener(optionClickListener);
        option3.setOnClickListener(optionClickListener);
        option4.setOnClickListener(optionClickListener);
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

        currentQuestionIndex++;

        if (currentQuestionIndex < questions.size()) {
            setQuestion(currentQuestionIndex);
        } else {
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

            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            startTimer();
        } else {
            showFinalScore();
        }
    }

    private void showFinalScore() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        option1.setVisibility(View.INVISIBLE);
        option2.setVisibility(View.INVISIBLE);
        option3.setVisibility(View.INVISIBLE);
        option4.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.INVISIBLE);

        Intent intent = new Intent(ArtQ1Activity.this, ScoreActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("correctCount", correctCount);
        intent.putExtra("incorrectCount", incorrectCount);

        startActivity(intent);
        finish();
    }
}