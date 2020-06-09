package com.ekc.ekctracking.models.pojo.carType;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarByTypeRoot {

    @SerializedName("CarGroup")
    private List<CarByTypeGroup> group;

    public List<CarByTypeGroup> getGroup() {
        return group;
    }

    public void setGroup(List<CarByTypeGroup> group) {
        this.group = group;
    }
}
