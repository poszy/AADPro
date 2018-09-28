/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

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

        } // end onsavedinstance state

    }
}
