package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WordsActivity extends AppCompatActivity {

    String storyPicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        Intent intent = getIntent();
        storyPicked = (String) intent.getSerializableExtra("story_chosen");
        String result = storyPicked;
    }

    private class Story ();


}
