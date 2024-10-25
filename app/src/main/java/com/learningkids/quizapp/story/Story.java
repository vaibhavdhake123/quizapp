package com.learningkids.quizapp.story;

public class Story {
    private int title;  // Resource ID for title
    private int story;  // Resource ID for story
    private int moral;  // Resource ID for moral
    private int image;  // Resource ID for image
    private int image2; // Optional, depending on your use case

    // Constructor
    public Story(int title, int story, int moral, int image) {
        this.title = title;
        this.story = story;
        this.moral = moral;
        this.image = image;
    }

    // Getters
    public int getTitle() {
        return title;
    }

    public int getStory() {
        return story;
    }

    public int getMoral() {
        return moral;
    }

    public int getImage() {
        return image;
    }

}