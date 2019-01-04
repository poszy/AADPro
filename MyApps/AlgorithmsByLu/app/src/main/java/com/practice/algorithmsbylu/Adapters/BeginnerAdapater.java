package com.practice.algorithmsbylu.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.algorithmsbylu.R;
import com.practice.algorithmsbylu.data.Algorithm;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;


public class BeginnerAdapater extends RecyclerView.Adapter<BeginnerAdapater.MyViewHolder> {

    private List<Algorithm> algorithmsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mDesciption;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.rec_title);
            mDesciption = (TextView) itemView.findViewById(R.id.rec_description);
        }
    }

    public BeginnerAdapater(List<Algorithm> algorithmsLists){

        this.algorithmsList = algorithmsLists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_list_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Algorithm algorithm = algorithmsList.get(position);

        holder.mTitle.setText(algorithm.getTitle());
        holder.mDesciption.setText(algorithm.getmDescription());
    }

    @Override
    public int getItemCount() {
        return algorithmsList.size();
    }



}
