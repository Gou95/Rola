package com.ujjwaltechnolabs.rolapartner.Fragment;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ujjwaltechnolabs.rolapartner.Activity.AddVehicleActivity;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.FragmentTripBinding;


public class TripFragment extends Fragment {



    FragmentTripBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentTripBinding.inflate(inflater, container, false);

        binding.txtMap.setPaintFlags(binding.txtMap.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        initClicks();


        return binding.getRoot();
    }

    private void initClicks() {

        binding.edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtName.setVisibility(View.GONE);
                } else {
                    binding.txtName.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        binding.edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtPhoneNumber.setVisibility(View.GONE);
                } else {
                    binding.txtPhoneNumber.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.edtDriverName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtDriverName.setVisibility(View.GONE);
                } else {
                    binding.txtDriverName.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        binding.edtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtComment.setVisibility(View.GONE);
                } else {
                    binding.txtComment.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        binding.edtPickUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtPickUpLocation.setVisibility(View.GONE);
                } else {
                    binding.txtPickUpLocation.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        binding.edtDropUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtDropUpLocation.setVisibility(View.GONE);
                } else {
                    binding.txtDropUpLocation.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        binding.spnVehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { // Assuming the first item is a prompt like "Select Vehicle"
                    binding.txtSelectVehicleType.setVisibility(View.GONE);
                } else {
                    binding.txtSelectVehicleType.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                binding.txtSelectVehicleType.setVisibility(View.VISIBLE);
            }
        });
        binding.spnBookingType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { // Assuming the first item is a prompt like "Select Vehicle"
                    binding.txtSelectBookingType.setVisibility(View.GONE);
                } else {
                    binding.txtSelectBookingType.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                binding.txtSelectBookingType.setVisibility(View.VISIBLE);
            }
        });

        binding.btnSave.setOnClickListener(view -> {
          validateInputs();
        });
    }
    private void validateInputs() {
        // Reset visibility of error messages before validation
        binding.txtName.setVisibility(View.GONE);
        binding.txtPhoneNumber.setVisibility(View.GONE);
        binding.txtDriverName.setVisibility(View.GONE);
        binding.txtComment.setVisibility(View.GONE);
        binding.txtPickUpLocation.setVisibility(View.GONE);
        binding.txtDropUpLocation.setVisibility(View.GONE);
        binding.txtSelectVehicleType.setVisibility(View.GONE);
        binding.txtSelectBookingType.setVisibility(View.GONE);


        String vehicleType = binding.spnVehicleType.getSelectedItem() != null ? binding.spnVehicleType.getSelectedItem().toString() : "";
        String bookingType = binding.spnBookingType.getSelectedItem() != null ? binding.spnBookingType.getSelectedItem().toString() : "";
        String name = binding.edtName.getText().toString().trim();
        String phoneNumber = binding.edtNumber.getText().toString().trim();
        String comment = binding.edtComment.getText().toString().trim();
        String pickup = binding.edtPickUp.getText().toString().trim();
        String dropup = binding.edtDropUp.getText().toString().trim();
        String driverName = binding.edtDropUp.getText().toString().trim();

        boolean isValid = true; // Flag to check overall validity

        if (driverName.isEmpty()) {
            binding.txtDriverName.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if (vehicleType.isEmpty()) {

        }
        if (bookingType.isEmpty()) {

        }
        if (name.isEmpty()) {
            binding.txtName.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if (phoneNumber.isEmpty()) {
            binding.txtPhoneNumber.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if (comment.isEmpty()) {
            binding.txtComment.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if (pickup.isEmpty()) {
            binding.txtPickUpLocation.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if (dropup.isEmpty()) {
            binding.txtDropUpLocation.setVisibility(View.VISIBLE);
            isValid = false;
        }

        // If all inputs are valid, proceed with saving the data
        if (isValid) {
            showToast("Vehicle details saved successfully!");
        }
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

}