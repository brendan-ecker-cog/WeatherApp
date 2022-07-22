package com.example.weatherappsimple;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

/**
 * The View Adapter for the CityActivity Recycler View
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityView>{
    private static final String TAG = CityAdapter.class.getSimpleName();
    private List<City> cities;

    public CityAdapter(List<City> mData){cities = mData;}

    /**
     * Creating the "Planks" and giving them to the CityView
     */
    @NonNull
    @Override
    public CityView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "Getting Planks");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.city_plank, parent, false);
        return new CityAdapter.CityView(view);
    }

    /**
     *  Writing on the "Planks"
     *  Putting information in the TextViews of the layout
     * @param holder    The current CityView
     * @param position  The current "index" of what city we are using
     */
    @Override
    public void onBindViewHolder(@NonNull CityView holder, int position) {
        Log.i(TAG, "Writing Plank");
        City current = cities.get(position);
        holder.title.setText(current.name.toUpperCase(Locale.ROOT) + ", " + current.region.toUpperCase(Locale.ROOT));
        holder.tempF.setText("Temp(F): " + current.tempF);
        holder.tempC.setText("Temp(C): " + current.tempC);
    }

    /**
     * Gets the size of the cities list
     *
     * @return  The total amount of cities
     */
    @Override
    public int getItemCount() {
        Log.i(TAG, cities.size() + " ");
        return cities.size();
    }


    /**
     * The views on the "Plank"
     * The views in the layout that need to be populated.
     */
    protected class CityView extends RecyclerView.ViewHolder{
        //Managing the planks
        TextView title;
        TextView tempF;
        TextView tempC;
        public CityView(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            tempF = itemView.findViewById(R.id.tempF);
            tempC = itemView.findViewById(R.id.tempC);
            Log.d(TAG, "Managing Planks");
        }
    }
}
