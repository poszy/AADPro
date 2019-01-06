package com.practice.algorithmsbylu.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private List<Algorithm> algorithmList = new ArrayList<>();
    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;

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


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        String name =" FragmentBeginner.java";
        Log.d("TAG", "onCreateView event : " + name);

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_beginner, null, false);

        mRecyclerview = (RecyclerView) rootView.findViewById(R.id.beginner_recycler_view);

        mAdapter = new BeginnerAdapater(algorithmList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(requireContext());
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mRecyclerview.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(mAdapter);

        prepareData();


        return rootView;
    }

    private void prepareData() {

        // This will clear the Algorithm list. This is away around onCreateView adding the same data to
        // the recycler view. without this, every time BeginnerFragment is recreated after being destroyed from the stack
        // it will increment the exact amount of items being added below.
        // this will work for now as there is not a clear solution to implement everything in onCreate.
        // onCreate only gets called once, once the fragment is recreated, oncreateview gets called again and the same data will
        // keep being added as long as the BeginnerFragment keeps being recreated.

        algorithmList.clear();

        Algorithm algorithm = new Algorithm(getString(R.string.title_binary_search), getString(R.string.desc_binary_search));
        algorithmList.add(algorithm);

        algorithm = new Algorithm(getString(R.string.title_selection_sort), getString(R.string.desc_selection_sort));
        algorithmList.add(algorithm);

        algorithm = new Algorithm(getString(R.string.title_recursion), getString(R.string.desc_recursion));
        algorithmList.add(algorithm);

        mAdapter.notifyDataSetChanged();
    }
}
