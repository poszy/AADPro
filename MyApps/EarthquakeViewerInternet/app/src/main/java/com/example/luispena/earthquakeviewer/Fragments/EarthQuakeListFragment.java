package com.example.luispena.earthquakeviewer.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luispena.earthquakeviewer.Adapters.EarthquakeRecyclerViewAdapter;
import com.example.luispena.earthquakeviewer.Earthquake;
import com.example.luispena.earthquakeviewer.Models.EarthquakeViewModel;
import com.example.luispena.earthquakeviewer.R;

import java.util.ArrayList;
import java.util.List;

public class EarthQuakeListFragment extends Fragment {

    private ArrayList <Earthquake> mEarthquakes = new ArrayList<Earthquake>();
    private RecyclerView mRecyclerView;
    private EarthquakeRecyclerViewAdapter mEarthquakeAdapter = new EarthquakeRecyclerViewAdapter(mEarthquakes);
    private SwipeRefreshLayout mSwipeToRefreshView;
    private OnListFragmentInteractionListener mListener;

    protected EarthquakeViewModel earthquakeViewModel;
    public interface OnListFragmentInteractionListener {
        void onListFragmentRefreshRequested();
    }

    public EarthQuakeListFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_earthquake_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mSwipeToRefreshView = view.findViewById(R.id.swiperefresh);


        return view;
    }

    protected void updateEarthquakes() {
        if (mListener != null)
            mListener.onListFragmentRefreshRequested();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Context context =  view.getContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(mEarthquakeAdapter);


        // Swipe set the Refresher
        mSwipeToRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateEarthquakes();
            }
        });
    }

    public void setEarthquakes(List<Earthquake> earthquakes){
        for(Earthquake earthquake: earthquakes){
            if (!mEarthquakes.contains(earthquake)){
                mEarthquakes.add(earthquake);
                mEarthquakeAdapter.notifyItemInserted(mEarthquakes.indexOf(earthquake));
            }
        }
        mSwipeToRefreshView.setRefreshing(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Retrieve the Earthquake View Model for the parent Activity.
        earthquakeViewModel = ViewModelProviders.of(getActivity())
                .get(EarthquakeViewModel.class);

        // Get the data from the View Model, and observe any changes.
        earthquakeViewModel.getEarthquakes()
                .observe(this, new Observer<List<Earthquake>>() {
                    @Override
                    public void onChanged(@Nullable List<Earthquake> earthquakes) {
                        // When the View Model changes, update the List
                        if (earthquakes != null)
                            setEarthquakes(earthquakes);
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnListFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
