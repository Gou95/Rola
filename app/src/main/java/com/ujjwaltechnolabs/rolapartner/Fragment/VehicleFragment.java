package com.ujjwaltechnolabs.rolapartner.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ujjwaltechnolabs.rolapartner.Adapter.VehicleAdapter;
import com.ujjwaltechnolabs.rolapartner.Model.VehicleResponse;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.FragmentVehicleBinding;

import java.util.ArrayList;

public class VehicleFragment extends Fragment {


    FragmentVehicleBinding binding;
    ArrayList<VehicleResponse> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentVehicleBinding.inflate(inflater, container, false);

        list.add(new VehicleResponse("123","asd","yes","suv"));
        list.add(new VehicleResponse("1234","asdfsd","yes","sadan"));
        list.add(new VehicleResponse("12345","asfsdfd","yes","suv"));
        list.add(new VehicleResponse("123456","asghd","yes","suv"));
        list.add(new VehicleResponse("12443543","aghsd","yes","suv"));
        list.add(new VehicleResponse("43534","asdhg","yes","sadan"));

        VehicleAdapter adapter = new VehicleAdapter(getActivity(),list);
        binding.vehicleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.vehicleRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
}