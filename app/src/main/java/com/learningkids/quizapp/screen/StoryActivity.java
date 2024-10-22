package com.learningkids.quizapp.screen;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.learningkids.quizapp.R;
import com.learningkids.quizapp.databinding.ActivityStoryBinding;
import com.learningkids.quizapp.story.Constants;
import com.learningkids.quizapp.story.Story;

import java.util.ArrayList;
import java.util.Locale;

public class StoryActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private ActivityStoryBinding binding;
    private int position = 0;
    private ArrayList<Story> storyList;
    private String speakableText;
    private boolean isPlaying = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_story);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityStoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get position from Intent
        position = getIntent().getIntExtra("position", 0);
        storyList = Constants.getStoryList();

        // Initialize Text-to-Speech
        tts = new TextToSpeech(this, this);

        // Set story view and speakable text
        setStoryView();
        setSpeakableText();

        binding.btnNext.setOnClickListener(v -> {
            if (position < storyList.size() - 1) {
                onChangeStory(1);
            } else {
                Toast.makeText(this, "No more stories available", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnPrevious.setOnClickListener(v -> {
            if (position > 0) {
                onChangeStory(-1);
            } else {
                Toast.makeText(this, "No more stories available", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnPlay.setOnClickListener(v -> playStory());
    }

    private void playStory() {
        if (isPlaying) {
            if (tts != null) {
                tts.stop();
            }
            isPlaying = false;
            binding.btnPlay.setImageResource(R.drawable.play);
        } else {
            isPlaying = true;
            speakOut(speakableText);
            binding.btnPlay.setImageResource(R.drawable.pause);
        }
    }

    private void onChangeStory(int offset) {
        if (tts != null) {
            tts.stop();
        }
        position += offset;
        isPlaying = false;
        setStoryView();
        binding.btnPlay.setImageResource(R.drawable.play);
        setSpeakableText();
    }

    private void setStoryView() {
        Story story = storyList.get(position);
        binding.storyImage.setImageResource(story.getImage2());
        binding.tvStoryTitle.setText(story.getTitle());
        binding.tvStory.setText(story.getStory());
        binding.tvMoral.setText(story.getMoral());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.forLanguageTag("hi-IN"));
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language not supported");
            }
        } else {
            Log.e("TTS", "Initialization failed");
        }
    }

    private void speakOut(String text) {
        if (tts != null) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "");
        }
    }

    private void setSpeakableText() {
        speakableText = getString(storyList.get(position).getStory()) + "कहानी की नीति । " +
                getString(storyList.get(position).getMoral());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

        binding = null;
    }

    // Placeholder method for edge-to-edge enablement (manual implementation needed in Java)
    private void enableEdgeToEdge() {
        // Implement edge-to-edge functionality if required
    }
}