package com.ekc.ekctracking.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class CarStatus implements Parcelable, Comparator {

    @SerializedName("CarNo")
    private String carNo;

    @SerializedName("GPSUnitNumber")
    private String GPSUnitNumber;

    @SerializedName("disabled_count")
    private int disable_count;

    @SerializedName("CarID")
    private String carID;

    @SerializedName("Latitude")
    private double latitude;

    @SerializedName("Longitude")
    private double longitude;

    @SerializedName("Status")
    private String status;

    @SerializedName("speed")
    private String speed;

    @SerializedName("Address")
    private String address;

    @SerializedName("date")
    private String date;

    @SerializedName("time")
    private String time;

    @SerializedName("Speed")
    private String speed2;

    @SerializedName("GPS_Unit")
    private String gpsUnit;

    @SerializedName("driverName")
    private String driverName;

    private String dataTime;

    protected CarStatus(Parcel in) {
        carNo = in.readString();
        GPSUnitNumber = in.readString();
        disable_count = in.readInt();
        carID = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        status = in.readString();
        speed = in.readString();
        address = in.readString();
        date = in.readString();
        time = in.readString();
        speed2 = in.readString();
        gpsUnit = in.readString();
        driverName = in.readString();
    }

    public static final Creator<CarStatus> CREATOR = new Creator<CarStatus>() {
        @Override
        public CarStatus createFromParcel(Parcel in) {
            return new CarStatus(in);
        }

        @Override
        public CarStatus[] newArray(int size) {
            return new CarStatus[size];
        }
    };

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getGPSUnitNumber() {
        return GPSUnitNumber;
    }

    public void setGPSUnitNumber(String GPSUnitNumber) {
        this.GPSUnitNumber = GPSUnitNumber;
    }

    public int getDisable_count() {
        return disable_count;
    }

    public void setDisable_count(int disable_count) {
        this.disable_count = disable_count;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSpeed2() {
        return speed2;
    }

    public void setSpeed2(String speed2) {
        this.speed2 = speed2;
    }

    public String getGpsUnit() {
        return gpsUnit;
    }

    public void setGpsUnit(String gpsUnit) {
        this.gpsUnit = gpsUnit;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }


    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(carNo);
        dest.writeString(GPSUnitNumber);
        dest.writeInt(disable_count);
        dest.writeString(carID);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(status);
        dest.writeString(speed);
        dest.writeString(address);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(speed2);
        dest.writeString(gpsUnit);
        dest.writeString(driverName);
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
