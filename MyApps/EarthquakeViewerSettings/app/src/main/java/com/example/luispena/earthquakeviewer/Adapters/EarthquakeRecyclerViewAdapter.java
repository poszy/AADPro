package com.example.luispena.earthquakeviewer.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luispena.earthquakeviewer.Earthquake;
import com.example.luispena.earthquakeviewer.R;
import com.example.luispena.earthquakeviewer.databinding.ListItemEarthquakeBinding;

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
        // From Version 2
        //View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_earthquake, viewGroup, false);
        //return new ViewHolder(view, binding);
        ListItemEarthquakeBinding binding = ListItemEarthquakeBinding.inflate(
                LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {


        /*viewHolder.earthquake = mEarthQuakes.get(position);
        viewHolder.detailsView.setText(mEarthQuakes.get(position).toString());*/
        // this earthwauke object is equal to the earthquake object that is being pulled from the view item popsition.

        // From version 2
        Earthquake earthquake = mEarthQuakes.get(position);
        /*viewHolder.date.setText(TIME_FORMAT.format(earthquake.getDate()));
        viewHolder.details.setText(earthquake.getDetails());
        viewHolder.magnitude.setText(MAGNITUDE_FORMAT.format(earthquake.getMagnitude()));*/

        viewHolder.binding.setEarthquake(earthquake);
        viewHolder.binding.executePendingBindings();

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

        //Version 2
        /*public final TextView date;
        public final TextView details;
        public final TextView magnitude;*/

        // this class is autogenerated when I added the binding <layout> tags & <variable> tags to
        // my list_item_earthquake file
        public final ListItemEarthquakeBinding binding;


        public ViewHolder( ListItemEarthquakeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setTimeformat(TIME_FORMAT);
            binding.setMagnitudeformat(MAGNITUDE_FORMAT);
            //From version 1
            //parentView = itemView;\
            //detailsView = (TextView) itemView.findViewById(R.id.list_item_earthquake_details);

           /* This from version 2 of the app
            to use the databinding technique I must overload the constructor to
            take in a ListItemEarthQuakeBinding object and set the time, format with its accessor methods.*/
           /* date = (TextView) itemView.findViewById(R.id.date);
            details = (TextView) itemView.findViewById(R.id.details);
            magnitude = (TextView) itemView.findViewById(R.id.magnitude);*/


        }

       /* @Override
        public String toString() {
            return super.toString() + " '" + detailsView + "'";
        }*/
    }
}
