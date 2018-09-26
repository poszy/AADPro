package com.luispena.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;


/**
 * Created by luispena on 5/28/18.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>  {

    // Class Variables
    private final LinkedList<String> mWordList;

    // com.luispena.recyclerviewdemo.WordListAdapter needs a constructor
    // that initilizes the word list form the data
    // to create a view for a list item
    // com.luispena.recyclerviewdemo.WordListAdapter needs to inflate the XML for a list item
    // use layout inflator for that job, it reads an xml layout
    private LayoutInflater mInflater;


    // Constructore for com.luispena.recyclerviewdemo.WordListAdapter
    public WordListAdapter(Context context, LinkedList<String> wordList) {

        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;

    }

    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate the layout and return a view holder with the layout and adapter
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(WordListAdapter.WordViewHolder holder, int position) {
        // This method connects my data to the view holder
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }




    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView wordItemView;
        final WordListAdapter mAdapter;


        public WordViewHolder(View itemView, WordListAdapter adapter) {

            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            // EveryTime an Item is Clicked

            // Get the position of the item getting clicked
            int mPosition = getLayoutPosition();
            // use mPosition to get the affected item in mWordList
            String element = mWordList.get(mPosition);

            // Change the word in the mWordList
            mWordList.set(mPosition, "Clicked! " + element);

            //notify the adapter , that the data has changed so
            // it can update the RecyclerView to dsiplay the data
            mAdapter.notifyDataSetChanged();

        } // end onClick

    } // End WordViewHolder Class


}
