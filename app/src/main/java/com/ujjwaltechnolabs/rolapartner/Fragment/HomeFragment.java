package com.ujjwaltechnolabs.rolapartner.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.ujjwaltechnolabs.rolapartner.Activity.AddDriversActivity;
import com.ujjwaltechnolabs.rolapartner.Activity.AddVehicleActivity;
import com.ujjwaltechnolabs.rolapartner.Activity.NotificationActivity;
import com.ujjwaltechnolabs.rolapartner.Activity.ProfileActivity;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentHomeBinding.inflate(inflater, container, false);

        binding.imgBtnUserprofile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            startActivity(intent);
        });

        binding.imgBtnNotification.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NotificationActivity.class);
            startActivity(intent);
        });

        binding.imgBtnAdd.setOnClickListener(v -> {
            showPopup();
        });

        return binding.getRoot();
    }

    @SuppressLint("MissingInflatedId")
    private void showPopup() {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View popupView = inflater.inflate(R.layout.popup_layout, null);
        AlertDialog.Builder popup = new AlertDialog.Builder(getActivity());
        popup.setView(popupView);
        AlertDialog dialog = popup.create();
        dialog.show();
        View linearAddDriver = popupView.findViewById(R.id.linear_addDriver);
        View linearAddVehicle = popupView.findViewById(R.id.linear_addVehicle);
       MaterialButton cancelButton = popupView.findViewById(R.id.btn_cancel);

       linearAddDriver.setOnClickListener(v -> {
           Intent intent = new Intent(getActivity(), AddDriversActivity.class);
           startActivity(intent);
           dialog.dismiss();

       });

       linearAddVehicle.setOnClickListener(v -> {
           Intent intent = new Intent(getActivity(), AddVehicleActivity.class);
           startActivity(intent);
           dialog.dismiss();
       });

        cancelButton.setOnClickListener(v -> dialog.dismiss());


    }
}