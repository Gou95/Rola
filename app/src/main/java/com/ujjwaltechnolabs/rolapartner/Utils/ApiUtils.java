package com.ujjwaltechnolabs.rolapartner.Utils;

public class ApiUtils {
    public static final String BASE_URL_TWO = "https://twprt-d0h5gfhzamgqdafk.eastus-01.azurewebsites.net";
    public static final String PHONE_API = "/auth/register/request-otp";
    public static final String VERIFICATION_OTP_API = "/auth/login/verify-otp-driver";
    public static final String DRIVER_DOCUMENT_UPLOAD_API = "/api/v1/drivers/{id}/documents";
    public static final String GET_REGISTRATION = "/api/v1/drivers/{id}/with-documents";
    public static final String GET_VEHICLE_TYPE = "/api/v1/vehicle-types";
    public static final String GET_FUEL_TYPE = "/api/v1/energyTypes";
    public static final String REGISTER_API = "api/v1/drivers/{id}";
    public static final String GET_SELECT_VEHICLE = "/api/v1/vehicleTypes";
    public static final String GET_SELECT_FUELTYPE = "/api/v1/energyTypes";
}
