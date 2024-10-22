package com.ujjwaltechnolabs.rolapartner.Activity.Login;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Body.VerificationBody;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Model.VerificationModel;
import com.ujjwaltechnolabs.rolapartner.MVVM.ViewModel.LoginViewModel;
import com.ujjwaltechnolabs.rolapartner.MainActivity;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityVerificationBinding;

import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificationActivity extends AppCompatActivity {
    ActivityVerificationBinding binding;
    LoginViewModel loginViewModel;
    VerificationBody verificationBody;
    String number;
    Locale locale;
    String currencyCode, country_code, languageCode;
    private FusedLocationProviderClient fusedLocationProviderClient;
    double latitude, longitude;
    String fcmToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        number = getIntent().getStringExtra("number");
        //token
      //  getToken();
        //for Location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLastLocation();
        } else {
            Toast.makeText(this, "Location permission is denied.", Toast.LENGTH_SHORT).show();
        }
        //For Language
        locale = Locale.getDefault();
        languageCode = locale.getLanguage();
        Log.e("launage", languageCode);
        Log.e("latitude", String.valueOf(latitude));
        Log.e("longitude", String.valueOf(longitude));
        //language and currency
        languageAndCurrency();
        //Click Event
        setOnClickListener();
        //Next Otp Input Automatic
        moveNumberToNext();
        //Observer
        observer();
    }

    private void observer() {
        loginViewModel.verificationObserver().observe(this, new Observer<VerificationModel>() {
            @Override
            public void onChanged(VerificationModel verificationModel) {
                if (verificationModel != null) {
                        startActivity(new Intent(VerificationActivity.this, MainActivity.class));
                        finish();

                }
            }
        });
    }

    private void setOnClickListener() {
        binding.imgBtnArrowBack.setOnClickListener(v -> {
            finish();
        });
        binding.btnVerify.setOnClickListener(v -> {
            String otp = binding.inputotp1.getText().toString() + binding.inputotp2.getText().toString() + binding.inputotp3.getText().toString() + binding.inputotp4.getText().toString();
            if (!otp.isEmpty()) {


                VerificationBody.Location location = new VerificationBody.Location();
                location.setType("Point");
                location.setCoordinates(Arrays.asList(latitude, longitude)); // Replace with actual source if dynamic
                verificationBody = new VerificationBody(number, otp, languageCode, fcmToken, currencyCode, location);
                loginViewModel.verification(VerificationActivity.this,verificationBody);
            } else {
                Toast.makeText(this, "Enter Your OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "getInstanceId failed", task.getException());
                    return;
                }
                // Get the Instance ID token
                fcmToken = task.getResult();
                // Log the token (or save it, send it to your server, etc.)
                Log.d(TAG, "FCM Token: " + fcmToken);
            }
        });

    }
    private void languageAndCurrency() {
        final TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        final String simCountry = tm.getSimCountryIso();
        if (simCountry != null && simCountry.length() == 2) {
            country_code = simCountry.toLowerCase(Locale.US);
            currencyCode = Currency.getInstance(new Locale("", country_code)).getCurrencyCode();
            Log.d("CurrencyCode", currencyCode);
            Log.d("CountryCode", country_code);
        }
    }
    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Location location = task.getResult();
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            Log.d("Location", "Latitude: " + latitude + ", Longitude: " + longitude);
                        } else {
                            requestNewLocation();
                        }
                    }
                });
    }

    private void requestNewLocation() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds
        locationRequest.setFastestInterval(5 * 1000); // 5 seconds

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    Location location = locationResult.getLastLocation();
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    Log.d("Location", "Latitude: " + latitude + ", Longitude: " + longitude);
                    fusedLocationProviderClient.removeLocationUpdates(this);
                }
            }, Looper.getMainLooper());
        } else {
            Toast.makeText(this, "Location permission is denied.", Toast.LENGTH_SHORT).show();
        }
    }



    private void startSmsRetriever() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        client.startSmsRetriever().addOnSuccessListener(aVoid -> {
            // Successfully started retriever
            IntentFilter filter = new IntentFilter();
            filter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
            registerReceiver(otpReceiver, filter);
        }).addOnFailureListener(e -> {
            // Failed to start retriever
            Log.e("MainActivity", "Error starting SMS retriever", e);
        });
    }
    private final BroadcastReceiver otpReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
                Bundle extras = intent.getExtras();
                Status status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);
                switch (status.getStatusCode()) {
                    case CommonStatusCodes.SUCCESS:
                        String message = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                        extractAndSetOtp(message);
                        break;
                    case CommonStatusCodes.TIMEOUT:
                        Log.e("MainActivity", "SMS retrieval timed out");
                        break;
                }
            }
        }
    };
    private void extractAndSetOtp(String message) {
        Pattern pattern = Pattern.compile("(\\d{4})"); // Extracts a 4-digit number
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            String otp = matcher.group(1);
            Log.e("ooooottttpp", otp);
            binding.inputotp1.setText(String.valueOf(otp.charAt(0)));
            binding.inputotp2.setText(String.valueOf(otp.charAt(1)));
            binding.inputotp3.setText(String.valueOf(otp.charAt(2)));
            binding.inputotp4.setText(String.valueOf(otp.charAt(3)));

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(otpReceiver);
    }
    private void moveNumberToNext() {
        binding.inputotp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.inputotp2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.inputotp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.inputotp3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.inputotp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.inputotp4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.inputotp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.btnVerify.callOnClick();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}