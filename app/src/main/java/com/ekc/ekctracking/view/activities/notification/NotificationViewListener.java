package com.ekc.ekctracking.view.activities.notification;

import com.ekc.ekctracking.models.realmDB.RealmCarStatus;

import io.realm.RealmList;

public interface NotificationViewListener {

    void onNotificationsLoaded(RealmList<RealmCarStatus> cars);
}
