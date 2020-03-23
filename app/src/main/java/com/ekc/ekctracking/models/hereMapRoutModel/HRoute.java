package com.ekc.ekctracking.models.hereMapRoutModel;

import com.google.gson.annotations.SerializedName;

public class HRoute {

    @SerializedName("response")
    private HereRoute response;

    public HRoute() {
    }

    public HereRoute getResponse() {
        return response;
    }

    public void setResponse(HereRoute response) {
        this.response = response;
    }
}
