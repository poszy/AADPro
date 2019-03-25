package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private String mFrocase;
    private TextView mWeatherDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO (2) Display the weather forecast that was passed from MainActivity
        mWeatherDisplay = (TextView) findViewById(R.id.tv_display_weather);
        Intent getExtraFromParent = getIntent();

        if(getExtraFromParent != null){
            if(getExtraFromParent.hasExtra(Intent.EXTRA_TEXT)){
                mFrocase = getExtraFromParent.getStringExtra(Intent.EXTRA_TEXT);
                mWeatherDisplay.setText(mFrocase);
            }
        }
    }
}