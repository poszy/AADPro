package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    // Final Strings to store state information about the list of images and list index
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";
    private final static String TAG = "BodyPartFragment";

    private List<Integer> mImageIds;
    private int mListIndex;


    public BodyPartFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null) {
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        //Inflate the Layout with The fragment_body_part.xml
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get a reference to the ImageView in the gragment layout
       final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        if(mImageIds!= null){

            imageView.setImageResource(mImageIds.get(mListIndex));

            //set an onclick listener to increment the index of image id by 1
            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    if (mListIndex < mImageIds.size()-1){

                        mListIndex++;

                    }else {
                        // reset the index if it goes over the length of the list.
                        mListIndex = 0;
                    }

                    // Set the image resource to the new list item
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });


        }else {

           Log.v(TAG, "This fragment has null list of image id's");
        }

        // Return root view
        return rootView;


    }

    public void setImageId(List<Integer> imageIds){mImageIds = imageIds;}
    public void setListIndex(int index) { mListIndex = index; }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST,(ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);
    }
}
