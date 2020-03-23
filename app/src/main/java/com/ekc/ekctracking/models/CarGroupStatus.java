package com.ekc.ekctracking.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CarGroupStatus implements Parcelable {

    @SerializedName("Name")
    private String clientName;

    @SerializedName("Car")
    private ArrayList<CarStatus> cars;

    protected CarGroupStatus(Parcel in) {
        clientName = in.readString();
        cars = in.createTypedArrayList(CarStatus.CREATOR);
    }

    public static final Creator<CarGroupStatus> CREATOR = new Creator<CarGroupStatus>() {
        @Override
        public CarGroupStatus createFromParcel(Parcel in) {
            return new CarGroupStatus(in);
        }

        @Override
        public CarGroupStatus[] newArray(int size) {
            return new CarGroupStatus[size];
        }
    };

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ArrayList<CarStatus> getCars() {
        return cars;
    }

    public void setCars(ArrayList<CarStatus> cars) {
        this.cars = cars;
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
        dest.writeString(clientName);
        dest.writeTypedList(cars);
    }
}
