package com.ujjwaltechnolabs.rolapartner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ujjwaltechnolabs.rolapartner.Model.VehicleResponse;
import com.ujjwaltechnolabs.rolapartner.R;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {
    private Context context;
    ArrayList<VehicleResponse> list ;

    public VehicleAdapter(Context context, ArrayList<VehicleResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VehicleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vehicle_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.ViewHolder holder, int position) {

        VehicleResponse response = list.get(position);
        holder.vehicleNumber.setText(response.getVehicleNumber());
        holder.running.setText(response.getRunning());
        holder.rideComplete.setText(response.getRideComplete());
        holder.vehicleType.setText(response.getVehicleType());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView vehicleNumber,running,rideComplete,vehicleType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.vehicle_image);
            vehicleNumber = itemView.findViewById(R.id.txt_vehicle_number);
            running = itemView.findViewById(R.id.txt_running);
            rideComplete = itemView.findViewById(R.id.txt_rideComplete);
            vehicleType = itemView.findViewById(R.id.txt_vehicleType);
        }
    }
}
