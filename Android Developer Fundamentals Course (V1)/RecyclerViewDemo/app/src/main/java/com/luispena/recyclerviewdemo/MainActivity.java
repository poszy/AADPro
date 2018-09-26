package com.luispena.recyclerviewdemo;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    // Declare Class Variables
    private final LinkedList<String> mWordList = new LinkedList<>();
    private int mCounter = 0;

    // For RecyclerView and adapter
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FAB : floating action button
        // to use Recycler views and FAB I need to enable
        // the android support libraries

        // Populate a List of words
        for(int i =0; i < 20; i++){
            // Add word to list as long as I is less than 20
            mWordList.addLast("Word " + mCounter++);
            Log.d("WordList", mWordList.getLast());

        }


        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        // Create an adapter and supplu the data to be displayed
        mAdapter = new WordListAdapter(this, mWordList);

        // Connect the adapter with the recylcerview
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Add a floating aciton click handler for creating new entries

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int wordListSize = mWordList.size();

                // add a new word to the end of the wordlsit
                mWordList.addLast("+ Word " + wordListSize);

                // Notify the adatper, that the data has changed so
                // it can update the Reccler to display the data

                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);

                //scroll to the bottom
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });


    }
}


/*
*
* I need 6 things in order to create a proper RecylerView
*
* Data: I will use the mWordList.
*
* A RecyclerView: The scrolling list that contains the list items.
*
* Layout for one item of data: All list items look the same.
*
* A layout manager: The layout manager handles the organization (layout) of user interface components in a view. You have already used the LinearLayout in a previous practical where the Android system handles the layout for you.
* RecyclerView requires an explicit layout manager to manage the arrangement of list items contained within it. This layout could be vertical, horizontal, or a grid. You will use a vertical linear layout manager provided by Android.
*
* An adapter: The adapter connects your data to the RecyclerView. It prepares the data in a view holder.
* You will create an adapter that inserts into and updates your generated words in your views.
*
* A view holder:Inside your adapter, you will create a ViewHolder class that contains the view information for displaying one item from the item's layout
*
*
*  Data [ Item(word) ] -> Adapter [com.luispena.recyclerviewdemo.WordListAdapter(ViewHolder)] -> Activity [RecylcerView(LayoutManager(WordListItem(word)))]
*
*
* */
