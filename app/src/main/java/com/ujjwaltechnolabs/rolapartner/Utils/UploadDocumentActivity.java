package com.ujjwaltechnolabs.rolapartner.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ujjwaltechnolabs.rolapartner.Activity.Camera.CameraActivity;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityUploadDocumentBinding;

public class UploadDocumentActivity extends AppCompatActivity {
    ActivityUploadDocumentBinding binding;
    String documentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadDocumentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        documentType = getIntent().getStringExtra("document");
        Log.e("dsdfhoiuahsd", documentType);
        //Click invents
        setOnclickListener();
    }

    private void setOnclickListener() {
        binding.btnTakePhoto.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
            intent.putExtra("document", documentType);
            startActivity(intent);
            finish();
        });
        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}