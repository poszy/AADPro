package com.example.luispena.earthquakeviewer.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luispena.earthquakeviewer.Earthquake;
import com.example.luispena.earthquakeviewer.R;

import java.util.ArrayList;

public class EarthQuakeListFragment extends Fragment {

    private ArrayList <Earthquake> mEarthquakes = new ArrayList<Earthquake>();

    public EarthQuakeListFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_earthquake_list, container, false);
        return view;
    }
}
