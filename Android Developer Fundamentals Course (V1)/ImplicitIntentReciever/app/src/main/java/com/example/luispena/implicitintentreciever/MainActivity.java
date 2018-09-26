package com.example.luispena.implicitintentreciever;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // I have to process the intent coming
    // from my other app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get intent
        Intent intent = getIntent();

        // get URI
        Uri uri = intent.getData();


        // lets make sure uri is not null
        // and if its not create a string from that URI object

        if(uri != null){

            String uri_string = "URI: " + uri.toString();
            TextView textView = (TextView) findViewById(R.id.text_uri_message);
            textView.setText(uri_string);

        }
    }
}
