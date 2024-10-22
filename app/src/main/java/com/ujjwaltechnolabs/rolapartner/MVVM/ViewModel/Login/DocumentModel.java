package com.ujjwaltechnolabs.rolapartner.MVVM.ViewModel.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("driverId")
    @Expose
    private Integer driverId;
    @SerializedName("documentType")
    @Expose
    private String documentType;
    @SerializedName("documentLink")
    @Expose
    private String documentLink;
    @SerializedName("expiryDate")
    @Expose
    private Object expiryDate;
    @SerializedName("verificationStatus")
    @Expose
    private String verificationStatus;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

//    public DocumentModel(String documentType) {
//        this.documentType = documentType;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    public Object getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Object expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
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
