package com.learningkids.quizapp.number;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.learningkids.quizapp.R;

import java.util.Locale;

public class NumberActivity extends AppCompatActivity {

    private ImageView numberImageView, playButton , nextButton ,backButton;

    private int currentIndex = 0;
    private int[] images = {
            R.drawable.i0,
            R.drawable.i1,
            R.drawable.i2,
            R.drawable.i3,
            R.drawable.i4,
            R.drawable.i5,
            R.drawable.i6,
            R.drawable.i7,
            R.drawable.i8,
            R.drawable.i9,
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

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Handle TTS initialization error
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
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                if (currentIndex >= images.length) {
                    currentIndex = 0; // Loop back to the first image
                }
                updateImage();
            }
        });

        // Back button click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex--;
                if (currentIndex < 0) {
                    currentIndex = images.length - 1; // Loop to the last image
                }
                updateImage();
            }
        });

}
    private void playNextImage() {
        if (isPlaying) {
            updateImage();
            speakNumber(currentIndex);

            currentIndex++;
            if (currentIndex >= images.length) {
                currentIndex = 0; // Reset to the first image if at the end
            }

            // Schedule next image after a delay (e.g., 2 seconds)
            numberImageView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    playNextImage();
                }
            }, 2000); // Adjust the delay as needed
        }
    }

    private void updateImage() {
        numberImageView.setImageResource(images[currentIndex]);
    }

    private void speakNumber(int number) {
        String text = String.valueOf(number);
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
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