package com.practice.algorithmsbylu.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.algorithmsbylu.R;

public class FragmentBeginner extends Fragment {

    public static String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private TextView textView;

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
        //mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_beginner, null, false);
        textView = (TextView) rootView.findViewById(R.id.text_beginner);

        return rootView;
    }
}
