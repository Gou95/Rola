package com.ujjwaltechnolabs.rolapartner.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.Utils.ApplicationUtils;
import com.ujjwaltechnolabs.rolapartner.Utils.UploadDocumentActivity;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityDocVerificationBinding;

public class DocVerificationActivity extends AppCompatActivity {
    ActivityDocVerificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Click Event
        setOnClickListener();

    }

    private void setOnClickListener() {
        binding.btnProfile.setOnClickListener(v -> {
            startUploadDocumentActivity(ApplicationUtils.PROFILE);
        });
        binding.btnDrivingLicense.setOnClickListener(v -> {
            startUploadDocumentActivity(ApplicationUtils.LICENSE);
        });
        binding.btnPanCard.setOnClickListener(v -> {
            startUploadDocumentActivity(ApplicationUtils.PAN_CARD);
        });
        binding.btnRC.setOnClickListener(v -> {
            startUploadDocumentActivity(ApplicationUtils.VEHICLE_REGISTRATION);
        });
        binding.btnVehicleInsurance.setOnClickListener(v -> {
            startUploadDocumentActivity(ApplicationUtils.VEHICLE_INSURANCE);
        });
           binding.btnVehiclePermit.setOnClickListener(v -> {
            startUploadDocumentActivity(ApplicationUtils.VEHICLE_PERMIT);
        });

    }
    private void startUploadDocumentActivity(String documentType) {
        Intent intent = new Intent(DocVerificationActivity.this, UploadDocumentActivity.class);
        intent.putExtra("document", documentType);
        startActivity(intent);
    }
}