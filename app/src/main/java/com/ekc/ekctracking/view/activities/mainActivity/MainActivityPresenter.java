package com.ekc.ekctracking.view.activities.mainActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ekc.ekctracking.R;
import com.ekc.ekctracking.configs.PrefManager;
import com.ekc.ekctracking.models.realmDB.RealmCarStatus;
import com.ekc.ekctracking.view.activities.notification.NotificationActivity;

import io.realm.RealmList;

public class MainActivityPresenter {

    private MainActivity mCurrent;
    private PrefManager mPrefManager;
    private MainActivityViewListener listener;

    public MainActivityPresenter(MainActivity mCurrent, MainActivityViewListener listener) {
        this.mCurrent = mCurrent;
        this.mPrefManager = new PrefManager(this.mCurrent);
        this.listener = listener;
    }

    void logout() {
        try {
            if (mPrefManager != null) {
                mPrefManager.saveString(PrefManager.KEY_PASSWORD, "");
                mPrefManager.saveString(PrefManager.KEY_USERNAME, "");
                mPrefManager.saveString(PrefManager.KEY_TOKEN, "");
                mPrefManager.saveString(PrefManager.KEY_ISSUED, "");
                mPrefManager.saveString(PrefManager.KEY_EXPIRES_IN, "");
                mPrefManager.saveString(PrefManager.KEY_TOKEN_TYPE, "");
                mPrefManager.saveString(PrefManager.KEY_GRANT_TYPE, "");

                listener.onLogoutSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    void pushCarStatusNotification(RealmList<RealmCarStatus> cars) {
        String tittle = "EKC Tracking";
        String subject = "يوجد " + cars.size() + " سيارات " + mCurrent.getString(R.string.disconnected_short);
        String body = "اضغط هنا لمعرفة المزيد";

        NotificationManager notificationManager = (NotificationManager) mCurrent.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(mCurrent, NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mCurrent, 0, intent, 0);

        Notification.Action action = new Notification.Action(R.drawable.ic_jeep_img, tittle, pendingIntent);

        Notification notify = new Notification.Builder
                (mCurrent.getApplicationContext())
                .setContentTitle(tittle)
                .setContentText(body)
                .setContentTitle(subject)
                .addAction(action)
                .setSmallIcon(R.drawable.ic_jeep_img).build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notify);

    }
}
