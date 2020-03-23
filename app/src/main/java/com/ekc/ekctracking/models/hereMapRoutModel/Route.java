package com.ekc.ekctracking.models.hereMapRoutModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Route {

    @SerializedName("leg")
    private ArrayList<Leg> leg;

    public Route() {
    }

    public ArrayList<Leg> getLeg() {
        return leg;
    }

    public void setLeg(ArrayList<Leg> leg) {
        this.leg = leg;
    }
}
