package com.ekc.ekctracking.models.findTrip;

public class SpeedFilter {

    private int speedFrom;
    private int speedTo;

    public SpeedFilter() {
    }

    public SpeedFilter(int speedFrom, int speedTo) {
        this.speedFrom = speedFrom;
        this.speedTo = speedTo;
    }

    public int getSpeedFrom() {
        return speedFrom;
    }

    public void setSpeedFrom(int speedFrom) {
        this.speedFrom = speedFrom;
    }

    public int getSpeedTo() {
        return speedTo;
    }

    public void setSpeedTo(int speedTo) {
        this.speedTo = speedTo;
    }
}
