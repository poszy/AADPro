package com.example.lu.startsignpicker.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lu.startsignpicker.R;

public class StarSignPickerAdapter extends RecyclerView.Adapter<StarSignPickerAdapter.ViewHolder> {

    private String[] mStarSigns = {
            "Aries","Taurus","Gemini","Cancer", "Leo","Virgo","Libra",
            "Scorpio", "Sagittarius","Capricorn","Aquarius","Pisces"};

    public StarSignPickerAdapter(){

    }

    @NonNull
    @Override
    public StarSignPickerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_layout, viewGroup, false);

        return new ViewHolder(v,null);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.mTextView.setText(mStarSigns[i]);

        viewHolder.mListner = new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if (mAdapaterItemclickListener != null){ mAdapaterItemclickListener.onItemClicked(mStarSigns[i]); }
            }
        };

    }


    @Override
    public int getItemCount() {

        return
                mStarSigns == null // if (mStarSign == null)
                ? 0 // { mStarSign == 0 }
                : mStarSigns.length; // else {mStarSigns // is not empty and return the total number of items in the data set}
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTextView;
        public View.OnClickListener mListner;


        public ViewHolder(@NonNull View itemView, View.OnClickListener listner) {
            super(itemView);
            mListner = listner;
            mTextView = itemView.findViewById(R.id.itemTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(mListner != null){mListner.onClick(itemView);}
        }

    }

    public interface IAdapterItemClick{ void onItemClicked(String mselectedItem);}

    IAdapterItemClick mAdapaterItemclickListener;

    public void setOnAdapaterItemclick(IAdapterItemClick adapaterItemclickHandler){mAdapaterItemclickListener = adapaterItemclickHandler;}
}
