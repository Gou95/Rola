package com.ujjwaltechnolabs.rolapartner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ujjwaltechnolabs.rolapartner.Model.DriverResponse;
import com.ujjwaltechnolabs.rolapartner.R;

import java.util.ArrayList;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> {

    Context context;
    ArrayList<DriverResponse> list ;

    public DriverAdapter(Context context, ArrayList<DriverResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DriverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.driver_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverAdapter.ViewHolder holder, int position) {
        DriverResponse response = list.get(position);

        holder.contactInformation.setText(response.getContactInformation());
        holder.residentialAddress.setText(response.getResidentialAddress());
        holder.employeeStatus.setText(response.getEmployeeStatus());
        holder.completerides.setText(response.getCompleterides());
        holder.currentVehicle.setText(response.getCurrentVehicle());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView contactInformation,residentialAddress,employeeStatus,completerides,currentVehicle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contactInformation = itemView.findViewById(R.id.txt_contactInformation);
            residentialAddress = itemView.findViewById(R.id.txt_residentialAddress);
            employeeStatus = itemView.findViewById(R.id.txt_employeestatus);
            completerides = itemView.findViewById(R.id.txt_completeRides);
            currentVehicle = itemView.findViewById(R.id.txt_currentVehicle);
        }
    }
}
