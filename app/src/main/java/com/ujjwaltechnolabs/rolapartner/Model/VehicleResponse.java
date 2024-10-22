package com.ujjwaltechnolabs.rolapartner.Model;

public class VehicleResponse {
    private String vehicleNumber,running,rideComplete,vehicleType;

    public VehicleResponse(String vehicleNumber, String running, String rideComplete, String vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.running = running;
        this.rideComplete = rideComplete;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRunning() {
        return running;
    }

    public void setRunning(String running) {
        this.running = running;
    }

    public String getRideComplete() {
        return rideComplete;
    }

    public void setRideComplete(String rideComplete) {
        this.rideComplete = rideComplete;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
