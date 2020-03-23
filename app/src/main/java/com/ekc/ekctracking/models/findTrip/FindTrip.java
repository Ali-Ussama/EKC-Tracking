package com.ekc.ekctracking.models.findTrip;

import com.google.gson.annotations.SerializedName;

public class FindTrip {

    @SerializedName("FoundCars")
    private FoundCars foundCars;

    public FoundCars getFoundCars() {
        return foundCars;
    }

    public void setFoundCars(FoundCars foundCars) {
        this.foundCars = foundCars;
    }
}
