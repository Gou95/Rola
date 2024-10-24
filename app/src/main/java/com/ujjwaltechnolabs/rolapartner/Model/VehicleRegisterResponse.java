package com.ujjwaltechnolabs.rolapartner.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleRegisterResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("childSeatAccessibility")
    @Expose
    private Boolean childSeatAccessibility;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("licensePlateNumber")
    @Expose
    private String licensePlateNumber;
    @SerializedName("vehicleTypeId")
    @Expose
    private Integer vehicleTypeId;
    @SerializedName("energyTypeId")
    @Expose
    private Integer energyTypeId;
    @SerializedName("driverId")
    @Expose
    private Integer driverId;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getChildSeatAccessibility() {
        return childSeatAccessibility;
    }

    public void setChildSeatAccessibility(Boolean childSeatAccessibility) {
        this.childSeatAccessibility = childSeatAccessibility;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Integer getEnergyTypeId() {
        return energyTypeId;
    }

    public void setEnergyTypeId(Integer energyTypeId) {
        this.energyTypeId = energyTypeId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
