package com.ekc.ekctracking.models;

import android.util.Log;

public class Logger {

    public void logFindTripRequest(String token, String gpsUnit, String fromDate, String toDate, String fromTime, String toTime, String TAG) {
        try {
            Log.i(TAG, "SaveMovingFilter: token = " + token);
            Log.i(TAG, "SaveMovingFilter: gpsUnit = " + gpsUnit);
            Log.i(TAG, "SaveMovingFilter: fromDate = " + fromDate);
            Log.i(TAG, "SaveMovingFilter: toDate = " + toDate);
            Log.i(TAG, "SaveMovingFilter: fromTime = " + fromTime);
            Log.i(TAG, "SaveMovingFilter: toTime = " + toTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
