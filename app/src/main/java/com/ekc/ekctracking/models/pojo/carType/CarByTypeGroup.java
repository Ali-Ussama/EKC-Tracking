package com.ekc.ekctracking.models.pojo.carType;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarByTypeGroup {

    @SerializedName("Name")
    private String groupName;

    @SerializedName("Car")
    private List<CarByTypeCars> cars;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<CarByTypeCars> getCars() {
        return cars;
    }

    public void setCars(List<CarByTypeCars> cars) {
        this.cars = cars;
    }
}
