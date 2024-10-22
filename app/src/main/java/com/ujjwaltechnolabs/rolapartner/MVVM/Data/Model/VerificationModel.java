package com.ujjwaltechnolabs.rolapartner.MVVM.Data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("isNewDriver")
    @Expose
    private Boolean isNewDriver;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("driver")
    @Expose
    private Driver driver;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getIsNewDriver() {
        return isNewDriver;
    }

    public void setIsNewDriver(Boolean isNewDriver) {
        this.isNewDriver = isNewDriver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }public class Driver {

        @SerializedName("driverID")
        @Expose
        private Integer driverID;
        @SerializedName("email")
        @Expose
        private String email;

        public Integer getDriverID() {
            return driverID;
        }

        public void setDriverID(Integer driverID) {
            this.driverID = driverID;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }

}
