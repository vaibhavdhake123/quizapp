package com.learningkids.quizapp.letter;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.learningkids.quizapp.R;

import java.util.Locale;

public class LetterActivity extends AppCompatActivity {

    private ImageView numberImageView, playButton , nextButton ,backButton;

    private int currentIndex = 0;
    private int[] images = {
            R.drawable.ia,
            R.drawable.ib,
            R.drawable.ic,
            R.drawable.id,
            R.drawable.ie,
            R.drawable.iff,
            R.drawable.ig,
            R.drawable.ih,
            R.drawable.ii,
            R.drawable.ij,
            R.drawable.ik,
            R.drawable.il,
            R.drawable.im,
            R.drawable.in,
            R.drawable.io,
            R.drawable.ip,
            R.drawable.iq,
            R.drawable.ir,
            R.drawable.is,
            R.drawable.it,
            R.drawable.iu,
            R.drawable.iv,
            R.drawable.iw,
            R.drawable.ix,
            R.drawable.iy,
            R.drawable.iz,

    };

    private String[] letters = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    private TextToSpeech textToSpeech;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_number);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numberImageView = findViewById(R.id.numberimg);
        playButton = findViewById(R.id.PlayButton);
        nextButton = findViewById(R.id.next);
        backButton = findViewById(R.id.back);

        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                // Set language to US English
                int result = textToSpeech.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Handle TTS initialization error
                    Log.e("TTS", "Language not supported or missing data");
                }

                // Adjust pitch and speed for a cute and playful voice
                textToSpeech.setPitch(1.5f); // Higher pitch for a cute tone
                textToSpeech.setSpeechRate(0.85f); // Slower speech rate for clarity

                // Optionally, try to select a female voice if available
                for (Voice voice : textToSpeech.getVoices()) {
                    if (voice.getName().contains("female") || voice.getName().contains("en-us-x-sfg#female")) {
                        textToSpeech.setVoice(voice); // Choose the female voice if available
                        break;
                    }
                }
            }
        });


        // Set the initial image
        numberImageView.setImageResource(images[currentIndex]);

        // Play button click listener
        playButton.setOnClickListener(v -> {
            if (!isPlaying) {
                isPlaying = true;
                playButton.setImageResource(R.drawable.pause); // Change to pause image
                playNextImage();
            } else {
                isPlaying = false;
                playButton.setImageResource(R.drawable.play); // Change to play image
            }
        });

        // Next button click listener
        nextButton.setOnClickListener(v -> {
            currentIndex++;
            if (currentIndex >= images.length) {
                currentIndex = 0; // Loop back to the first image
            }
            updateImage();
        });

        // Back button click listener
        backButton.setOnClickListener(v -> {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = images.length - 1; // Loop to the last image
            }
            updateImage();
        });

    }

    private void playNextImage() {
        if (isPlaying) {
            updateImage();
            speakLetter(currentIndex);

            currentIndex++;
            if (currentIndex >= images.length) {
                currentIndex = 0; // Reset to the first image if at the end
            }

            // Schedule next image after a delay (e.g., 2 seconds)
            numberImageView.postDelayed(this::playNextImage, 2000); // Adjust the delay as needed
        }
    }

    private void updateImage() {
        numberImageView.setImageResource(images[currentIndex]);
    }

    // Modified to speak the corresponding letter instead of a number
    private void speakLetter(int index) {
        String letter = letters[index]; // Get the letter corresponding to the current index
        textToSpeech.speak(letter, TextToSpeech.QUEUE_FLUSH, null, null);
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