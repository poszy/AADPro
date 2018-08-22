package com.luispena.simpleasynctasl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    //Key for saving the state of the TextView
    private static final String TEXT_STATE = "currentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView1);

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
    public void startTask(View view) {

        // Set text to textview
        mTextView.setText("Napping.......");

        // Start the AsyncTask.
        new SimpleAsyncTask(mTextView).execute();

    }
}
