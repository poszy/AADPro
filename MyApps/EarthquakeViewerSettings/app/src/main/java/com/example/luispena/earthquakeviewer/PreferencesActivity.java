package com.example.luispena.earthquakeviewer;

import android.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;

public class PreferencesActivity extends AppCompatActivity {

    public static final String PREF_AUTO_UPDATE = "PREF_AUTO_UPDATE";
    public static final String USER_PREFERENCE  = "USER_PREFERENCE";
    public static final String PREF_MIN_MAG     = "PREF_MIN_MAG";
    public static final String PREF_UPDATE_FREQ = "PREF_UPDATE_FREQ";

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
    }

    // I have to use this class because once I extend AppCompactActivity
    // My preferenceACtivity class cannot extend PReferenceFragmentCompat
    // so I need an inner fragment class for inheritance
    public static class PrefFragment extends PreferenceFragmentCompat{

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {

            //String S is root key
            setPreferencesFromResource(R.xml.userpreferences, null);
        }
    }
}
