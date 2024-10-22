package com.ujjwaltechnolabs.rolapartner.Activity.Camera;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;
//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;
import com.ujjwaltechnolabs.rolapartner.Activity.Login.DocVerificationActivity;
import com.ujjwaltechnolabs.rolapartner.MVVM.ViewModel.Login.DocumentModel;
import com.ujjwaltechnolabs.rolapartner.MVVM.ViewModel.LoginViewModel;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.SharedPref.SharedPrefManager;
import com.ujjwaltechnolabs.rolapartner.Utils.ApplicationUtils;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityCameraBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CameraActivity extends AppCompatActivity {
    ActivityCameraBinding binding;
    //for camera
    int cameraFacing = CameraSelector.LENS_FACING_BACK;
    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result) {
                startCamera(cameraFacing);
            }
        }
    });
    //for gallery
    private static final int REQUEST_IMAGE_PICK = 1;
    private static final int REQUEST_PERMISSION_READ_EXTERNAL_STORAGE = 2;
    private Bitmap selectedImageBitmap;
    //for cropping
    Uri croppedImageUri;
    LoginViewModel viewModel;
    int driverId;
    File document;
    String documentType;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCameraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        progressDialog = new ProgressDialog(CameraActivity.this);
        driverId = SharedPrefManager.getInstance(CameraActivity.this).getInt("id", 0);
        documentType = getIntent().getStringExtra("document");
        Log.e("DocumentType", documentType);
        // Set the camera facing based on document type
        if (ApplicationUtils.PROFILE.equals(documentType)) {
            cameraFacing = CameraSelector.LENS_FACING_FRONT;
            binding.linearLayout.setVisibility(View.GONE);
            binding.txtCut.setVisibility(View.GONE);

        } else {
            cameraFacing = CameraSelector.LENS_FACING_BACK;
        }
        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            activityResultLauncher.launch(Manifest.permission.CAMERA);
        } else {
            startCamera(cameraFacing);
        }
        //Click invents
        setOnclickListener();

        viewModel.documentObserver().observe(this, new Observer<DocumentModel>() {
            @Override
            public void onChanged(DocumentModel documentModel) {
                progressDialog.dismiss();
                if (documentModel != null) {
                    Intent intent;
                    if (Objects.equals(documentModel.getDocumentType(), ApplicationUtils.LICENSE)) {
                        intent = new Intent(getApplicationContext(), DocVerificationActivity.class);
                        intent.putExtra("document", documentModel.getDocumentType());
                        Log.e("documentType", documentModel.getDocumentType());
                        startActivity(intent);
                        finish();
                    }
                    if (Objects.equals(documentModel.getDocumentType(), ApplicationUtils.PROFILE)) {
                        intent = new Intent(getApplicationContext(), DocVerificationActivity.class);
                        intent.putExtra("document", documentModel.getDocumentType());
                        Log.e("documentType", documentModel.getDocumentType());
                        startActivity(intent);
                        finish();
                    }
                    if (Objects.equals(documentModel.getDocumentType(), ApplicationUtils.PAN_CARD)) {
                        intent = new Intent(getApplicationContext(), DocVerificationActivity.class);
                        intent.putExtra("document", documentModel.getDocumentType());
                        Log.e("documentType", documentModel.getDocumentType());
                        startActivity(intent);
                        finish();
                    }
                    if (Objects.equals(documentModel.getDocumentType(), ApplicationUtils.VEHICLE_REGISTRATION)) {
                        intent = new Intent(getApplicationContext(), DocVerificationActivity.class);
                        intent.putExtra("document", documentModel.getDocumentType());
                        Log.e("documentType", documentModel.getDocumentType());
                        startActivity(intent);
                        finish();
                    }
                    if (Objects.equals(documentModel.getDocumentType(), ApplicationUtils.VEHICLE_INSURANCE)) {
                        intent = new Intent(getApplicationContext(), DocVerificationActivity.class);
                        intent.putExtra("document", documentModel.getDocumentType());
                        Log.e("documentType", documentModel.getDocumentType());
                        startActivity(intent);
                        finish();
                    }    if (Objects.equals(documentModel.getDocumentType(), ApplicationUtils.VEHICLE_PERMIT)) {
                        intent = new Intent(getApplicationContext(), DocVerificationActivity.class);
                        intent.putExtra("document", documentModel.getDocumentType());
                        Log.e("documentType", documentModel.getDocumentType());
                        startActivity(intent);
                        finish();
                    }

                }
            }
        });

    }

    private void setOnclickListener() {
        binding.btnGallery.setOnClickListener(v -> {
            //for open gallery
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestRuntimePermissionFunc("manageStorage");
            } else {
                requestRuntimePermissionFunc("storage");
            }

        });
        binding.btnCancel.setOnClickListener(v -> {
            binding.cameraLayout.setVisibility(View.VISIBLE);
            binding.editLayout.setVisibility(View.GONE);
            binding.imagePreview.setImageURI(null);
            startCamera(cameraFacing);
        });
        binding.txtCut.setOnClickListener(v -> {
            finish();
        });
        binding.btnOkay.setOnClickListener(v -> {

            progressDialog.setMessage("Uploading");
            progressDialog.setCancelable(false);
            progressDialog.show();
            RequestBody docType = RequestBody.create(MediaType.parse("text/plain"), documentType);
            // Check the URI scheme
            if (croppedImageUri.toString().startsWith("content://")) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(croppedImageUri);
                    File tempFile = new File(getCacheDir(), "tempImage.jpg");
                    copyInputStreamToFile(inputStream, tempFile);
                    document = tempFile;
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the error, possibly with a Toast or a Log message
                    return; // Exit the listener
                }
            } else {
                document = new File(croppedImageUri.getPath());
            }

            RequestBody proImg = RequestBody.create(MediaType.parse("image/*"), document);
            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("document", document.getName(), proImg);
            viewModel.document(CameraActivity.this, driverId, docType, imagePart);
        });

    }

    private void requestRuntimePermissionFunc(String manageStorage) {
        if (manageStorage.equals("storage")) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("this permission is required for this and this").setTitle("storage required").setCancelable(false).setPositiveButton("accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
                    }
                }).setNegativeButton("reject", (dialog, which) -> dialog.dismiss()).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
            }
        } else if (manageStorage.equals("manageStorage")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // Permission is granted
                    openGallery();
                    Log.d("Permission is granted", "yes");
                } else {
                    // Permission is not granted, request it
                    Log.d("Permission is granted", "no");

                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            }

        }


    }

    private void copyInputStreamToFile(InputStream in, File file) {
        try (OutputStream out = new FileOutputStream(file)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //for cropping
    private void startImageCrop(Uri imageUri) {
//        CropImage.activity(imageUri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .start(this);
    }

    //for camera
    private void startCamera(int cameraFacing) {

        int aspectRatio = aspectRatio(binding.previewView.getWidth(), binding.previewView.getHeight());
        ListenableFuture<ProcessCameraProvider> listenableFuture = ProcessCameraProvider.getInstance(this);

        listenableFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = (ProcessCameraProvider) listenableFuture.get();

                Preview preview = new Preview.Builder().setTargetAspectRatio(aspectRatio).build();
                ImageCapture imageCapture = new ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        .setTargetRotation(getWindowManager().getDefaultDisplay().getRotation()).build();

                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(cameraFacing).build();
                cameraProvider.unbindAll();
                Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
                Log.e("startCamera1", "");
                binding.capture.setOnClickListener(v -> {


                    if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                            && ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        Log.e("startCamera2", "");

                    } else {
                        Log.e("startCamera3", "");

                        takePicture(imageCapture);
                    }

                });
                preview.setSurfaceProvider(binding.previewView.getSurfaceProvider());
            } catch (ExecutionException | InterruptedException e) {
                Log.e("startCamera4", "");

                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));

    }

    // Start the image cropping process
    private void startImageCropCamera(File imageFile) {
        // Verify that the file exists before starting the cropping process
        if (imageFile.exists()) {
            Uri imageUri = Uri.fromFile(imageFile);
//            CropImage.activity(imageUri)
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .start(this);
        } else {
            Log.e("FileNotFound", "The file does not exist at the specified path.");
            // Handle the case where the file is not found
        }
    }

    //take picture from camera 2
    private void takePicture(ImageCapture imageCapture) {
        Log.e("startCamera5", "");

        final File file = new File(getExternalFilesDir(null), System.currentTimeMillis() + ".jpg");

        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();
        imageCapture.takePicture(outputFileOptions, Executors.newCachedThreadPool(), new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        selectedImageBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        binding.cameraLayout.setVisibility(View.GONE);
                        binding.editLayout.setVisibility(View.VISIBLE);
                        startImageCropCamera(file);

//                        Toast.makeText(CameraActivity.this, "Image Saved at:" + file.getPath(), Toast.LENGTH_SHORT).show();
                    }
                });
                startCamera(cameraFacing);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CameraActivity.this, "Failed to save:" + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                startCamera(cameraFacing);
            }
        });

    }

    private int aspectRatio(int width, int height) {
        double previewRatio = (double) Math.max(width, height) / Math.min(width, height);
        if (Math.abs(previewRatio = 4.0 / 3.0) <= Math.abs(previewRatio - 16.0 / 9.0)) {
            return AspectRatio.RATIO_4_3;
        }
        return AspectRatio.RATIO_16_9;
    }

    //open gallery
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            binding.cameraLayout.setVisibility(View.GONE);
//            binding.imagePreview.setImageURI(selectedImageUri);
            binding.editLayout.setVisibility(View.VISIBLE);
            startImageCrop(selectedImageUri);
        }
//        else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            // Handle the cropped image
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                croppedImageUri = result.getUri();
//                binding.imagePreview.setImageURI(croppedImageUri);
//                // Now you have the cropped and possibly rotated image, do what you need with it.
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Exception error = result.getError();
//                // Handle the error
//            }
//        }

    }
}
