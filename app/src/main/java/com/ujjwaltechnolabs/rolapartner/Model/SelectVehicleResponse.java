package com.ujjwaltechnolabs.rolapartner.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectVehicleResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Data data;
    public class Data {

        @SerializedName("vehicleTypes")
        @Expose
        private List<VehicleType> vehicleTypes;

        public List<VehicleType> getVehicleTypes() {
            return vehicleTypes;
        }

        public void setVehicleTypes(List<VehicleType> vehicleTypes) {
            this.vehicleTypes = vehicleTypes;
        }
        public class VehicleType {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("baseFare")
            @Expose
            private Integer baseFare;
            @SerializedName("averageSpeed")
            @Expose
            private Integer averageSpeed;
            @SerializedName("fuelConsumption")
            @Expose
            private Integer fuelConsumption;
            @SerializedName("imageUrl")
            @Expose
            private String imageUrl;
            @SerializedName("status")
            @Expose
            private Boolean status;
            @SerializedName("createdAt")
            @Expose
            private String createdAt;
            @SerializedName("updatedAt")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getBaseFare() {
                return baseFare;
            }

            public void setBaseFare(Integer baseFare) {
                this.baseFare = baseFare;
            }

            public Integer getAverageSpeed() {
                return averageSpeed;
            }

            public void setAverageSpeed(Integer averageSpeed) {
                this.averageSpeed = averageSpeed;
            }

            public Integer getFuelConsumption() {
                return fuelConsumption;
            }

            public void setFuelConsumption(Integer fuelConsumption) {
                this.fuelConsumption = fuelConsumption;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public Boolean getStatus() {
                return status;
            }

            public void setStatus(Boolean status) {
                this.status = status;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

        }
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
