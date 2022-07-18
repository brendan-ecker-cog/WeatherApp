package com.example.weatherappsimple;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * View adapter for the recycler view.
 */
public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.WeatherView> {


    private static final String TAG = "ViewAdapter";
    String[] data;

    public ViewAdapter(String[] mData){
        data = mData;
    }

    @NonNull
    @Override
    //Getting "Planks"
    public WeatherView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "Getting Planks");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_plank, parent, false);
        return new WeatherView(view);
    }

    //Counting how many items
    @Override
    public int getItemCount() {
        Log.d(TAG, "Counting");
        return data.length;
    }

    //Writing on the planks
    @Override
    public void onBindViewHolder(@NonNull WeatherView holder, int position) {
        Log.d(TAG, "Writing");
        holder.tv.setText(data[position]);
    }

    //The view on the "Plank"
    protected class WeatherView extends RecyclerView.ViewHolder{

        //Managing the planks
        TextView tv;
        public WeatherView(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            Log.d(TAG, "Managing");
        }
    }
}
