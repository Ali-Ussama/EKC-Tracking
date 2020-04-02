package com.ekc.ekctracking.view.activities.mainActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.NotificationCompat;

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
    public static final String CHANNEL_ID = "ekctracking_notification_channel_ID";

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
//        Intent intent = new Intent(mCurrent, NotificationActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(mCurrent, 100, intent, 0);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(mCurrent, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_jeep_small)
//                .setContentTitle(subject)
//                .setContentText(body)
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText("Much longer text that cannot fit one line..."))
//                .setContentIntent(pendingIntent)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setAutoCancel(true)
//                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
//                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
//
////            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mCurrent);
//        NotificationManager notificationManager = (NotificationManager) mCurrent.getSystemService(NOTIFICATION_SERVICE);
//        // notificationId is a unique int for each notification that you must define
//        notificationManager.notify(0, builder.build());

//        Intent intent = new Intent(mCurrent, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(mCurrent, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder b = new NotificationCompat.Builder(mCurrent);
//
//        b.setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.drawable.ic_jeep_small)
//                .setTicker("Hearty365")
//                .setContentTitle("Default notification")
//                .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
//                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
//                .setContentIntent(contentIntent)
//                .setContentInfo("Info");
//
//
//        NotificationManager notificationManager = (NotificationManager) mCurrent.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(1, b.build());

        scheduleNotification(getNotification(cars.size()), 0);
        Log.d(TAG, "Short lived task is done.");

    }

    private void scheduleNotification(Notification notification, int delay) {
        Log.d(TAG, "scheduleNotification: is called");
        Intent notificationIntent = new Intent(mCurrent, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mCurrent, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) mCurrent.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(int carsSize) {
        Log.d(TAG, "getNotification: is called");
        String tittle = "EKC Tracking";
        String subject = "يوجد " + carsSize + " سيارات " + mCurrent.getString(R.string.disconnected_short).concat("\n").concat("اضغط هنا لمعرفة المزيد");

        Intent intent = new Intent(mCurrent, NotificationActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(mCurrent, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action = new NotificationCompat.Action(R.mipmap.ic_launcher, tittle, contentIntent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mCurrent, CHANNEL_ID);
        builder.setAutoCancel(true)
                .setContentTitle(tittle)
                .setContentText(subject)
                .addAction(action)
                .setChannelId(CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        return builder.build();
    }

    void dummyPushNotification() {
        try {
            Log.d(TAG, "dummyPushNotification: is called");
            String tittle = "EKC Tracking";
            String subject = "يوجد " + 10 + " سيارات " + mCurrent.getString(R.string.disconnected_short);
            String body = "اضغط هنا لمعرفة المزيد";

//            // Create an explicit intent for an Activity in your app
//            Intent intent = new Intent(mCurrent, NotificationActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//            PendingIntent pendingIntent = PendingIntent.getActivity(mCurrent, 100, intent, 0);
//
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(mCurrent, CHANNEL_ID)
//                    .setSmallIcon(R.drawable.ic_jeep_small)
//                    .setContentTitle(subject)
//                    .setContentText(body)
//                    .setStyle(new NotificationCompat.BigTextStyle()
//                            .bigText("Much longer text that cannot fit one line..."))
//                    .setContentIntent(pendingIntent)
//                    .setPriority(NotificationCompat.PRIORITY_HIGH)
//                    .setAutoCancel(true)
//                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
//                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
//
////            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mCurrent);
//            NotificationManager notificationManager = (NotificationManager) mCurrent.getSystemService(NOTIFICATION_SERVICE);
//            // notificationId is a unique int for each notification that you must define
//            notificationManager.notify(100, builder.build());

//            Intent intent = new Intent(mCurrent, MainActivity.class);
//            PendingIntent contentIntent = PendingIntent.getActivity(mCurrent, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//            NotificationCompat.Builder b = new NotificationCompat.Builder(mCurrent);
//
//            b.setAutoCancel(true)
//                    .setDefaults(Notification.DEFAULT_ALL)
//                    .setWhen(System.currentTimeMillis())
//                    .setSmallIcon(R.drawable.ic_jeep_small)
//                    .setTicker("Hearty365")
//                    .setContentTitle("Default notification")
//                    .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
//                    .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
//                    .setContentIntent(contentIntent)
//                    .setContentInfo("Info");
//
//
//            NotificationManager notificationManager = (NotificationManager) mCurrent.getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.notify(1, b.build());

            scheduleNotification(getNotification(0), 400);

            Log.d(TAG, "dummyPushNotification: Short lived task is done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
