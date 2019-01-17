package com.example.luispena.earthquakeviewer.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luispena.earthquakeviewer.Earthquake;
import com.example.luispena.earthquakeviewer.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EarthquakeRecyclerViewAdapter extends RecyclerView.Adapter<EarthquakeRecyclerViewAdapter.ViewHolder> {

    private final List<Earthquake> mEarthQuakes;

    public EarthquakeRecyclerViewAdapter(List<Earthquake> earthquakes){ mEarthQuakes = earthquakes;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_earthquake, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {


        /*viewHolder.earthquake = mEarthQuakes.get(position);
        viewHolder.detailsView.setText(mEarthQuakes.get(position).toString());*/
        // this earthwauke object is equal to the earthquake object that is being pulled from the view item popsition.
        Earthquake earthquake = mEarthQuakes.get(position);
        viewHolder.date.setText(TIME_FORMAT.format(earthquake.getmDate()));
        viewHolder.details.setText(earthquake.getmDetails());
        viewHolder.magnitude.setText(MAGNITUDE_FORMAT.format(earthquake.getmMagnitude()));

    }

    @Override
    public int getItemCount() {
        return mEarthQuakes.size();
    }

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH.mm", Locale.US);
    private static  final NumberFormat MAGNITUDE_FORMAT = new DecimalFormat("0.0");

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Version 1
        //public final View parentView;
        //public final TextView detailsView;
        //public Earthquake earthquake;

        public final TextView date;
        public final TextView details;
        public final TextView magnitude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //From version 1
            //parentView = itemView;\
            //detailsView = (TextView) itemView.findViewById(R.id.list_item_earthquake_details);

            date = (TextView) itemView.findViewById(R.id.date);
            details = (TextView) itemView.findViewById(R.id.details);
            magnitude = (TextView) itemView.findViewById(R.id.magnitude);

        }

       /* @Override
        public String toString() {
            return super.toString() + " '" + detailsView + "'";
        }*/
    }
}
