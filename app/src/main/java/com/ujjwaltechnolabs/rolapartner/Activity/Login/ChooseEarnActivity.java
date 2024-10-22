package com.ujjwaltechnolabs.rolapartner.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityChooseEarnBinding;

public class ChooseEarnActivity extends AppCompatActivity {
    ActivityChooseEarnBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseEarnBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Click Event
        setOnClickListener();
    }

    private void setOnClickListener() {
            binding.btnNext.setOnClickListener(v -> {
                startActivity(new Intent(ChooseEarnActivity.this, LanguageActivity.class));
            });
    }
}