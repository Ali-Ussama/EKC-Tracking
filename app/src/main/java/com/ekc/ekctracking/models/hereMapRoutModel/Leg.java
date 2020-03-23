package com.ekc.ekctracking.models.hereMapRoutModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Leg {

    @SerializedName("maneuver")
    private ArrayList<Maneuver> maneuver;

    public Leg() {
    }

    public ArrayList<Maneuver> getManeuver() {
        return maneuver;
    }

    public void setManeuver(ArrayList<Maneuver> maneuver) {
        this.maneuver = maneuver;
    }
}
