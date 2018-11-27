package com.example.android.fragmentexample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;



/*
*
*  In this fragment I created a totally separate fragment, layout,
*  this is one way to do it but another way to do it is to add another fragment to the existing fragment_simple file
*
*
* */

public class RatingFragment extends Fragment {


    public RatingFragment(){


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View rootView = inflater.inflate(R.layout.fragment_rating, container, false);
        final RatingBar ratingBar = rootView.findViewById(R.id.mRatingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                Toast.makeText(getActivity(), " You have rated this song to " + ratingBar.getRating() + " !", Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }



}
