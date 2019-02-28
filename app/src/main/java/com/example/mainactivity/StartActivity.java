package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class StartActivity extends AppCompatActivity  {

    Spinner spinner;
    String[] objects = {"Simple", "Tarzan", "University", "Clothes", "Dance"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Declare and initialize the new Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, objects);
        spinner = (Spinner) findViewById(R.id.planets_spinner);
        spinner.setAdapter(adapter);

        // Stores the MadLib the user selected in the String story
        Button btnStart = (Button) findViewById(R.id.button);
        View.OnClickListener listener = new MyClickListener();
        btnStart.setOnClickListener(listener);
    }

    private class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            // Getting the MadLib the user choose from the spinner
            String storyChosen = spinner.getSelectedItem().toString();

            // Setting up a new intent to transfer user to the next activity
            Intent intent = new Intent(StartActivity.this, WordsActivity.class);
            intent.putExtra("story_chosen", storyChosen);
            startActivity(intent);
        }
    }
}
