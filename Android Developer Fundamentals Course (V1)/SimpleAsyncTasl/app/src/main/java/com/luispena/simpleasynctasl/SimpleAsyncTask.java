package com.luispena.simpleasynctasl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, Void, String>{

    // 1st param is for doInBackGround
    // 2nd param is for onProgressUpdate
    // 3rd param is for onPostExecute

    private TextView mTextView;
    //Key for saving the state of the TextView
    private static final String TEXT_STATE = "currentText";


    public SimpleAsyncTask(TextView tv) {
        mTextView = tv;
    }

    @Override
    protected String doInBackground(Void... voids) {

        // Generate a random number
        Random r = new Random();
        int n  = r.nextInt(11);

        //Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 200;

        // Sleep for the random amount of time
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";

    }

    protected void onPostExecute(String result){
        mTextView.setText(result);
    }

}
