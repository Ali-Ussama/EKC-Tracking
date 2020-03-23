package com.ekc.ekctracking.models.hereMapRoutModel;

import com.google.gson.annotations.SerializedName;

public class Maneuver {

    @SerializedName("position")
    private Position position;

    @SerializedName("length")
    private double length;

    public Maneuver() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public static class Position {

        @SerializedName("latitude")
        private double latitude;

        @SerializedName("longitude")
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
