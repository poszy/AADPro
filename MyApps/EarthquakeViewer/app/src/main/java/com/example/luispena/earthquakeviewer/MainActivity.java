package com.example.luispena.earthquakeviewer;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.luispena.earthquakeviewer.Fragments.EarthQuakeListFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";
    EarthQuakeListFragment mEarthquakeListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        if (savedInstanceState == null){

            FragmentTransaction ft = fm.beginTransaction();

            mEarthquakeListFragment = new EarthQuakeListFragment();
            ft.add(R.id.main_activity_frame, mEarthquakeListFragment,TAG_LIST_FRAGMENT);
            ft.commitNow();
        }else{
            mEarthquakeListFragment = (EarthQuakeListFragment)fm.findFragmentByTag(TAG_LIST_FRAGMENT);
        }

        Date now = Calendar.getInstance().getTime();
        List<Earthquake> dummyQuakes = new ArrayList<Earthquake>(0);
        dummyQuakes.add(new Earthquake("0", now, "San Jose", null, 7.3, null));
        dummyQuakes.add(new Earthquake("1", now, "LA", null, 4.3, null));


        mEarthquakeListFragment.setEarthquakes(dummyQuakes);

    }
}
