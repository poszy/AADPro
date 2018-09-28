package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;


public class MasterListFragment extends Fragment {

    private GridView mGridview;

    OnImageClickListener mCallback;
    public interface OnImageClickListener{

        void onImageSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure the host activity has implemented the callback interface

        try{

            mCallback = (OnImageClickListener) context;

        }catch(ClassCastException e){

            throw new ClassCastException(context.toString() + "must implement onImageClickListener!");

        }
    }

    public MasterListFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        mGridview = (GridView) rootView.findViewById(R.id.master_grid_view);

        // create the adapter
        // takes in the contet and arrraylist of all image resources
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        // Set the adapter on the GridView
        mGridview.setAdapter(mAdapter);

        // Set a click listener on the gridview and trigger the callback onImageSelected when an item is clicked

        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onImageSelected(position);
            }
        });

        return rootView;
    }




    //create MasterListAdapter
}
