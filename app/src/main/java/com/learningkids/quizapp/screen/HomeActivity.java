package com.learningkids.quizapp.screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learningkids.quizapp.R;
import com.learningkids.quizapp.bordgame.BoardGameActivity;
import com.learningkids.quizapp.selecter.SelectScreen;
import com.learningkids.quizapp.story.Constants;
import com.learningkids.quizapp.story.Story;
import com.learningkids.quizapp.story.StoryAdapter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CardView EngQ, EnvQ, GkQ, ArtQ;
        ImageView BoardGame, Flip;

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        EngQ = findViewById(R.id.EnglishQ);
        EnvQ = findViewById(R.id.EnvQ);
        GkQ = findViewById(R.id.GKQ);
        ArtQ = findViewById(R.id.ArtQ);
        BoardGame = findViewById(R.id.game);
        Flip = findViewById(R.id.flip);

        // Set up RecyclerView for stories
        ArrayList<Story> data = Constants.getStoryList();
        setAdapterRecyclerView(data);

        // Set click listeners for category buttons
        EngQ.setOnClickListener(v -> openCategorySelection("English"));
        EnvQ.setOnClickListener(v -> openCategorySelection("Environment"));
        GkQ.setOnClickListener(v -> openCategorySelection("GK"));
        ArtQ.setOnClickListener(v -> openCategorySelection("Art"));

        // BoardGame click listener (moved outside of setAdapterRecyclerView)
        BoardGame.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, BoardGameActivity.class);
            startActivity(intent);
        });
    }

    private void openCategorySelection(String category) {
        Intent intent = new Intent(HomeActivity.this, SelectScreen.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    private void setAdapterRecyclerView(ArrayList<Story> data) {
        // Shuffle the data before setting the adapter
//        Collections.shuffle(data);

        // Set up RecyclerView with a horizontal LinearLayoutManager and StoryAdapter
        RecyclerView recyclerView = findViewById(R.id.rvStoryList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        StoryAdapter adapter = new StoryAdapter(data);
        recyclerView.setAdapter(adapter);

        // Handle item click events for stories
        adapter.setOnClickListener(position -> {
            Intent intent = new Intent(HomeActivity.this, StoryActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }
}
