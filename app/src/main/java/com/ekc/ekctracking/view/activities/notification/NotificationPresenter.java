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
    private Realm mRealm;

    public NotificationPresenter(NotificationActivity mContext, NotificationViewListener viewListener) {
        this.mContext = mContext;
        this.viewListener = viewListener;
        mPrefManager = new PrefManager(mContext);
        this.mRealm = Realm.getDefaultInstance();
    }

    void loadNotifications() {
        Log.d(TAG, "loadNotifications: is called");
        try {
            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try {
                        final RealmResults<RealmCarStatus> results = mRealm.where(RealmCarStatus.class).findAllAsync();
                        results.addChangeListener((realmCarStatuses, changeSet) -> {
                            Log.d(TAG, "loadNotifications: onChange: is called");
                            RealmList<RealmCarStatus> cars = new RealmList<>();
                            cars.addAll(realmCarStatuses);
                            if (viewListener != null) {
                                viewListener.onNotificationsLoaded(cars);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
