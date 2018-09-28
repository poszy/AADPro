package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;


import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private GridView mGridview;
    private Button mButton;
    int headIndex;
    int bodyIndex;
    int legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageSelected(int position) {

        // Define the behavior for on imageSelected
        Toast.makeText(this, "Position Clicked = " + position, Toast.LENGTH_SHORT).show();

        // bodyPartNumber will b - 0 for the ehad fragment, 1 for the body and 2 for the leg fragment
        // dividing by 12 gives us these integer alues because each list of images resources has a size of 12
        int bodyPartNumber = position / 12;

        // Store the correct list index no matter where in the image list has been clicked
        // this ensures that the index will always be a value between 0 -11
        int listIndex = position -12*bodyPartNumber;

        switch (bodyPartNumber){

            case 0: headIndex = listIndex;
                break;
            case 1: bodyIndex = listIndex;
                break;
            case 2: legIndex = listIndex;
                break;
            default: break;

        }

        // Put the information in a bundle and attach it to an intent that will launch an AndroidMeActivity

        Bundle b = new Bundle();

        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);

        // Attach the bundle to an intent
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        mButton = (Button) findViewById(R.id.next_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    } // end OnImageSelected

}
