package com.ujjwaltechnolabs.rolapartner.MVVM.Data.Body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleRegisterBody {

    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("seat")
    @Expose
    private String seat;
    @SerializedName("licensePlateNumber")
    @Expose
    private String licensePlateNumber;
    @SerializedName("vehicleTypeId")
    @Expose
    private String vehicleTypeId;
    @SerializedName("energyTypeId")
    @Expose
    private String energyTypeId;
    @SerializedName("driverId")
    @Expose
    private String driverId;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getEnergyTypeId() {
        return energyTypeId;
    }

    public void setEnergyTypeId(String energyTypeId) {
        this.energyTypeId = energyTypeId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
