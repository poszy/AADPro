package com.luispena.androidcompat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mHelloTextView;

    private String[] mCollorArray = {
        "red", "pink", "purple", "deep_purple",
        "indigo", "blue", "light_blue", "cyan", "teal", "green",
        "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
        "brown", "grey", "blue_grey", "black"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloTextView = (TextView) findViewById(R.id.hello_textview);

        // restore saved instance state ( the text color)
        if (savedInstanceState != null) {

            mHelloTextView.setTextColor(savedInstanceState.getInt("color"));

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save the current text color
        outState.putInt("color", mHelloTextView.getCurrentTextColor());
    }



    public void changeColor(View view) {

        Random random = new Random();
        String colorName = mCollorArray[random.nextInt(20)];

        // get the color identifier that matches the color name
        int colorResourceName = getResources().getIdentifier(colorName, "color",
                getApplicationContext().getPackageName());

        // get the color ID from the resources
        int colorRes = ContextCompat.getColor(this, colorResourceName);

        // Set the text color
        mHelloTextView.setTextColor(colorRes);

    }
}
