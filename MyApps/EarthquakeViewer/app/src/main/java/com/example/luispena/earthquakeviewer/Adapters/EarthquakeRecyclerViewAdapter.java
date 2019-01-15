package com.example.luispena.earthquakeviewer.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luispena.earthquakeviewer.Earthquake;
import com.example.luispena.earthquakeviewer.R;

import java.util.List;

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

        viewHolder.earthquake = mEarthQuakes.get(position);
        viewHolder.detailsView.setText(mEarthQuakes.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return mEarthQuakes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View parentView;
        public final TextView detailsView;
        public Earthquake earthquake;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentView = itemView;
            detailsView = (TextView) itemView.findViewById(R.id.list_item_earthquake_details);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + detailsView + "'";
        }
    }
}
