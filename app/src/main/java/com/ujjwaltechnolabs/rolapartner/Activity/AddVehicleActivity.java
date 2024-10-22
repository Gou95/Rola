package com.ujjwaltechnolabs.rolapartner.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Model.LoginModel;
import com.ujjwaltechnolabs.rolapartner.MVVM.ViewModel.LoginViewModel;
import com.ujjwaltechnolabs.rolapartner.Model.SelectFuelTypeResponse;
import com.ujjwaltechnolabs.rolapartner.Model.SelectVehicleResponse;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityAddVehicleBinding;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class AddVehicleActivity extends AppCompatActivity {

    ActivityAddVehicleBinding binding;
    LoginViewModel loginViewModel;

    private static int REQUEST_CODE_CERTIFICATE = 100;
    private static int REQUEST_CODE_INSURANCE = 101;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 200;


    SelectVehicleResponse.Data.VehicleType response;
    ArrayList<SelectVehicleResponse.Data.VehicleType> list = new ArrayList<>();
    ArrayList<SelectFuelTypeResponse> fuelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddVehicleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.imgBtnArrowBack.setOnClickListener(v -> {
            onBackPressed();
        });

        observers();

        loginViewModel.selectVehicle(AddVehicleActivity.this);
        loginViewModel.selectFuelType(AddVehicleActivity.this);

        initClicks();



    }

    private void initClicks() {
//        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position > 0) { // Assuming the first item is a prompt like "Select Vehicle"
//                    binding.txtSelectVehicleName.setVisibility(View.GONE);
//                } else {
//                    binding.txtSelectVehicleName.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                binding.txtSelectVehicleName.setVisibility(View.VISIBLE);
//            }
//        });
//        binding.spinnerFueltype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position > 0) { // Assuming the first item is a prompt like "Select Vehicle"
//                    binding.txtSelectVehicleName.setVisibility(View.GONE);
//                } else {
//                    binding.txtSelectVehicleName.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                binding.txtSelectVehicleName.setVisibility(View.VISIBLE);
//            }
//        });

        binding.edtCarName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtCarName.setVisibility(View.GONE);
                } else {
                    binding.txtCarName.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.edtVehicleNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtVehicleNumber.setVisibility(View.GONE);
                } else {
                    binding.txtVehicleNumber.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.edtInsurance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtInsurancePic.setVisibility(View.GONE);
                } else {
                    binding.txtInsurancePic.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.edtCertificate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.txtRc.setVisibility(View.GONE);
                } else {
                    binding.txtRc.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });


        binding.txtUpload.setOnClickListener(v -> {
            checkCameraPermissionAndOpenCamera(REQUEST_CODE_CERTIFICATE);
        });

        binding.cardTrue.setOnClickListener(v -> {
            checkCameraPermissionAndOpenCamera(REQUEST_CODE_INSURANCE);
        });

        binding.btnSave.setOnClickListener(view -> validateInputs());
    }


    private void checkCameraPermissionAndOpenCamera(int requestCode) {
        if (ContextCompat.checkSelfPermission(AddVehicleActivity.this, android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, open the camera
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, requestCode);
        } else {
            // Request permission
            ActivityCompat.requestPermissions(AddVehicleActivity.this,
                    new String[]{android.Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        }
    }
    private void observers() {
        loginViewModel.selectVehicleObserver().observe(this, new Observer<SelectVehicleResponse>() {
            @Override
            public void onChanged(SelectVehicleResponse selectVehicleResponse) {
                if (selectVehicleResponse != null && selectVehicleResponse.getData() != null) {
                    list.clear(); // Clear previous data
                    list.addAll(selectVehicleResponse.getData().getVehicleTypes()); // Add new data
                    setupSpinner(list); // Update UI
                } else {
                    Toast.makeText(AddVehicleActivity.this, "No vehicle types available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginViewModel.selectFueltypeObserver().observe(this, new Observer<List<SelectFuelTypeResponse>>() {
            @Override
            public void onChanged(List<SelectFuelTypeResponse> selectFuelTypeResponses) {
                if (selectFuelTypeResponses != null) {
                    fuelList.clear();
                    fuelList.addAll(selectFuelTypeResponses); // Add all fuel types to the list
                    setupFuelSpinner(fuelList);
                } else {
                    Toast.makeText(AddVehicleActivity.this, "No fuel types available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setupFuelSpinner(ArrayList<SelectFuelTypeResponse> fuelTypes) {
        List<String> fuelTypeNames = new ArrayList<>();

        for (SelectFuelTypeResponse fuelType : fuelTypes) {
            fuelTypeNames.add(fuelType.getType());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fuelTypeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerFueltype.setAdapter(adapter);

        binding.spinnerFueltype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SelectFuelTypeResponse response1 = fuelTypes.get(position);
                int fuelTypeId = response1.getId();
                Log.d("TAG", "onItemSelected: fuelId  "+fuelTypeId);

                String selectedFuelType = fuelTypeNames.get(position);

                Toast.makeText(AddVehicleActivity.this, selectedFuelType + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optionally handle this case
            }
        });
    }

    private void setupSpinner(ArrayList<SelectVehicleResponse.Data.VehicleType> vehicleTypes) {
        List<String> vehicleNames = new ArrayList<>();


        for (SelectVehicleResponse.Data.VehicleType vehicleType : vehicleTypes) {
            vehicleNames.add(vehicleType.getName());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vehicleNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the Spinner
        binding.spinner.setAdapter(adapter);

        // Optionally set an item selected listener
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Get the selected vehicle name

                // Find the corresponding VehicleType from vehicleTypes based on the position
                SelectVehicleResponse.Data.VehicleType selectedVehicleType = vehicleTypes.get(position);

                // Get the ID or any other property from the selected VehicleType
                int vehicleId = selectedVehicleType.getId();
                Log.d("TAG", "onItemSelected: Vehicle ID - " + vehicleId);
                String selectedVehicle = vehicleNames.get(position);
                String selectedId = vehicleNames.get((int) id);
                Toast.makeText(AddVehicleActivity.this, selectedId + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where no item is selected if necessary
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CERTIFICATE);
            } else {
                // Permission denied, inform the user
                Toast.makeText(this, "Camera permission is required to take a photo", Toast.LENGTH_SHORT).show();
            }
        }
}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap != null) {
                Uri imageUri = saveImageToFile(bitmap, requestCode);
                if (requestCode == REQUEST_CODE_CERTIFICATE) {
                    binding.edtCertificate.setText(imageUri.toString());
                } else if (requestCode == REQUEST_CODE_INSURANCE) {
                    binding.edtInsurance.setText(imageUri.toString());
                }
            }
        } else {
            showToast("Image capture cancelled");
        }
    }

    private Uri saveImageToFile(Bitmap bitmap, int requestCode) {
        File file = new File(getCacheDir(), requestCode == REQUEST_CODE_CERTIFICATE ? "certificate_image.jpg" : "insurance_image.jpg");
        try (FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            return Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Error saving image");
            return null;
        }
    }

    //    private void startCrop(Uri sourceUri, Uri destinationUri) {
//        UCrop.of(sourceUri, destinationUri)
//                .withAspectRatio(16, 9)  // Set your desired aspect ratio
//                .withMaxResultSize(1000, 1000)  // Maximum resolution for cropped image
//                .start(this);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK && data != null) {
//            // Extract the captured bitmap from the camera intent
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            if (bitmap != null) {
//                // Save the image to temporary file
//                File file = new File(getCacheDir(), "temp_image.jpg");
//                try (FileOutputStream out = new FileOutputStream(file)) {
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//                    Uri imageUri = Uri.fromFile(file);
//
//                    // Start cropping using UCrop
//                    startCrop(imageUri, Uri.fromFile(new File(getCacheDir(), "cropped_image.jpg")));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Toast.makeText(this, "Error saving image", Toast.LENGTH_SHORT).show();
//                }
//            }
//        } else if (requestCode == UCrop.REQUEST_CROP) {
//
//            Uri resultUri = UCrop.getOutput(data);
//            if (resultUri != null) {
//
//                // Now insert the cropped image into the EditText using an ImageSpan
//                insertImageIntoEditText(binding.edtCertificate, resultUri);
//                insertImageIntoEditText(binding.edtInsurance, resultUri);
//            }
//        } else if (resultCode == UCrop.RESULT_ERROR) {
//            Throwable cropError = UCrop.getError(data);
//            if (cropError != null) {
//                cropError.printStackTrace();
//                Toast.makeText(this, "Image cropping failed", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void insertImageIntoEditText(EditText editText, Uri imageUri) {
//        try {
//            // Convert the Uri to a Bitmap
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//
//            // Create an ImageSpan from the Bitmap
//            ImageSpan imageSpan = new ImageSpan(this, bitmap);
//
//            // Create a SpannableString and insert the ImageSpan
//            SpannableString spannableString = new SpannableString(" "); // Add a space (or any text you want)
//            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//            // Append the ImageSpan to the EditText
//            int start = editText.getSelectionStart();
//            if (start < 0) start = 0;
//            editText.getText().insert(start, spannableString);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Failed to insert image", Toast.LENGTH_SHORT).show();
//        }
//    }
    private void validateInputs() {
        // Reset visibility of error messages before validation
        binding.txtSelectVehicleName.setVisibility(View.GONE);
        binding.txtSelectFuelType.setVisibility(View.GONE);
        binding.txtCarName.setVisibility(View.GONE);
        binding.txtVehicleNumber.setVisibility(View.GONE);
        binding.txtInsurancePic.setVisibility(View.GONE);
        binding.txtRc.setVisibility(View.GONE);

        String vehicleName = binding.spinner.getSelectedItem() != null ? binding.spinner.getSelectedItem().toString() : "";
        String fuelType = binding.spinnerFueltype.getSelectedItem() != null ? binding.spinnerFueltype.getSelectedItem().toString() : "";
        String carName = binding.edtCarName.getText().toString().trim();
        String vehicleNumber = binding.edtVehicleNumber.getText().toString().trim();
        String insurance = binding.edtInsurance.getText().toString().trim();
        String certificate = binding.edtCertificate.getText().toString().trim();

        boolean isValid = true; // Flag to check overall validity

        if (vehicleName.isEmpty()) {

        }
        if (fuelType.isEmpty()) {

        }
        if (carName.isEmpty()) {
            binding.txtCarName.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if (vehicleNumber.isEmpty()) {
            binding.txtVehicleNumber.setVisibility(View.VISIBLE);
            isValid = false;
        } else if (!isValidVehicleNumber(vehicleNumber)) {
            Toast.makeText(AddVehicleActivity.this, "Invalid vehicle number format. Expected format: MP-01 ABCD", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if (insurance.isEmpty()) {
            binding.txtInsurancePic.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if (certificate.isEmpty()) {
            binding.txtRc.setVisibility(View.VISIBLE);
            isValid = false;
        }

        // If all inputs are valid, proceed with saving the data
        if (isValid) {
            showToast("Vehicle details saved successfully!");
        }
    }

    private boolean isValidVehicleNumber(String vehicleNumber) {
        String regex = "^[A-Z]{2}-\\d{2} [A-Z]{4}$"; // Regex for format XX-99 ABCD
        return vehicleNumber.matches(regex);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}