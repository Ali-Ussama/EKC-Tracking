package com.ekc.ekctracking.view.activities.carConfig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ekc.ekctracking.R;
import com.ekc.ekctracking.configs.AppUtils;
import com.ekc.ekctracking.models.realmDB.RealmCarByType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class CarConfigActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int MY_KEY_FOR_RETURNED_VALUE = 1;
    private static final int CALL_REQUEST_CODE = 2;

    @BindView(R.id.activity_car_config_car_details_car_no)
    TextView mCarNoTV;

    @BindView(R.id.activity_car_config_car_details_gps_no)
    TextView mGPSNoTV;

    @BindView(R.id.activity_car_config_car_details_sim_card_no)
    TextView mSIMCardNoTV;

    @BindView(R.id.activity_car_config_car_details_call_fab)
    FloatingActionButton mCallFab;

    @BindView(R.id.activity_car_config_check_car_container)
    ConstraintLayout mCheckActionContainer;

    @BindView(R.id.activity_car_config_check_gprs_container)
    ConstraintLayout mGPRSActionContainer;

    @BindView(R.id.activity_car_config_set_device_first_time_container)
    ConstraintLayout mBeginActionContainer;

    @BindView(R.id.activity_car_config_set_password_container)
    ConstraintLayout mPasswordActionContainer;

    @BindView(R.id.activity_car_config_internet_network_container)
    ConstraintLayout mInternetActionContainer;

    @BindView(R.id.activity_car_config_ip_port_network_container)
    ConstraintLayout mIPPortContainer;

    @BindView(R.id.activity_car_config_ip_and_port_network_arrow)
    ImageView mIpPortArrow;

    @BindView(R.id.activity_car_config_ip_network_et)
    TextInputEditText mIpET;

    @BindView(R.id.activity_car_config_port_network_et)
    TextInputEditText mPortET;

    @BindView(R.id.activity_car_config_ip_port_network_sub_container)
    ConstraintLayout mIPPortSubContainer;

    @BindView(R.id.activity_car_config_ip_and_port_network_fab)
    FloatingActionButton mIPPortFabAction;

    //Advanced Configurations
    //Time Interval
    @BindView(R.id.activity_car_config_time_interval_sub_container)
    ConstraintLayout mTimeIntervalSubContainer;

    @BindView(R.id.activity_car_config_time_interval_arrow)
    ImageView mTimeIntervalArrow;

    @BindView(R.id.activity_car_config_time_interval_et)
    TextInputEditText mTimeIntervalET;

    //Distance
    @BindView(R.id.activity_car_config_distance_sub_container)
    ConstraintLayout mDistanceSubContainer;

    @BindView(R.id.activity_car_config_distance_arrow)
    ImageView mDistanceArrow;

    @BindView(R.id.activity_car_config_distance_et)
    TextInputEditText mDistanceET;

    //Angle
    @BindView(R.id.activity_car_config_angle_sub_container)
    ConstraintLayout mAngleSubContainer;

    @BindView(R.id.activity_car_config_angle_arrow)
    ImageView mAngleArrow;

    @BindView(R.id.activity_car_config_angle_et)
    TextInputEditText mAngleET;

    private static final String TAG = "CarConfigActivity";
    private CarConfigActivity mCurrent;
    private String carNo;
    private String simCardNo;
    private Realm realm;
    private RealmCarByType car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_config);

        init();
    }

    private void init() {
        try {
            ButterKnife.bind(this);
            mCurrent = CarConfigActivity.this;

            carNo = getIntent().getStringExtra(getString(R.string.car_no));
            realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            car = realm.where(RealmCarByType.class).contains("carNo", carNo).findFirst();
            realm.commitTransaction();

            if (car != null) {
                Log.d(TAG, "init: car != null");
                mCarNoTV.setText(carNo);
                mSIMCardNoTV.setText(car.getPhoneNo());
                mGPSNoTV.setText(car.getGpsUnitNo());
                simCardNo = car.getPhoneNo();
            }

            requestPermission();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestPermission() {
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                // permission is not granted, ask for permission:
                ActivityCompat.requestPermissions(this, //assuming this is Activity or a subclass of it
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_KEY_FOR_RETURNED_VALUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {

    }

    public void handleCarCheckAction(View view) {
        try {
            String sms = "Check123456";
            sendMessage(sms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String smsMessage) {
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                // permission is not granted, ask for permission:
                ActivityCompat.requestPermissions(this, //assuming this is Activity or a subclass of it
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_KEY_FOR_RETURNED_VALUE);
            } else {

                String dialogMsg = getString(R.string.are_you_sure_to_send_sms).concat(" ").concat(smsMessage).concat("\n").concat("الى رقم ").concat(simCardNo).concat(" ?");
                AppUtils.showConfirmationDialog(mCurrent, dialogMsg, getString(R.string.send), getString(R.string.cancel), new AppUtils.CallBack() {
                    @Override
                    public void OnPositiveClicked(MaterialDialog dlg) {
                        String smsNumber = String.format("smsto: %s", simCardNo);

                        // Set the service center address if needed, otherwise null.
                        String scAddress = null;
                        // Set pending intents to broadcast
                        // when message sent and when delivered, or set to null.
                        PendingIntent sentIntent = null, deliveryIntent = null;
                        // Use SmsManager.
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage
                                (smsNumber, null, smsMessage,
                                        null, null);

                        dlg.dismiss();
                        AppUtils.showToast(mCurrent, getString(R.string.message_sent_successfully));
                    }

                    @Override
                    public void OnNegativeClicked(MaterialDialog dlg) {

                    }
                });
//            Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
//
//            // Set the data for the intent as the phone number.
//            smsIntent.setData(Uri.parse(smsNumber));
//            // Add the message (sms) with the key ("sms_body").
//            smsIntent.putExtra("sms_body", sms);
//            // If package resolves (target app installed), send intent.
//            if (smsIntent.resolveActivity(getPackageManager()) != null) {
//                startActivity(smsIntent);
//            } else {
//                Log.e(TAG, "Can't resolve app for ACTION_SENDTO Intent");
//            }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCarGPRSAction(View view) {
        try {
            String sms = "GPRS123456";
            sendMessage(sms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleBeginAction(View view) {
        try {
            String sms = "Begin123456";
            sendMessage(sms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleSetPasswordAction(View view) {
        try {
            String sms = "Password123456 123456";
            sendMessage(sms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleInternetNetworkAction(View view) {
        try {
            String sms = "Apn123456 mobinilweb";
            if (simCardNo.startsWith("010")) {
                sms = "Apn123456 internet.vodafone.net";
            } else if (simCardNo.startsWith("011")) {
                sms = "Apn123456 Etisalat";
            }
            sendMessage(sms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleIPPortVisibilityAction(View view) {
        try {
            if (mIPPortSubContainer.getVisibility() == View.VISIBLE) {
                mIpPortArrow.setImageResource(R.drawable.ic_chevron_left_black_24dp);
                mIPPortSubContainer.setVisibility(View.GONE);
            } else {
                mIpPortArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                mIPPortSubContainer.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleIPPortAction(View view) {
        try {
            if (mIpET.getText() == null || mIpET.getText().toString().isEmpty()) {
                mIpET.setError(getString(R.string.required));
            } else if (mPortET.getText() == null || mPortET.getText().toString().isEmpty()) {
                mPortET.setError(getString(R.string.required));
            } else {
                //adminip123456 92.42.106.205 8040”
                String ip = mIpET.getText().toString();
                String port = mPortET.getText().toString();

                String sms = "adminip123456 " + ip + " " + port;

                sendMessage(sms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCallSIMCardNoAction(View view) {
        callPhoneNo();
    }

    private void callPhoneNo() {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mCurrent, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
            } else {
                String uri = "tel:" + simCardNo.trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleTimeIntervalAction(View view) {
        if (mTimeIntervalET.getText() == null || mTimeIntervalET.getText().toString().isEmpty()) {
            mTimeIntervalET.setError(getString(R.string.required));
        } else if (mTimeIntervalET.getText().toString().length() == 3 && Integer.parseInt(mTimeIntervalET.getText().toString()) > 255) {
            mTimeIntervalET.setError(getString(R.string.max_time_is_255));
        } else if (mTimeIntervalET.getText().toString().length() <= 2 && Integer.parseInt(mTimeIntervalET.getText().toString()) < 20) {
            mTimeIntervalET.setError(getString(R.string.min_time_is_20));
        } else {
            //fix030***n 123456
            String time = mTimeIntervalET.getText().toString();
            if (time.length() == 2) {
                time = "0".concat(time);
            }
            String sms = "fix" + time + "s***n 123456";

            sendMessage(sms);
        }
    }

    public void handleTimeIntervalVisibilityAction(View view) {
        try {
            if (mTimeIntervalSubContainer.getVisibility() == View.VISIBLE) {
                mTimeIntervalArrow.setImageResource(R.drawable.ic_chevron_left_black_24dp);
                mTimeIntervalSubContainer.setVisibility(View.GONE);
            } else {
                mTimeIntervalArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                mTimeIntervalSubContainer.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleDistanceAction(View view) {
        if (mDistanceET.getText() == null || mDistanceET.getText().toString().isEmpty()) {
            mDistanceET.setError(getString(R.string.required));
        } else if (mDistanceET.getText().toString().length() > 4) {
            mDistanceET.setError(getString(R.string.max_distance_digits));
        } else {
            //distance123456 0050
            String distance = mDistanceET.getText().toString();
            if (distance.length() == 1) {
                distance = "000".concat(distance);
            } else if (distance.length() == 2) {
                distance = "00".concat(distance);
            } else if (distance.length() == 3) {
                distance = "0".concat(distance);
            }
            String sms = "distance123456 " + distance;

            sendMessage(sms);
        }
    }

    public void handleDistanceVisibilityAction(View view) {
        try {
            if (mDistanceSubContainer.getVisibility() == View.VISIBLE) {
                mDistanceArrow.setImageResource(R.drawable.ic_chevron_left_black_24dp);
                mDistanceSubContainer.setVisibility(View.GONE);
            } else {
                mDistanceArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                mDistanceSubContainer.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleAngleVisibilityAction(View view) {
        try {
            if (mAngleSubContainer.getVisibility() == View.VISIBLE) {
                mAngleArrow.setImageResource(R.drawable.ic_chevron_left_black_24dp);
                mAngleSubContainer.setVisibility(View.GONE);
            } else {
                mAngleArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                mAngleSubContainer.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleAngleAction(View view) {
        if (mAngleET.getText() == null || mAngleET.getText().toString().isEmpty()) {
            mAngleET.setError(getString(R.string.required));
        } else if (mAngleET.getText().toString().length() > 3) {
            mAngleET.setError(getString(R.string.max_angle_digits));
        } else {
            //angle+password+space+angle angle123456 030
            String angle = mAngleET.getText().toString();
            if (angle.length() == 1) {
                angle = "00".concat(angle);
            } else if (angle.length() == 2) {
                angle = "0".concat(angle);
            }
            String sms = "angle123456 " + angle;

            sendMessage(sms);
        }
    }

    public void handleBackAction(View view) {
        finish();
    }
}
