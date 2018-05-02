package com.example.luispena.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Priate Variables for Text edits
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign private edit text a view layout
        mWebsiteEditText = (EditText) findViewById(R.id.website_edittext);
        mLocationEditText = (EditText) findViewById(R.id.location_edittext);
        mShareTextEditText = (EditText) findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {

        // Once text is entered. url will be the same as text entered
        String url = mWebsiteEditText.getText().toString();
        // Take that string and parse it as uri
        Uri webpage = Uri.parse(url);

        // Create new implicit intent and pass the action view and webpage var
        // this tells the intent to act on an action rather than
        // an explicit component (activity class).
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // resolveActivity() and Android package manager will find
        // an activity that can handle my implicit intent
        if(intent.resolveActivity(getPackageManager()) != null){

            // getPackageManger basically will search for installed
            // apps  on device to make sure there is at lease one activity
            // that can handle the request

            startActivity(intent);
        }
        else{
            Log.d("ImplicitIntents","Can't handle this!");
        }
    }




    public void openLocation(View view) {

        String loc = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {

    String txt = mShareTextEditText.getText().toString();
    String mimeType = "text/plain";

    // The actitiy that lauches this share intent (this
    // setType() the MIME type of the item to be shared
    // setChooserTitle() the title that appears on the system app chooser
    // setText() the actual text to be shared
    // startChooser() show the system app chooser and send the intent

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with:")
                .setText(txt)
                .startChooser();



    }
}
