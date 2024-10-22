package com.ujjwaltechnolabs.rolapartner.Model;

public class DriverResponse {
    private String contactInformation,residentialAddress,employeeStatus,completerides,currentVehicle;

    public DriverResponse(String contactInformation, String residentialAddress, String employeeStatus, String completerides, String currentVehicle) {
        this.contactInformation = contactInformation;
        this.residentialAddress = residentialAddress;
        this.employeeStatus = employeeStatus;
        this.completerides = completerides;
        this.currentVehicle = currentVehicle;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getCompleterides() {
        return completerides;
    }

    public void setCompleterides(String completerides) {
        this.completerides = completerides;
    }

    public String getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentVehicle(String currentVehicle) {
        this.currentVehicle = currentVehicle;
    }
}
