package com.practice.algorithmsbylu.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.algorithmsbylu.Adapters.BeginnerAdapater;
import com.practice.algorithmsbylu.R;
import com.practice.algorithmsbylu.data.Algorithm;

import java.util.ArrayList;
import java.util.List;

public class FragmentBeginner extends Fragment {

    public static String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private TextView textView;


    private List<Algorithm> algorithmList = new ArrayList<>();
    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

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

        mRecyclerview = (RecyclerView) rootView.findViewById(R.id.beginner_recycler_view);

        mAdapter = new BeginnerAdapater(algorithmList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(requireContext());
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mRecyclerview.setAdapter(mAdapter);
        // textView = (TextView) rootView.findViewById(R.id.text_beginner);

        prepareData();

        return rootView;
    }

    private void prepareData() {
        Algorithm algorithm = new Algorithm(getString(R.string.title_binary_search), getString(R.string.desc_binary_search));
        algorithmList.add(algorithm);

        algorithm = new Algorithm(getString(R.string.title_selection_sort), getString(R.string.desc_selection_sort));
        algorithmList.add(algorithm);

        algorithm = new Algorithm(getString(R.string.title_recursion), getString(R.string.desc_recursion));
        algorithmList.add(algorithm);

        mAdapter.notifyDataSetChanged();
    }
}
