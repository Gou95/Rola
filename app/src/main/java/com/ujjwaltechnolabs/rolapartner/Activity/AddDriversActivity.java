package com.ujjwaltechnolabs.rolapartner.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityAddDriversBinding;

public class AddDriversActivity extends AppCompatActivity {

    ActivityAddDriversBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDriversBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initClicks();


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

        binding.edtPhoneNumber.addTextChangedListener(new TextWatcher() {
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


        binding.edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtEmail.setVisibility(View.GONE);
                } else {
                    binding.txtEmail.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.edtPhoto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtProfilePic.setVisibility(View.GONE);
                } else {
                    binding.txtProfilePic.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        binding.edtDrivingDL.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtDrivingDl.setVisibility(View.GONE);
                } else {
                    binding.txtDrivingDl.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });



        binding.imgBtnArrowBack.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.btnSave.setOnClickListener(view -> {
            validateInputs();
        });
    }
    private void validateInputs() {
        // Reset visibility of error messages before validation
        binding.txtName.setVisibility(View.GONE);
        binding.txtPhoneNumber.setVisibility(View.GONE);
        binding.txtEmail.setVisibility(View.GONE);
        binding.txtProfilePic.setVisibility(View.GONE);
        binding.txtDrivingDl.setVisibility(View.GONE);



        String name = binding.edtName.getText().toString().trim();
        String phoneNumber = binding.edtPhoneNumber.getText().toString().trim();
        String email = binding.edtEmail.getText().toString().trim();
        String profile = binding.edtPhoto.getText().toString().trim();
        String dl = binding.edtDrivingDL.getText().toString().trim();

        boolean isValid = true; // Flag to check overall validity

        if (name.isEmpty()) {
            binding.txtName.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if (phoneNumber.isEmpty()) {
            binding.txtPhoneNumber.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if (email.isEmpty()) {
            binding.txtEmail.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if (profile.isEmpty()) {
            binding.txtProfilePic.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if (dl.isEmpty()) {
            binding.txtDrivingDl.setVisibility(View.VISIBLE);
            isValid = false;
        }


        // If all inputs are valid, proceed with saving the data
        if (isValid) {
            showToast("Vehicle details saved successfully!");
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}