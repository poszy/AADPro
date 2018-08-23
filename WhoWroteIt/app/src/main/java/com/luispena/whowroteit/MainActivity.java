package com.luispena.whowroteit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText bInput;
    private TextView bAuthor;
    private TextView bTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bInput  = (EditText) findViewById(R.id.bookInput);
        bTitle  = (TextView) findViewById(R.id.titleText);
        bAuthor = (TextView) findViewById(R.id.authorText);



    }

    public void searchButton(View view) {

        // bInput  returns an "Editable" datatype which needs to be converted into a string.
        String queryString = bInput.getText().toString();

        // you should not make a network connection on the UI thread.
        // If you attempt a network connection on the UI thread,
        // the Android Runtime may raise a NetworkOnMainThreadException to warn you that it's a bad idea.

    }
}
