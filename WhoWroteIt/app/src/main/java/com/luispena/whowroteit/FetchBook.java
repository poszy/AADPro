package com.luispena.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FetchBook extends AsyncTask<String, Void, String> {


    private TextView mTitleText;
    private TextView mAuthorText;

    public FetchBook(TextView mTitleText, TextView mAuthorText) {

        this.mTitleText = mTitleText;
        this.mAuthorText = mAuthorText;

    }

    @Override
    protected String doInBackground(String... params) {


        return NetworkUtils.getBookImage(params[0]);

    }

    @Override
    protected void onPostExecute(String s) {
        // what happens when updating the ui.
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);
            //JSON repsonse has an items key that returns every book
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            // iterate throught the items array
            for (int i = 0; i < itemsArray.length(); i++) {

                JSONObject book = itemsArray.getJSONObject(i);  // get the current item in the json array
                String title = null;
                String authors = null;

                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                // check to see if the author and titles exists, if they do not
                // throw and error. this is better than debugging the code on the networking code level
                try {

                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (title != null && authors != null) {
                    mTitleText.setText(title);
                    mAuthorText.setText(authors);
                    return;
                } // end if

            } // end For

            mTitleText.setText("No Results Found");
            mAuthorText.setText("");

        } catch (JSONException e) {
            mTitleText.setText("No Results Found");
            mAuthorText.setText("");
            e.printStackTrace();

        }


    }

}
