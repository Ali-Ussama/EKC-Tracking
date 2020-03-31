package com.ekc.ekctracking.view.activities.notification;

import android.util.Log;

import com.ekc.ekctracking.configs.PrefManager;
import com.ekc.ekctracking.models.realmDB.RealmCarStatus;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class NotificationPresenter {


    private static final String TAG = "NotificationPresenter";
    private NotificationActivity mContext;
    private NotificationViewListener viewListener;
    private PrefManager mPrefManager;
    private Realm realm;

    public NotificationPresenter(NotificationActivity mContext, NotificationViewListener viewListener) {
        this.mContext = mContext;
        this.viewListener = viewListener;
        mPrefManager = new PrefManager(mContext);
        this.realm = Realm.getDefaultInstance();
    }

    void loadNotifications() {
        Log.d(TAG, "loadNotifications: is called");
        final RealmResults<RealmCarStatus> results = realm.where(RealmCarStatus.class).findAll();
        RealmList<RealmCarStatus> cars = new RealmList<>();
        cars.addAll(results);
        if (viewListener != null) {
            viewListener.onNotificationsLoaded(cars);
        }
        results.addChangeListener((realmCarStatuses, changeSet) -> {
            Log.d(TAG, "loadNotifications: onChange: is called");
            cars.clear();
            cars.addAll(realmCarStatuses);
            if (viewListener != null) {
                viewListener.onNotificationsLoaded(cars);
            }
        });

    }
}
