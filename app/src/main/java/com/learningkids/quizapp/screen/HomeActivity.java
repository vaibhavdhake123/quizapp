package com.learningkids.quizapp.screen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.learningkids.quizapp.R;
import com.learningkids.quizapp.bordgame.BoardGameActivity;
import com.learningkids.quizapp.letter.LetterActivity;
import com.learningkids.quizapp.number.NumberActivity;
import com.learningkids.quizapp.quiz.selecter.SelectScreen;
import com.learningkids.quizapp.story.Constants;
import com.learningkids.quizapp.story.Story;
import com.learningkids.quizapp.story.StoryAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<Story> originalStoryList;
    private InterstitialAd mInterstitialAd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CardView EngQ, EnvQ, GkQ, ArtQ , num, letter, game;


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (!isNetworkAvailable()) {
            Toast.makeText(this, "No Internet connection. Please turn on your internet.", Toast.LENGTH_LONG).show();
        }


        new Thread(
                () -> {
                    MobileAds.initialize(this, initializationStatus -> {});
                })
                .start();

        // Initialize Views
        EngQ = findViewById(R.id.EnglishQ);
        EnvQ = findViewById(R.id.EnvQ);
        GkQ = findViewById(R.id.GKQ);
        ArtQ = findViewById(R.id.ArtQ);
        num = findViewById(R.id.number);
        letter = findViewById(R.id.letter);
        game = findViewById(R.id.game);

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

//        loadInterstitialAd();


        originalStoryList = Constants.getStoryList(); // Store the original list
        setAdapterRecyclerView(originalStoryList);

        // Set click listeners for category buttons
        EngQ.setOnClickListener(v -> openCategorySelection("English"));
        EnvQ.setOnClickListener(v -> openCategorySelection("Environment"));
        GkQ.setOnClickListener(v -> openCategorySelection("GK"));
        ArtQ.setOnClickListener(v -> openCategorySelection("Art"));

        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NumberActivity.class);
                startActivity(intent);
            }
        });

        letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LetterActivity.class);
                startActivity(intent);
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BoardGameActivity.class);
                startActivity(intent);
            }
        });


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void openCategorySelection(String category) {
//        showInterstitialAd();
        Intent intent = new Intent(HomeActivity.this, SelectScreen.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    private void setAdapterRecyclerView(ArrayList<Story> data) {
        // Shuffle the data for display
        ArrayList<Story> shuffledData = new ArrayList<>(data);
        Collections.shuffle(shuffledData);

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvStoryList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        StoryAdapter adapter = new StoryAdapter(shuffledData);
        recyclerView.setAdapter(adapter);

        // Handle item click events for stories
        adapter.setOnClickListener(position -> {
            // Get the original story position based on the clicked position
            Story selectedStory = shuffledData.get(position);
            int originalPosition = originalStoryList.indexOf(selectedStory); // Find the original position

            Intent intent = new Intent(HomeActivity.this, StoryActivity.class);
            intent.putExtra("position", originalPosition); // Pass the original position
            startActivity(intent);
        });
    }

//    private void loadInterstitialAd() {
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        // Replace "ca-app-pub-3940256099942544/1033173712" with your real Ad Unit ID
//        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest,
//                new InterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(InterstitialAd interstitialAd) {
//                        // The interstitial ad was loaded successfully
//                        mInterstitialAd = interstitialAd;
//                        setupFullScreenCallback();
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(LoadAdError adError) {
//                        // Handle the error
//                        mInterstitialAd = null;
//                    }
//                });
//    }
//
//    // Method to show the Interstitial Ad when it's loaded
//    private void showInterstitialAd() {
//        if (mInterstitialAd != null) {
//            mInterstitialAd.show(this);
//        } else {
//            // If the ad is not ready, you might want to load it again
//            loadInterstitialAd();
//        }
//    }
//
//    // Optional: Handle Ad Events
//    private void setupFullScreenCallback() {
//        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//            @Override
//            public void onAdDismissedFullScreenContent() {
//                // Called when the ad is dismissed.
//                mInterstitialAd = null;
//                // Load another ad for future
//                loadInterstitialAd();
//            }
//
//            @Override
//            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
//                // Called when ad fails to show
//                mInterstitialAd = null;
//            }
//
//            @Override
//            public void onAdShowedFullScreenContent() {
//                // Called when ad is shown
//                mInterstitialAd = null;
//            }
//        });
//    }


}

