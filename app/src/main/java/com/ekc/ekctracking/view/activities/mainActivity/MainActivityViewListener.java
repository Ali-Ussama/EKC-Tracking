package com.ekc.ekctracking.view.activities.mainActivity;

import com.ekc.ekctracking.models.realmDB.RealmCarStatus;

import io.realm.RealmList;

public interface MainActivityViewListener {

    void onLogoutSuccess();

    void navDrawIconPressed();

    void onCarStatusChanged(RealmList<RealmCarStatus> cars);

}
