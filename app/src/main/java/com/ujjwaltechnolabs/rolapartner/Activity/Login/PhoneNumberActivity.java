package com.ujjwaltechnolabs.rolapartner.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Body.LoginBody;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Model.LoginModel;
import com.ujjwaltechnolabs.rolapartner.MVVM.ViewModel.LoginViewModel;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.Utils.PhoneNumberValidator;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityPhoneNumberBinding;

public class PhoneNumberActivity extends AppCompatActivity {
    ActivityPhoneNumberBinding binding;
    String countryCode, phoneNumber, fullNumber, countryNameCode;
    LoginViewModel loginViewModel;
    LoginBody loginBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        //Click Event
        setOnClickListener();
        //validation on edittext
        edittexttextchange();
        //Observer
        observer();
    }
    private void observer() {

        loginViewModel.loginObserver().observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {
                binding.btnSend.setVisibility(View.VISIBLE);
                binding.txtLoading.setVisibility(View.INVISIBLE);
                binding.btnSend.setText(R.string.send);
                binding.btnSend.setClickable(true);
                if (loginModel != null) {
                    Toast.makeText(PhoneNumberActivity.this, R.string.otp_send_successfully, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), VerificationActivity.class);
                    intent.putExtra("number", fullNumber);
                    startActivity(intent);
                }
            }
        });


    }
    private void setOnClickListener() {
        binding.btnSend.setOnClickListener(v -> {
            countryNameCode = binding.countryCode.getSelectedCountryNameCode();
            countryCode = "+" + binding.countryCode.getSelectedCountryCode();
            phoneNumber = binding.txtNumber.getText().toString().trim();
            fullNumber = countryCode + phoneNumber;
            Log.e("countryCode", countryCode + phoneNumber);
            if (PhoneNumberValidator.isValidPhoneNumber(phoneNumber, countryNameCode)) {
                // Valid phone number
                binding.txtErrorMessage.setText("");
                Log.e("Numasdasdber", countryCode);

                binding.btnSend.setVisibility(View.INVISIBLE);
                binding.txtLoading.setVisibility(View.VISIBLE);
                loginBody = new LoginBody(phoneNumber, countryCode);
                loginViewModel.login(PhoneNumberActivity.this,loginBody);

            } else {
                // Invalid phone number
                binding.txtErrorMessage.setText("Enter Correct Number");
            }
        });
    }


    private void phonenumbervalidater() {

        String countryCode1, phoneNumber1;
        countryCode1 = binding.countryCode.getSelectedCountryNameCode();
        phoneNumber1 = binding.txtNumber.getText().toString().trim();
        if (PhoneNumberValidator.isValidPhoneNumber(phoneNumber1, countryCode1)) {
            // Valid phone number
            binding.txtErrorMessage.setText("");
        } else {
            // Invalid phone number
            binding.txtErrorMessage.setText("Enter Correct Number");
        }

    }

    private void edittexttextchange() {
        binding.txtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                phonenumbervalidater();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phonenumbervalidater();
            }

            @Override
            public void afterTextChanged(Editable s) {
                phonenumbervalidater();
            }
        });
    }

}