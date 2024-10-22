package com.ujjwaltechnolabs.rolapartner.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ujjwaltechnolabs.rolapartner.Adapter.DriverAdapter;
import com.ujjwaltechnolabs.rolapartner.Model.DriverResponse;
import com.ujjwaltechnolabs.rolapartner.Model.VehicleResponse;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.FragmentDriverBinding;

import java.util.ArrayList;


public class DriverFragment extends Fragment {


    FragmentDriverBinding binding;

    ArrayList<DriverResponse> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentDriverBinding.inflate(inflater, container, false);

        list.add(new DriverResponse("123","asd","yes","dfsd","hhkh"));
        list.add(new DriverResponse("1234","df","ds","suv","dfdf"));
        list.add(new DriverResponse("12345","fd","yesdf","sduv","hhkbxh"));
        list.add(new DriverResponse("123456","ret","ydsfes","ffdf","cbxcv"));
        list.add(new DriverResponse("123333","ret","dsfds","fdfd","cxvcv"));
        list.add(new DriverResponse("123444","asgdfgd","yedffs","suvdfd","cvvcx"));



        DriverAdapter adapter = new DriverAdapter(getContext(),list);
        binding.driverRecyclerView.setAdapter(adapter);
        binding.driverRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return binding.getRoot();
    }
}