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
    String wordLeft;
    Story story;
    Button confirm;
    EditText editText;
    TextView textView;
    int wordsThatLeft;
    InputStream is;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        //story.clear();

        // Get the intent (which is the MadLib the user selected) from StartActivity and stores it in 'storyPicked'
        Intent intent = getIntent();
        storyPicked = (String) intent.getSerializableExtra("story_chosen");
        Log.d(TAG, "Story Picked: " + storyPicked);

        // Assigning the ok button
        confirm = findViewById(R.id.okAfterInput);
        View.OnClickListener listener = new MyClickListener();
        confirm.setOnClickListener(listener);

        // Inputs the proper text file into the inputStream
        switch(storyPicked) {
            case ("Simple"):
                is = getResources().openRawResource(R.raw.madlib0_simple);
                break;
            case ("Tarzan"):
                is = getResources().openRawResource(R.raw.madlib1_tarzan);
                break;
            case ("University"):
                is = getResources().openRawResource(R.raw.madlib2_university);
                break;
            case ("Clothes"):
                is = getResources().openRawResource(R.raw.madlib3_clothes);
                break;
            case ("Dance"):
                is = getResources().openRawResource(R.raw.madlib4_dance);
                break;
        }

        // Construct a new Story object
        story = new Story(is);

        // Setting the first hint
        editText = findViewById(R.id.input);
        editText.setHint(story.getNextPlaceholder());

        // Setting the first counter
        textView = findViewById(R.id.wordsLeft);
        textView.setText(String.valueOf(story.getPlaceholderCount()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Putting 'story' into the outState as a serializable
        outState.putSerializable("Story", story);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        // On restoration of the instance, gets the serializable with the key "Story"
        // Sets the hint and the text in the UI
        story = (Story) inState.getSerializable("Story");
        editText.setHint(story.getNextPlaceholder());
        textView.setText(String.valueOf(story.getPlaceholderRemainingCount()));
    }

    private class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            // Assigning the EditText and textView, and assigning values to them
            editText = findViewById(R.id.input);
            textView = findViewById(R.id.wordsLeft);
            editText.setHint(story.getNextPlaceholder());
            wordsThatLeft = story.getPlaceholderRemainingCount();

            // Loop to keep prompting the user for a new input as long as the placeholder remaining count is positive
            Log.d(TAG, "onClick:" + story.getPlaceholderRemainingCount());
            if (story.getPlaceholderRemainingCount() > 0) {
                String words = editText.getText().toString();
                story.fillInPlaceholder(words);
                Log.d(TAG, "onClick:" + story.getPlaceholderRemainingCount());
                editText.setText("");
                editText.setHint(story.getNextPlaceholder());
                textView.setText(String.valueOf(story.getPlaceholderRemainingCount()));
                if (story.isFilledIn()) {

                        // If there are no more words to be put in, transfers to the next activity using intent
                        Intent secondIntent = new Intent(WordsActivity.this, DisplayActivity.class);
                        secondIntent.putExtra("story_chosen", story);
                        startActivity(secondIntent);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {

        // Assigning the back button to go back to the first activity
        Intent intent = new Intent(WordsActivity.this, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
