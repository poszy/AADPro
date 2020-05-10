package com.practice.algorithmsbylu.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.practice.algorithmsbylu.Algorithms.BinarySearch;
import com.practice.algorithmsbylu.R;
import com.practice.algorithmsbylu.data.Algorithm;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;


public class BeginnerAdapater extends RecyclerView.Adapter<BeginnerAdapater.MyViewHolder> {

    private List<Algorithm> algorithmsList;


    // Setting onClickListener to Recycleview
    // 1. implement the onclick listener to the viewholder extending the recycleview
    // 2. implement the onClick handler method and check if your listener is not null - click item
    // 3. Create a public interface  with method signature of onItemclicked
    // 4. Make object reference variable of the interface from step 3.
    // 5. Create method that passes a reference object variable from step 4 ,
    // and set the global variable equal to the variable that is passed from method implementation .
    // 6. in viewHolder constructor pass a onclickListener variable
    // and make references to the variables
    // 7. pass NULL to view holder constructor
    // 8. make reference to adapter and then implement the onclick method IadapaterItemclick() inside desired class
    // 9. must implement a click method onBindViewHolder

    // Step 1.
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitle;
        private TextView mDesciption;
        // Step 6
        public View.OnClickListener mListner;

        // Step 6 cont.
        public MyViewHolder(View itemView, View.OnClickListener listner) {
            super(itemView);

            // Step 6 cont.
            mListner = listner; //
            mTitle = (TextView) itemView.findViewById(R.id.rec_title);
            mDesciption = (TextView) itemView.findViewById(R.id.rec_description);

            itemView.setOnClickListener(this); //6 bind the view
        }

        @Override
        public void onClick(View v) {
            // Step 2.
            if(mListner != null){mListner.onClick(itemView);}
        }
    }

    public BeginnerAdapater(List<Algorithm> algorithmsLists){

        this.algorithmsList = algorithmsLists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_list_item, parent, false);
        // step 7.
        return new MyViewHolder(itemView, null);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final Algorithm algorithm = algorithmsList.get(position);

        holder.mTitle.setText(algorithm.getTitle());
        holder.mDesciption.setText(algorithm.getmDescription());

        // Step 9
        // must implement a click method here.
        // This detects the click
        holder.mListner = new View.OnClickListener() {
            private static final String TAG = "BeginnerAdapter: ";

            @Override
            public void onClick(View v) {
                if (mAdapterItemclickListener != null){

                    if (position == 0) {
                       // mAdapterItemclickListener.onItemclicked(position);
                        Intent intent = new Intent(v.getContext(), BinarySearch.class);
                        v.getContext().startActivity(intent);
                        Log.v(TAG, "index=" + position);
                    } else if (position == 1){
                        Log.v(TAG, "index=" + position);
                    }else if (position == 2){
                        Log.v(TAG, "index=" + position);
                    }
                    else {
                        Log.v(TAG, "There was an internal error");

                    }
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return algorithmsList.size();
    }


    // Step 3
    // passes in a string (data) once the interface method is overriden
    public interface IAdpaterItemClick{void onItemclicked(int position);}

    // Step 4
    IAdpaterItemClick mAdapterItemclickListener;

    // Step 5
    public void setOnAdapterItemClick(IAdpaterItemClick adapterClickHandler){ mAdapterItemclickListener = adapterClickHandler; }

    public void updateList(List<Algorithm> newList) {

        algorithmsList = (List<Algorithm>) new Algorithm();
        algorithmsList.addAll(newList);
        notifyDataSetChanged();


    }
}
