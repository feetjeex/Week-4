package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    Story story;
    String storyPicked;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaying);
        Button backButton;

        // Implements the textView
        textView = findViewById(R.id.story);

        // Gets the text from the previous activity
        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("story_chosen");

        // Implementing the 'return to start screen button'
        backButton = findViewById(R.id.backButton);
        View.OnClickListener listener = new MyClickListener();
        backButton.setOnClickListener(listener);

        // Converts the text of the story picked to a string and assigns it to the textView
        storyPicked = story.toString();
        textView.setText(Html.fromHtml(storyPicked));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Saves the instance state using Serialization
        outState.putSerializable("Text", storyPicked);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        // On restoration, sets the text to the correct, user filled-in text
        String storyPicked = (String) inState.getSerializable("Text");
        textView.setText(Html.fromHtml(storyPicked));

    }

    private class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            // Implementing the 'return to start screen button'
            Intent thirdIntent = new Intent(DisplayActivity.this, StartActivity.class);
            startActivity(thirdIntent);
        }
    }

    @Override
    public void onBackPressed() {

        // Assigning the back button to go back to the first activity
        Intent intent = new Intent(DisplayActivity.this, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
