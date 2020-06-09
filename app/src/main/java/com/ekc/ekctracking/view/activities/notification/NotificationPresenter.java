package com.ekc.ekctracking.view.activities.notification;

import android.Manifest;
import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ekc.ekctracking.R;
import com.ekc.ekctracking.configs.AppUtils;
import com.ekc.ekctracking.configs.PrefManager;
import com.ekc.ekctracking.models.realmDB.RealmCarByType;
import com.ekc.ekctracking.models.realmDB.RealmCarStatus;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class NotificationPresenter {


    private static final String TAG = "NotificationPresenter";
    private static final int MY_KEY_FOR_RETURNED_VALUE = 2;
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

    public void onCarCheckAction(RealmCarStatus car) {
        try {
            mRealm.beginTransaction();
            RealmCarByType carByType = mRealm.where(RealmCarByType.class).contains(mContext.getString(R.string.car_no), car.getCarNo()).findFirst();
            mRealm.commitTransaction();
            if (carByType != null && carByType.getPhoneNo() != null) {
                String sms = "Check123456";
                String smsNo = "Tel: ".concat(carByType.getPhoneNo());
                sendSms(sms, smsNo, carByType.getPhoneNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCarGPRSAction(RealmCarStatus car) {
        try {
            mRealm.beginTransaction();
            RealmCarByType carByType = mRealm.where(RealmCarByType.class).contains(mContext.getString(R.string.car_no), car.getCarNo()).findFirst();
            mRealm.commitTransaction();
            if (carByType != null && carByType.getPhoneNo() != null) {
                String sms = "GPRS123456";
                String smsNo = String.format("smsto: %s", carByType.getCarNo());
                sendSms(sms, smsNo, carByType.getPhoneNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendSms(String smsMessage, String smsNo, String simCardNo) {
        try {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                // permission is not granted, ask for permission:
                ActivityCompat.requestPermissions(mContext, //assuming this is Activity or a subclass of it
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_KEY_FOR_RETURNED_VALUE);
            } else {

                String dialogMsg = mContext.getString(R.string.are_you_sure_to_send_sms).concat(" ").concat(smsMessage).concat("\n").concat("الى رقم ").concat(simCardNo).concat(" ?");
                AppUtils.showConfirmationDialog(mContext, dialogMsg, mContext.getString(R.string.send), mContext.getString(R.string._cancel), new AppUtils.CallBack() {
                    @Override
                    public void OnPositiveClicked(MaterialDialog dlg) {

                        // Set the service center address if needed, otherwise null.
                        // Set pending intents to broadcast
                        // when message sent and when delivered, or set to null.
                        // Use SmsManager.
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage
                                (smsNo, null, smsMessage,
                                        null, null);

                        dlg.dismiss();
                        AppUtils.showToast(mContext, mContext.getString(R.string.message_sent_successfully));
                    }

                    @Override
                    public void OnNegativeClicked(MaterialDialog dlg) {
                        dlg.dismiss();

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
