
package com.luispena.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     // Class Global Variables
    int mScoreOne = 0;
    int mScoreTwo = 0;

    //Tag to be used as the key in OnSavedInstanceState
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    private TextView teamOne;
    private TextView teamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // In android API 26 and above you no longer need to case (TextView)
        // https://stackoverflow.com/questions/44902651/no-need-to-cast-the-result-of-findviewbyid
        teamOne = (TextView) findViewById(R.id.score_one);
        teamTwo = (TextView) findViewById(R.id.score_two);


        if (savedInstanceState != null) {
            mScoreOne = savedInstanceState.getInt(STATE_SCORE_1);
            mScoreTwo = savedInstanceState.getInt(STATE_SCORE_2);

            //Set the score text views
            teamOne.setText(String.valueOf(mScoreOne));
            teamTwo.setText(String.valueOf(mScoreTwo));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Save the scores
        outState.putInt(STATE_SCORE_1, mScoreOne);
        outState.putInt(STATE_SCORE_2, mScoreOne);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // change the label of the menu based on the state of the app
        int nightMode = AppCompatDelegate.getDefaultNightMode();

        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){

            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);

        } //end if

        else {

            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);

        } // end else

        return true;

    } // end onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Check if the correct item was clicked
        if(item.getItemId()==R.id.night_mode){

            int nightMode = AppCompatDelegate.getDefaultNightMode();

            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            } // end second if

            else{

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } //end else

            // this will recreate the activity when theme changes
            recreate();

            return true;
        } // end first if

        //Recreate the activity for the theme change to take effect
        recreate();
        return super.onOptionsItemSelected(item);
    } // end onOptionsItemSelected

    public void decreaseScore(View view) {
        Toast.makeText(getApplicationContext(), "d score", Toast.LENGTH_SHORT).show();


        // Get the id of the button that was clicked
        int viewID = view.getId();

        switch (viewID){

            // if it was on Team 1
            case R.id.decrease_one:
                // decrease the score
                Toast.makeText(getApplicationContext(), "Decreased Team 1 Score", Toast.LENGTH_SHORT).show();

                mScoreOne--;
                teamOne.setText(String.valueOf(mScoreOne));
                break;

            case R.id.decrease_two:
                Toast.makeText(getApplicationContext(), "Decreased Team 2 Score", Toast.LENGTH_SHORT).show();

                mScoreTwo--;
                teamTwo.setText(String.valueOf(mScoreTwo));

                break;

        } // end switch

    } // end decreaseScore


    public void increaseScore(View view) {
        Toast.makeText(getApplicationContext(), "i score", Toast.LENGTH_SHORT).show();

        // Get the id of the button that was clicked
        int viewID = view.getId();

        switch (viewID){

            // if it was on Team 1]
            case R.id.increase_one:
                Toast.makeText(getApplicationContext(), "Increased Team 1 Score", Toast.LENGTH_SHORT).show();

                // increase the score and update TextView
                mScoreOne++;
                teamOne.setText(String.valueOf(mScoreOne));

                break;

            case R.id.increase_two:
                Toast.makeText(getApplicationContext(), "Decreased Team 2 Score", Toast.LENGTH_SHORT).show();


                mScoreTwo++;
                teamTwo.setText(String.valueOf(mScoreTwo));

                break;

        } // end switch
    }
}
