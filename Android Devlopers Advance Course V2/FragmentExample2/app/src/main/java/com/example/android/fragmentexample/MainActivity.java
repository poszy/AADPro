/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmentexample;


import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button mButton;
    private Boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.mButton);

        /*
         To manage a Fragment in your Activity, use FragmentManager.
         To perform a Fragment transaction in your Activity—such as adding, removing, or replacing a Fragment—use methods in FragmentTransaction.

        * */

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFragmentDisplayed){

                    displayFragment();
                }
                else{
                    closeFragment();
                }
            }
        });

        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                mButton.setText(R.string.close);
            }
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void displayFragment(){

        SimpleFragment simpleFragment = SimpleFragment.newInstance();

        // start a transaction, get an instance of FragmentManager using getSupportFragmentManager()
        FragmentManager fManager = getSupportFragmentManager();

        // get an instance FragmentTransaction that uses beginTransaction().
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();

        /**
         * This code adds a new fragment using .add() transaction method.
         * @param 1 : layout resource  fragment_container, for the ViewGroup in which the gragment should be placed
         * @param 2 : pass in the fragment (simplefragment)
         *  the add() trasaction, calls the coe for addTobackStack(null), in order to add
         *  the transaction to a back stack which is handled bay the activity.
         *  it allows the user to to return to the previous Fragment state by pressing the back button
         */

        fragmentTransaction.add(R.id.fragment_ratings, simpleFragment).addToBackStack(null).commit();

        // update the button text upon click
        mButton.setText(R.string.close);

        // set boolean flag to indicate fragment is open
        isFragmentDisplayed = true;

    }


    public void closeFragment(){
        // Get the fragment manager
        FragmentManager fManager = getSupportFragmentManager();

        // Checl if the fragment is already showing

        SimpleFragment simpleFragment = (SimpleFragment) fManager.findFragmentById(R.id.fragment_ratings);

        if (simpleFragment != null){

           FragmentTransaction fragmentTransaction = fManager.beginTransaction();
           fragmentTransaction.remove(simpleFragment).commit();

        }

        mButton.setText(R.string.open);

        isFragmentDisplayed = false;

    }


}
