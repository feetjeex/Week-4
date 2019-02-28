package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.InputStream;

public class WordsActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity";

    String storyPicked;
    String result;
    String wordLeft;
    Story story;
    Button confirm;
    EditText editText;
    TextView textView;
    int wordsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        //story.clear();

        // Get the intent (which is the MadLib the user selected) from StartActivity and stores it in 'result'
        Intent intent = getIntent();
        storyPicked = (String) intent.getSerializableExtra("story_chosen");
        result = storyPicked;

        // Assigning the ok button
        confirm = findViewById(R.id.okAfterInput);
        View.OnClickListener listener = new MyClickListener();
        confirm.setOnClickListener(listener);

        // Opens the user requested text file and stores it in 'is'
        InputStream is = getResources().openRawResource(R.raw.madlib0_simple);

        // Construct a new Story object
        story = new Story(is);
    }

    private class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            // Assigning the EditText
            editText = findViewById(R.id.input);
            textView = findViewById(R.id.wordsLeft);

            // Loop to keep prompting the user for a new input as long as the placeholder remaining count is positive
            editText.setHint(story.getNextPlaceholder());
            wordsLeft = story.getPlaceholderRemainingCount();

            if (story.getPlaceholderRemainingCount() > 0) {
                Log.d(TAG, "Words remaining: " + story.getPlaceholderRemainingCount());
                String words = editText.getText().toString();
                story.fillInPlaceholder(words);
                Log.d(TAG, "Word selected: " + words);
                editText.setText("");
                editText.setHint(story.getNextPlaceholder());
            }

            else {
                Intent secondIntent = new Intent(WordsActivity.this, DisplayActivity.class);
                secondIntent.putExtra("story_chosen", story);
                startActivity(secondIntent);
            }
        }
    }


}
