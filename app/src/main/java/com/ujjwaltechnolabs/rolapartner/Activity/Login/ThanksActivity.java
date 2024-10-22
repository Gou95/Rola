package com.ujjwaltechnolabs.rolapartner.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityThanksBinding;

public class ThanksActivity extends AppCompatActivity {
    ActivityThanksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThanksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Click Event
        setOnClickListener();
    }

    private void setOnClickListener() {
        binding.btnGoToNextStep.setOnClickListener(v -> {
            startActivity(new Intent(ThanksActivity.this, DocVerificationActivity.class));

        });
        binding.btnGoToAccountSetUp.setOnClickListener(v -> {
            Toast.makeText(this, "Button Click Account SetUp", Toast.LENGTH_SHORT).show();
        });
    }
}