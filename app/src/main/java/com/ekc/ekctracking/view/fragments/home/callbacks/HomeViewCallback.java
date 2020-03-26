package com.ekc.ekctracking.view.fragments.home.callbacks;

import android.location.Location;

import com.ekc.ekctracking.models.findTrip.FindTrip;
import com.ekc.ekctracking.models.hereMapRoutModel.Maneuver;
import com.ekc.ekctracking.models.pojo.CarStatus;
import com.ekc.ekctracking.models.pojo.StatusRoot;

import java.util.ArrayList;

public interface HomeViewCallback {

    void onLocationChanged(Location location);

    void onOnGoingStatus(StatusRoot statusRoot);

    void onFindTripSuccess(FindTrip findTrip);

    void onFindTripFailure(Throwable throwable);

    void onHereRoutSuccess(ArrayList<Maneuver> positionsResult);

    void onHereRoutFailure(Throwable throwable);

    void onSortPoints(FindTrip findTrip);

    void onCarSelected(CarStatus carStatus);
}
