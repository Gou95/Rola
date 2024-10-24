package com.ujjwaltechnolabs.rolapartner.MVVM.ViewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ujjwaltechnolabs.rolapartner.Interface.ApiInterface;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Body.LoginBody;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Body.VehicleRegisterBody;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Body.VerificationBody;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Model.LoginModel;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Model.VerificationModel;
import com.ujjwaltechnolabs.rolapartner.MVVM.ViewModel.Login.DocumentModel;
import com.ujjwaltechnolabs.rolapartner.Model.SelectFuelTypeResponse;
import com.ujjwaltechnolabs.rolapartner.Model.SelectVehicleResponse;
import com.ujjwaltechnolabs.rolapartner.Model.VehicleRegisterResponse;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.Retrofit.RetrofitServices;
import com.ujjwaltechnolabs.rolapartner.Utils.CustomToast;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    ApiInterface apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

    MutableLiveData<LoginModel> mLoginResultMutableData;
    MutableLiveData<VerificationModel> mVerificationResultMutableData;
    MutableLiveData<DocumentModel> mDocumentResultMutableData;

    MutableLiveData<SelectVehicleResponse> mSelectVehicleResultMutableData;
    MutableLiveData<List<SelectFuelTypeResponse>> mSelectFuelTypeResultMutableData;
    MutableLiveData<VehicleRegisterResponse> mVehicleRegisterResultMutableData;

    public LoginViewModel(){
        mLoginResultMutableData = new MutableLiveData<>();
        mVerificationResultMutableData = new MutableLiveData<>();
        mDocumentResultMutableData = new MutableLiveData<>();
        mSelectVehicleResultMutableData = new MutableLiveData<>();
        mSelectFuelTypeResultMutableData = new MutableLiveData<>();
        mVehicleRegisterResultMutableData = new MutableLiveData<>();

    }
    public MutableLiveData<LoginModel> loginObserver() {
        return mLoginResultMutableData;
    }

    public MutableLiveData<VerificationModel> verificationObserver() {
        return mVerificationResultMutableData;
    }
    public MutableLiveData<DocumentModel> documentObserver() {
        return mDocumentResultMutableData;
    }

    public MutableLiveData<SelectVehicleResponse> selectVehicleObserver() {
        return mSelectVehicleResultMutableData;
    }
    public MutableLiveData<VehicleRegisterResponse> vehicleRegisterObserver() {
        return mVehicleRegisterResultMutableData;
    }
    public MutableLiveData<List<SelectFuelTypeResponse>> selectFueltypeObserver() {
        return mSelectFuelTypeResultMutableData;
    }

    public void login(Context context, LoginBody loginBody) {
        Call<LoginModel> call = apiInterface.LoginApi(loginBody);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()) {
                    mLoginResultMutableData.postValue(response.body());
                    Log.e("onResponse", response.message());
                } else {
                    CustomToast.showToastShort(context, context.getString(R.string.try_again));
                    Log.e("onResponseElse", response.message());
                    mLoginResultMutableData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                mLoginResultMutableData.postValue(null);
                Log.e("onFailure", t.getMessage());
                CustomToast.showToastShort(context,  context.getString(R.string.server_not_response));
                Log.e("onFailure", t.getLocalizedMessage());

            }
        });

    }

    public void verification(Context context, VerificationBody verificationBody) {
        Call<VerificationModel> call = apiInterface.verificationAPi(verificationBody);
        call.enqueue(new Callback<VerificationModel>() {
            @Override
            public void onResponse(Call<VerificationModel> call, Response<VerificationModel> response) {
                if (response.isSuccessful()) {
                    mVerificationResultMutableData.postValue(response.body());
                } else {
                    mVerificationResultMutableData.postValue(null);
                    CustomToast.showToastShort(context, context.getString(R.string.wrong_otp));

                }
            }

            @Override
            public void onFailure(Call<VerificationModel> call, Throwable t) {
                mVerificationResultMutableData.postValue(null);
                CustomToast.showToastShort(context, context.getString(R.string.server_not_response));

            }
        });

    }
    public void document(Context context, int driverId, RequestBody docType, MultipartBody.Part image) {
        Call<DocumentModel> call = apiInterface.uploadDocumentApi(driverId, docType, image);
        call.enqueue(new Callback<DocumentModel>() {
            @Override
            public void onResponse(Call<DocumentModel> call, Response<DocumentModel> response) {
                // Handle the API response here
                if (response.isSuccessful()) {
                    mDocumentResultMutableData.postValue(response.body());
                    Log.e("onResponse", response.message());
                } else {
                    CustomToast.showToastShort(context, context.getString(R.string.try_again));

                    mDocumentResultMutableData.postValue(null);
                    Log.e("onResponseElse", response.message());
                }
            }

            @Override
            public void onFailure(Call<DocumentModel> call, Throwable t) {
                // Handle the API error here
                Log.e("onFailure", t.getMessage());
                CustomToast.showToastShort(context, context.getString(R.string.server_not_response));
                mDocumentResultMutableData.postValue(null);
            }
        });
    }


    public void selectVehicle(Context context) {
        Call<SelectVehicleResponse> call = apiInterface.selectVehicleApi();
        call.enqueue(new Callback<SelectVehicleResponse>() {
            @Override
            public void onResponse(Call<SelectVehicleResponse> call, Response<SelectVehicleResponse> response) {
                // Handle the API response here
                if (response.isSuccessful()) {
                    mSelectVehicleResultMutableData.postValue(response.body());
                    Log.e("onResponse", response.message());
                } else {
                    CustomToast.showToastShort(context, context.getString(R.string.try_again));

                    mSelectVehicleResultMutableData.postValue(null);
                    Log.e("onResponseElse", response.message());
                }
            }

            @Override
            public void onFailure(Call<SelectVehicleResponse> call, Throwable t) {
                // Handle the API error here
                Log.e("onFailure", t.getMessage());
                CustomToast.showToastShort(context, context.getString(R.string.server_not_response));
                mSelectVehicleResultMutableData.postValue(null);
            }
        });
    }

    public void selectFuelType(Context context) {
        Call<List<SelectFuelTypeResponse>> call = apiInterface.selectFuelTypeApi();
        call.enqueue(new Callback<List<SelectFuelTypeResponse>>() {
            @Override
            public void onResponse(Call<List<SelectFuelTypeResponse>> call, Response<List<SelectFuelTypeResponse>> response) {
                // Handle the API response here
                if (response.isSuccessful()) {
                    mSelectFuelTypeResultMutableData.postValue(response.body());
                    Log.e("onResponse", response.message());
                } else {
                    CustomToast.showToastShort(context, context.getString(R.string.try_again));

                    mSelectFuelTypeResultMutableData.postValue(null);
                    Log.e("onResponseElse", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<SelectFuelTypeResponse>> call, Throwable t) {
                // Handle the API error here
                Log.e("onFailure", t.getMessage());
                CustomToast.showToastShort(context, context.getString(R.string.server_not_response));
                mSelectFuelTypeResultMutableData.postValue(null);
            }
        });
    }

    public void vehicleRegister(Context context,VehicleRegisterBody body) {
        Call<VehicleRegisterResponse> call = apiInterface.vehicleRegisterApi( body);
        call.enqueue(new Callback<VehicleRegisterResponse>() {
            @Override
            public void onResponse(Call<VehicleRegisterResponse> call, Response<VehicleRegisterResponse> response) {
                // Handle the API response here
                if (response.isSuccessful()) {
                    mVehicleRegisterResultMutableData.postValue(response.body());
                    Log.e("onResponse", response.message());
                } else {
                    CustomToast.showToastShort(context, context.getString(R.string.try_again));

                    mVehicleRegisterResultMutableData.postValue(null);
                    Log.e("onResponseElse", response.message());
                }
            }

            @Override
            public void onFailure(Call<VehicleRegisterResponse> call, Throwable t) {
                // Handle the API error here
                Log.e("onFailure", t.getMessage());
                CustomToast.showToastShort(context, context.getString(R.string.server_not_response));
                mVehicleRegisterResultMutableData.postValue(null);
            }
        });
    }
}
