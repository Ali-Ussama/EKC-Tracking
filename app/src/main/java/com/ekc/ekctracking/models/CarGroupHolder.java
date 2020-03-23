package com.ekc.ekctracking.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CarGroupHolder implements Parcelable {

    @SerializedName("CarGroup")
    private ArrayList<CarGroupStatus> carGroupStatus;

    protected CarGroupHolder(Parcel in) {
        carGroupStatus = in.readParcelable(CarGroupStatus.class.getClassLoader());
    }

    public static final Creator<CarGroupHolder> CREATOR = new Creator<CarGroupHolder>() {
        @Override
        public CarGroupHolder createFromParcel(Parcel in) {
            return new CarGroupHolder(in);
        }

        @Override
        public CarGroupHolder[] newArray(int size) {
            return new CarGroupHolder[size];
        }
    };

    public ArrayList<CarGroupStatus> getCarGroupStatus() {
        return carGroupStatus;
    }

    public void setCarGroupStatus(ArrayList<CarGroupStatus> carGroupStatus) {
        this.carGroupStatus = carGroupStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(carGroupStatus);
    }
}
