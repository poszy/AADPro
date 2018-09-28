package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;


import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private GridView mGridview;
    private Button mButton;

    // a single-pane display refers to phones screens, a two-pane screen referes to tablets
    private boolean mTwoPane;
    int headIndex;
    int bodyIndex;
    int legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // determine if I am creating a two-pane or single-pane display

        if(findViewById(R.id.android_me_linear_layout) != null){
            mTwoPane = true;

            // hide the Next button that appears for phones.
            mButton = (Button) findViewById(R.id.next_button);
            mButton.setVisibility(View.GONE);


            // Change the gridview to space out the images more on a tablet
            mGridview = (GridView) findViewById(R.id.master_grid_view);
            mGridview.setNumColumns(2);

            if (savedInstanceState == null) {

                // Create a new bodypartfragment and display it with the FragmentManager
                BodyPartFragment headFragment = new BodyPartFragment();
                BodyPartFragment bodyFragment = new BodyPartFragment();
                BodyPartFragment legsFragment = new BodyPartFragment();

                Bundle b = getIntent().getExtras();
                assert b != null;
                int aa = b.getInt("headIndex");
                int bb = b.getInt("bodyIndex");
                int cc = b.getInt("legIndex");

                // Use a FragmentManager and transaction to add the fragment to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                // set tge kust if unage ud;s for the head gragment and set the position to the second image in the list
                headFragment.setImageId(AndroidImageAssets.getHeads());
                headFragment.setListIndex(aa);

                bodyFragment.setImageId(AndroidImageAssets.getBodies());
                bodyFragment.setListIndex(bb);

                legsFragment.setImageId(AndroidImageAssets.getLegs());
                legsFragment.setListIndex(cc);

                //Fragment transaction
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                fragmentManager.beginTransaction()
                        .add(R.id.legs_container, legsFragment)
                        .commit();

            } // end onsavedinstance stat


        }else{

            mTwoPane = false;

        }

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

        if(mTwoPane) {

            // handle two pane case
            BodyPartFragment  newFragment = new BodyPartFragment();
            BodyPartFragment nbodyFragment = new BodyPartFragment();
            BodyPartFragment nlegsFragment = new BodyPartFragment();

            switch (bodyPartNumber) {

                case 0:
                    // a head image has been clicked. give the correct image resources to the new fragment

                    newFragment.setImageId(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);

                    // replace the old head fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .replace(R.id.body_container, newFragment)
                            .replace(R.id.legs_container, newFragment)
                            .commit();

                    break;
            }

        }

        else {

            switch (bodyPartNumber) {

                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;

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

        } //enf else

    } // end OnImageSelected

}
