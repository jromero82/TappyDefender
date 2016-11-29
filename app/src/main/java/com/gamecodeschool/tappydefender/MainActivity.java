package com.gamecodeschool.tappydefender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    // This is the entry point to the game
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Here we set the UI layout as the view
        setContentView(R.layout.activity_main);

        // Get a reference to the button in our layout
        final Button buttonPlay = (Button)findViewById(R.id.buttonPlay);

        // listen for clicks
        buttonPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // Create a new Intent object.
        Intent i = new Intent(this, GameActivity.class);
        // Start the GameActivity class via the Intent
        startActivity(i);
        // Now shut the activity down
        finish();
    }
}