package com.learningkids.quizapp.story;

import com.learningkids.quizapp.R;

import java.util.ArrayList;

public class Constants {

    // Static method to get the story list
    public static ArrayList<Story> getStoryList() {
        ArrayList<Story> storyList = new ArrayList<>();

        Story story1 = new Story(
                R.string.title1,
                R.string.story1,
                R.string.moral1,
                R.drawable.rv_image1,
                R.drawable.image1
        );
        storyList.add(story1);

        Story story2 = new Story(
                R.string.title2,
                R.string.story2,
                R.string.moral2,
                R.drawable.rv_image2,
                R.drawable.image2
        );
        storyList.add(story2);

        Story story3 = new Story(
                R.string.title3,
                R.string.story3,
                R.string.moral3,
                R.drawable.rv_image3,
                R.drawable.image3
        );
        storyList.add(story3);

        Story story4 = new Story(
                R.string.title4,
                R.string.story4,
                R.string.moral4,
                R.drawable.rv_image4,
                R.drawable.image4
        );
        storyList.add(story4);

        Story story5 = new Story(
                R.string.title5,
                R.string.story5,
                R.string.moral5,
                R.drawable.rv_image5,
                R.drawable.image5
        );
        storyList.add(story5);

        Story story6 = new Story(
                R.string.title6,
                R.string.story6,
                R.string.moral6,
                R.drawable.rv_image6,
                R.drawable.image6
        );
        storyList.add(story6);

        Story story7 = new Story(
                R.string.title7,
                R.string.story7,
                R.string.moral7,
                R.drawable.rv_image7,
                R.drawable.image7
        );
        storyList.add(story7);

        Story story8 = new Story(
                R.string.title8,
                R.string.story8,
                R.string.moral8,
                R.drawable.rv_image8,
                R.drawable.image8
        );
        storyList.add(story8);

        Story story9 = new Story(
                R.string.title9,
                R.string.story9,
                R.string.moral9,
                R.drawable.rv_image9,
                R.drawable.image9
        );
        storyList.add(story9);

        Story story10 = new Story(
                R.string.title10,
                R.string.story10,
                R.string.moral10,
                R.drawable.rv_image10,
                R.drawable.image10
        );
        storyList.add(story10);

        return storyList;
    }
}