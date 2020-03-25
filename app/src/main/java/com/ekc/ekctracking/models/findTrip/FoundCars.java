package com.ekc.ekctracking.models.findTrip;

import com.ekc.ekctracking.models.pojo.CarStatus;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoundCars {

    @SerializedName("ToDateMarks")
    private String toDateMarks;

    @SerializedName("DromDateMarks")
    private String fromDateMarks;

    @SerializedName("Car")
    private ArrayList<CarStatus> locations;

    public String getToDateMarks() {
        return toDateMarks;
    }

    public void setToDateMarks(String toDateMarks) {
        this.toDateMarks = toDateMarks;
    }

    public String getFromDateMarks() {
        return fromDateMarks;
    }

    public void setFromDateMarks(String fromDateMarks) {
        this.fromDateMarks = fromDateMarks;
    }

    public ArrayList<CarStatus> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<CarStatus> locations) {
        this.locations = locations;
    }
}
