package com.example.luispena.earthquakeviewer;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.luispena.earthquakeviewer.Fragments.EarthQuakeListFragment;
import com.example.luispena.earthquakeviewer.Models.EarthquakeViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EarthQuakeListFragment.OnListFragmentInteractionListener{

    private static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";
    EarthQuakeListFragment mEarthquakeListFragment;
    EarthquakeViewModel earthquakeViewModel;

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

        //Date now = Calendar.getInstance().getTime();
        //List<Earthquake> dummyQuakes = new ArrayList<Earthquake>(0);
        //dummyQuakes.add(new Earthquake("0", now, "San Jose", null, 7.3, null));
        //dummyQuakes.add(new Earthquake("1", now, "LA", null, 4.3, null));
        //mEarthquakeListFragment.setEarthquakes(dummyQuakes);

        earthquakeViewModel = ViewModelProviders.of(this).get(EarthquakeViewModel.class);

    }

    @Override
    public void onListFragmentRefreshRequested() {
        updateEarthquakes();
    }

    private void updateEarthquakes(){
        earthquakeViewModel.loadEarthQuakes();
    }
}
