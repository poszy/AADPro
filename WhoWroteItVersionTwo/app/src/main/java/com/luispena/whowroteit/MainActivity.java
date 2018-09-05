package com.luispena.whowroteit;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText bInput;
    private TextView bAuthor;
    private TextView bTitle;
    private Button   bButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bInput  = (EditText) findViewById(R.id.bookInput);
        bTitle  = (TextView) findViewById(R.id.titleText);
        bAuthor = (TextView) findViewById(R.id.authorText);
        bButton = (Button) findViewById(R.id.searchButton);


    }

    public void searchButton(View view) {

        // bInput  returns an "Editable" datatype which needs to be converted into a string.
        String queryString = bInput.getText().toString();


        // Hide the keyboard when the button is pressed
        InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0) {
            new FetchBook(bInput, bAuthor).execute(queryString);
            bAuthor.setText("");
            bTitle.setText(R.string.loading);
        }

        else {
            if (queryString.length() == 0) {
                bAuthor.setText("");
                bTitle.setText("Please enter a search term");
            } else {
                bAuthor.setText("");
                bTitle.setText("Please check your network connection and try again.");
            }
        }


        // you should not make a network connection on the UI thread.
        // If you attempt a network connection on the UI thread,
        // the Android Runtime may raise a NetworkOnMainThreadException to warn you that it's a bad idea.

        new FetchBook(bTitle, bAuthor).execute(queryString);
    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
