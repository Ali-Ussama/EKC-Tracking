package com.ekc.ekctracking.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OnGoingRequest implements Parcelable {

    @SerializedName("Authorization")
    private String authorization;

    protected OnGoingRequest(Parcel in) {
        authorization = in.readString();
    }

    public OnGoingRequest() {
    }

    public static final Creator<OnGoingRequest> CREATOR = new Creator<OnGoingRequest>() {
        @Override
        public OnGoingRequest createFromParcel(Parcel in) {
            return new OnGoingRequest(in);
        }

        @Override
        public OnGoingRequest[] newArray(int size) {
            return new OnGoingRequest[size];
        }
    };

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
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
        dest.writeString(authorization);
    }
}
