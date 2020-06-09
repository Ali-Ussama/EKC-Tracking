package com.ekc.ekctracking.models.pojo.carType;

import com.google.gson.annotations.SerializedName;


public class CarByTypeCars {

    @SerializedName("CarNo")
    private String carNo;

    @SerializedName("PhoneNo")
    private String phoneNo;

    @SerializedName("GPSUnitNumber")
    private String gpsUnitNo;

    @SerializedName("disabled_count")
    private String disabledCount;

    @SerializedName("CarID")
    private String carID;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGpsUnitNo() {
        return gpsUnitNo;
    }

    public void setGpsUnitNo(String gpsUnitNo) {
        this.gpsUnitNo = gpsUnitNo;
    }

    public String getDisabledCount() {
        return disabledCount;
    }

    public void setDisabledCount(String disabledCount) {
        this.disabledCount = disabledCount;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }
}
