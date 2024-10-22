package com.learningkids.quizapp.story;

public class Story {
    private int title;
    private int story;
    private int moral;
    private int image;
    private int image2;

    // Constructor
    public Story(int title, int story, int moral, int image, int image2) {
        this.title = title;
        this.story = story;
        this.moral = moral;
        this.image = image;
        this.image2 = image2;
    }

    // Getters and Setters
    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getStory() {
        return story;
    }

    public void setStory(int story) {
        this.story = story;
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }
}