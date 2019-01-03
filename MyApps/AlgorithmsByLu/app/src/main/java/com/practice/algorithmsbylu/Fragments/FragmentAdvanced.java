package com.practice.algorithmsbylu.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.algorithmsbylu.R;

public class FragmentAdvanced extends Fragment {

    public static String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static FragmentBeginner newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentBeginner fragment = new FragmentBeginner();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_advanced, null, false);

        return rootView;
    }
}
