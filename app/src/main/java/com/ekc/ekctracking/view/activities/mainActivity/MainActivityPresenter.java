package com.ekc.ekctracking.view.activities.mainActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ekc.ekctracking.R;
import com.ekc.ekctracking.configs.PrefManager;
import com.ekc.ekctracking.models.realmDB.RealmCarStatus;
import com.ekc.ekctracking.view.activities.notification.NotificationActivity;

import io.realm.RealmList;

public class MainActivityPresenter {

    private static final String TAG = "MainActivityPresenter";
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
        Log.d(TAG, "pushCarStatusNotification: is called");
        String tittle = "EKC Tracking";
        String subject = "يوجد " + cars.size() + " سيارات " + mCurrent.getString(R.string.disconnected_short);
        String body = "اضغط هنا لمعرفة المزيد";
//
//        NotificationManager notificationManager = (NotificationManager) mCurrent.getSystemService(Context.NOTIFICATION_SERVICE);
//        Intent intent = new Intent(mCurrent, NotificationActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(mCurrent, 0, intent, 0);
//
//        Notification.Action action = new Notification.Action(R.mipmap.ic_launcher, tittle, pendingIntent);
//
//        Notification notify = new Notification.Builder
//                (mCurrent.getApplicationContext())
//                .setContentTitle(tittle)
//                .setContentText(body)
//                .setContentTitle(subject)
//                .addAction(action)
//                .setSmallIcon(R.mipmap.ic_launcher).build();
//
//
//        notify.flags |= Notification.FLAG_AUTO_CANCEL;
//        notificationManager.notify(0, notify);

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(mCurrent, NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(mCurrent, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mCurrent, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(subject)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mCurrent);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
        Log.d(TAG, "Short lived task is done.");

    }
    private static final String CHANNEL_ID = "ekctracking_notification_channel_ID";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    void dummyPushNotification(){
        try {
            Log.d(TAG, "dummyPushNotification: is called");
            String tittle = "EKC Tracking";
            String subject = "يوجد " + 10 + " سيارات " + mCurrent.getString(R.string.disconnected_short);
            String body = "اضغط هنا لمعرفة المزيد";

            // Create an explicit intent for an Activity in your app
            Intent intent = new Intent(mCurrent, NotificationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(mCurrent, 0, intent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(mCurrent, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(subject)
                    .setContentText(body)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Much longer text that cannot fit one line..."))
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mCurrent);

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, builder.build());
            Log.d(TAG, "Short lived task is done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
