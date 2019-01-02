package com.example.luispena.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.luispena.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;


    private EditText mMessageEditText;

    // Reply Variables
    private TextView mReplyHeadTextView;
    private TextView mReplyTexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Logging variables.

        Log.d(LOG_TAG,"-----------");
        Log.d(LOG_TAG, "On Create");

        mMessageEditText = (EditText) findViewById(R.id.editText_main);

        // REPLY view instances variables
        mReplyHeadTextView = (TextView) findViewById(R.id.text_header_reply);
        mReplyTexView = (TextView) findViewById(R.id.text_message_reply);

        // test to make sure the bundle is not null

        if (savedInstanceState != null) {

            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if (isVisible){
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTexView.setText(savedInstanceState.getString("reply_text"));
                mReplyTexView.setVisibility(View.VISIBLE);

            }
        }



    }



    public void launchSecondActivity(View view) {

        //Log in console everytime something happens
        Log.d(LOG_TAG, "Button_main has been pressed");

        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, message);

        // When starting and intent startActivity does
        // not expect anything to be returned
        // so we have to use StartActivityForResult()
        // to return data back to main activity
        //startActivity(intent);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST){

            if(resultCode == RESULT_OK){

                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyTexView.setVisibility(View.VISIBLE);
                mReplyTexView.setText(reply);
                mReplyTexView.setVisibility(View.VISIBLE);

            } // end inner if

        } // end if
    } // end OnActivityResult

    // override methods for loggin purposes.

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG,"onStart");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        // check to see if the heading is visible
        // if it is, save it, otherwise we
        // are still using the default layout (orientation of screen)
        if(mReplyHeadTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", mReplyTexView.getText().toString());
        }
    }

}
