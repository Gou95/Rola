package com.ujjwaltechnolabs.rolapartner.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityLoginEarnBinding;

public class LoginEarnActivity extends AppCompatActivity {
    ActivityLoginEarnBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginEarnBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Click Event
        setOnClickListener();

    }

    private void setOnClickListener() {
        binding.btnNext.setOnClickListener(v -> {
            if (!binding.txtCityName.getText().toString().isEmpty()){
                startActivity(new Intent(LoginEarnActivity.this, ChooseEarnActivity.class));
            }
            else {
                Toast.makeText(this, "Please select your location", Toast.LENGTH_SHORT).show();
            }
        });
    }
}