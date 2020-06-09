package com.ekc.ekctracking.models.realmDB;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmCarByType extends RealmObject {

    @PrimaryKey
    private String carNo;

    private String phoneNo;

    private String gpsUnitNo;

    private String disabledCount;

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
