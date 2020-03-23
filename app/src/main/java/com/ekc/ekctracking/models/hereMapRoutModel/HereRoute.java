package com.ekc.ekctracking.models.hereMapRoutModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HereRoute {

    @SerializedName("route")
    private ArrayList<Route> route;

    public HereRoute() {
    }

    public ArrayList<Route> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Route> route) {
        this.route = route;
    }
}
