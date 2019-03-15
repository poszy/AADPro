/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.sunshine.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mWeatherTextView;
    private TextView mSearchWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);
        mSearchWeather = (EditText) findViewById(R.id.et_weather_data);
        // T (4) Delete the dummy weather data. You will be getting REAL data from the Internet in this lesson.
        // T (3) Delete the for loop that populates the TextView with dummy data

        // T Call loadWeatherData to perform the network request to get the weather
        loadWeatherData();
    }

    // TODO (8) Create a method that will get the user's preferred location and execute your new AsyncTask and call it loadWeatherData
    public void loadWeatherData(){

        String weatherQuery = mSearchWeather.getText().toString();
        URL weatherURL = NetworkUtils.buildUrl(weatherQuery);
        mWeatherTextView.setText(weatherURL.toString());
        new WeatherTask().execute(weatherURL);

    }

    // TODO (5) Create a class that extends AsyncTask to perform network requests
    public class WeatherTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... params) {
            URL searchURL = params[0];
            String weatherResults = null;
            try {
                weatherResults = NetworkUtils.getResponseFromHttpUrl(searchURL);

            }catch (IOException e){
                e.printStackTrace();
            }
            return weatherResults;
        }

        @Override
        protected void onPostExecute(String weatherResults) {

            if(weatherResults != null & !weatherResults.equals("")){
                mWeatherTextView.setText(weatherResults);
            }

        }
    }
    }

