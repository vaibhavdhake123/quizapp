package com.learningkids.quizapp.quiz.selecter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.learningkids.quizapp.R;
import com.learningkids.quizapp.quiz.test.ArtHardActivity;
import com.learningkids.quizapp.quiz.test.ArtMediumActivity;
import com.learningkids.quizapp.quiz.test.ArtNormalActivity;
import com.learningkids.quizapp.quiz.test.EnglishHardActivity;
import com.learningkids.quizapp.quiz.test.EnglishMediumActivity;
import com.learningkids.quizapp.quiz.test.EnglishNormalActivity;
import com.learningkids.quizapp.quiz.test.EnvHardActivity;
import com.learningkids.quizapp.quiz.test.EnvMediumActivity;
import com.learningkids.quizapp.quiz.test.EnvNormalActivity;
import com.learningkids.quizapp.quiz.test.GkHardActivity;
import com.learningkids.quizapp.quiz.test.GkMediumActivity;
import com.learningkids.quizapp.quiz.test.GkNormalActivity;

public class SelectScreen extends AppCompatActivity {

    ImageView normal, medium, hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        normal = findViewById(R.id.normal);
        medium = findViewById(R.id.medium);
        hard = findViewById(R.id.hard);

        // Get the category passed from the HomeActivity
        String category = getIntent().getStringExtra("category");

        normal.setOnClickListener(v -> startActivityByCategory(category, "normal"));
        medium.setOnClickListener(v -> startActivityByCategory(category, "medium"));
        hard.setOnClickListener(v -> startActivityByCategory(category, "hard"));
    }

    private void startActivityByCategory(String category, String level) {
        Class<?> targetActivity = null;

        switch (category) {
            case "English":
                targetActivity = level.equals("normal") ? EnglishNormalActivity.class :
                        level.equals("medium") ? EnglishMediumActivity.class :
                                level.equals("hard") ? EnglishHardActivity.class : null;
                break;
            case "Environment":
                targetActivity = level.equals("normal") ? EnvNormalActivity.class :
                        level.equals("medium") ? EnvMediumActivity.class :
                                level.equals("hard") ? EnvHardActivity.class : null;
                break;
            case "GK":
                targetActivity = level.equals("normal") ? GkNormalActivity.class :
                        level.equals("medium") ? GkMediumActivity.class :
                                level.equals("hard") ? GkHardActivity.class : null;
                break;
            case "Art":
                targetActivity = level.equals("normal") ? ArtNormalActivity.class :
                        level.equals("medium") ? ArtMediumActivity.class :
                                level.equals("hard") ? ArtHardActivity.class : null;
                break;
        }

        if (targetActivity != null) {
            Intent intent = new Intent(SelectScreen.this, targetActivity);
            startActivity(intent);
        } else {
            Log.e("SelectScreen", "Invalid category or level: " + category + ", " + level);
            Toast.makeText(this, "Invalid selection", Toast.LENGTH_SHORT).show();
        }
    }
}