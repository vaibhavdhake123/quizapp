package com.learningkids.quizapp.screen;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.learningkids.quizapp.R;
import com.learningkids.quizapp.story.Constants;
import com.learningkids.quizapp.story.Story;

import java.util.HashMap;
import java.util.Locale;

public class StoryActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private ImageView btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_story);

        // Handle system bar insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        ImageView storyImage = findViewById(R.id.storyImage);
        TextView tvStoryTitle = findViewById(R.id.tvStoryTitle);
        TextView tvStory = findViewById(R.id.tvStory);
        TextView tvMoral = findViewById(R.id.tvMoral);
        btnPlay = findViewById(R.id.btnPlay); // Use the class-level variable

        // Get the intent and retrieve the position of the selected story
        int position = getIntent().getIntExtra("position", 0);

        // Retrieve the story based on the position
        Story story = Constants.getStoryList().get(position);

        // Set the values in the UI
        tvStoryTitle.setText(story.getTitle());
        tvStory.setText(story.getStory());
        tvMoral.setText(story.getMoral());
        storyImage.setImageResource(story.getImage());

        // Set up TextToSpeech
        textToSpeech = new TextToSpeech(this, this);

        // Set the onClick listener for the play button
        btnPlay.setOnClickListener(v -> {
            String storyText = tvStory.getText().toString();
            String moralText = tvMoral.getText().toString();

            // Set up the utterance progress listener
            textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                    // Optional: handle when speech starts
                }

                @Override
                public void onDone(String utteranceId) {
                    if ("story".equals(utteranceId)) {
                        // Speak the moral after the story is done
                        String moralPrompt = "Moral of the story: " + moralText;
                        HashMap<String, String> params = new HashMap<>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "moral");
                        textToSpeech.speak(moralPrompt, TextToSpeech.QUEUE_FLUSH, params);
                    }
                }

                @Override
                public void onError(String utteranceId) {
                    // Optional: handle errors
                }
            });

            // Speak the story
            HashMap<String, String> params = new HashMap<>();
            params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "story");
            textToSpeech.speak(storyText, TextToSpeech.QUEUE_FLUSH, params);
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            // Check if the language is supported
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Handle the error (e.g., show a toast)
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
