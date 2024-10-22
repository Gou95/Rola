package com.ujjwaltechnolabs.rolapartner.MVVM.Data.Body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VerificationBody {

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("language_code")
    @Expose
    private String languageCode;
    @SerializedName("fcmToken")
    @Expose
    private String fcmToken;
    @SerializedName("preferredCurrency")
    @Expose
    private String preferredCurrency;
    @SerializedName("location")
    @Expose
    private Location location;

    public VerificationBody(String phoneNumber, String otp, String languageCode, String fcmToken, String preferredCurrency, Location location) {
        this.phoneNumber = phoneNumber;
        this.otp = otp;
        this.languageCode = languageCode;
        this.fcmToken = fcmToken;
        this.preferredCurrency = preferredCurrency;
        this.location = location;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getPreferredCurrency() {
        return preferredCurrency;
    }

    public void setPreferredCurrency(String preferredCurrency) {
        this.preferredCurrency = preferredCurrency;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static class Location {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("coordinates")
        @Expose
        private List<Double> coordinates;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }

    }
}
