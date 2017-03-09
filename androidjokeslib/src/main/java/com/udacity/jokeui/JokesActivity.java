package com.udacity.jokeui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import in.eous.jokeui.R;

public class JokesActivity extends AppCompatActivity {

    private static final String JOKES_EXTRA = "com.udacity.gradle.INTENT_EXTRA_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        String jokeString = getIntent().getStringExtra(JOKES_EXTRA);
        TextView textview = (TextView) findViewById(R.id.jokes_txtvw);
        textview.setText(jokeString);
    }

    public static void startActivity(Activity parent, @NonNull String joke) {
        Intent intent = new Intent(parent, JokesActivity.class);
        intent.putExtra(JOKES_EXTRA, joke);
        parent.startActivity(intent);
    }
}
