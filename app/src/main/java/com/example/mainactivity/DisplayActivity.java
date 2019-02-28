package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity";
    Button backButton;
    Story story;
    String storyPicked;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaying);

        textView = findViewById(R.id.story);

        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("story_chosen");

        backButton = findViewById(R.id.backButton);
        View.OnClickListener listener = new MyClickListener();
        backButton.setOnClickListener(listener);

        storyPicked = story.toString();
        Log.d(TAG, "Story: " + storyPicked);
        textView.setText(storyPicked);
    }

    private class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent thirdIntent = new Intent(DisplayActivity.this, StartActivity.class);
            startActivity(thirdIntent);
        }
    }
}
