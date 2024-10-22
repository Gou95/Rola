package com.ujjwaltechnolabs.rolapartner.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityLanguageBinding;

public class LanguageActivity extends AppCompatActivity {
    ActivityLanguageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Click Event
        setOnClickListener();
    }

    private void setOnClickListener() {
        binding.btnContinue.setOnClickListener(v -> {
            startActivity(new Intent(LanguageActivity.this, ThanksActivity.class));
        });
    }
}