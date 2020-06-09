package com.ekc.ekctracking.models.pojo.carType;

import com.google.gson.annotations.SerializedName;

public class CarByType {

    @SerializedName("Root")
    private CarByTypeRoot root;

    public CarByTypeRoot getRoot() {
        return root;
    }

    public void setRoot(CarByTypeRoot root) {
        this.root = root;
    }
}
