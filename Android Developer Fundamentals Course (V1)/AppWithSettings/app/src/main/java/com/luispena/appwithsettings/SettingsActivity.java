package com.luispena.appwithsettings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {
/* *
   * se a specialized Fragment subclass to display a list of settings.
   * The best practice is to use a regular Activity that hosts a PreferenceFragment that displays the app settings.
   * Fragments like PreferenceFragment provide a more flexible architecture for your app, compared to using activities alone.
   * A fragment is like a modular section of an activity—it has its own lifecycle and receives its own input events,
   * and you can add or remove a fragment while the activity is running.
  * */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
