package com.ujjwaltechnolabs.rolapartner.Interface;


import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Body.LoginBody;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Body.VehicleRegisterBody;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Body.VerificationBody;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Model.LoginModel;
import com.ujjwaltechnolabs.rolapartner.MVVM.Data.Model.VerificationModel;
import com.ujjwaltechnolabs.rolapartner.MVVM.ViewModel.Login.DocumentModel;
import com.ujjwaltechnolabs.rolapartner.Model.SelectFuelTypeResponse;
import com.ujjwaltechnolabs.rolapartner.Model.SelectVehicleResponse;
import com.ujjwaltechnolabs.rolapartner.Model.VehicleRegisterResponse;
import com.ujjwaltechnolabs.rolapartner.Utils.ApiUtils;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {
    @POST(ApiUtils.PHONE_API)
    Call<LoginModel> LoginApi(@Body LoginBody loginBody);

    @POST(ApiUtils.VERIFICATION_OTP_API)
    Call<VerificationModel> verificationAPi(@Body VerificationBody verificationBody);
    @Multipart
    @POST(ApiUtils.DRIVER_DOCUMENT_UPLOAD_API)
    Call<DocumentModel> uploadDocumentApi(@Path("id") int id,
                                          @Part("documentType") RequestBody documentType,
                                          @Part MultipartBody.Part document);

    @GET(ApiUtils.GET_SELECT_VEHICLE)
    Call<SelectVehicleResponse> selectVehicleApi();

    @GET(ApiUtils.GET_SELECT_FUELTYPE)
    Call<List<SelectFuelTypeResponse>> selectFuelTypeApi();

    @POST(ApiUtils.VEHICLES_CREATE)
    Call<VehicleRegisterResponse> vehicleRegisterApi(@Body VehicleRegisterBody body);


}
