package com.ekc.ekctracking.models.findTrip;

public class FindTripRequest {

    private String gpsUnitNo;

    private String fromDate;

    private String fromTime;

    private String toDate;

    private String toTime;

    private String token;

    public FindTripRequest() {
    }

    public FindTripRequest(String gpsUnitNo, String fromDate, String fromTime, String toDate, String toTime, String token) {
        this.gpsUnitNo = gpsUnitNo;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
        this.token = token;
    }

    public String getGpsUnitNo() {
        return gpsUnitNo;
    }

    public void setGpsUnitNo(String gpsUnitNo) {
        this.gpsUnitNo = gpsUnitNo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
