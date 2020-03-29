package com.ekc.ekctracking.view.activities.notification;

import android.os.Bundle;
import android.widget.ViewAnimator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ekc.ekctracking.R;
import com.ekc.ekctracking.models.realmDB.RealmCarStatus;
import com.ekc.ekctracking.view.adapters.NotificationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

public class NotificationActivity extends AppCompatActivity implements NotificationViewListener {

    @BindView(R.id.notification_activity_rec_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.notification_view_animator)
    ViewAnimator viewAnimator;

    private NotificationAdapter mNotificationAdapter;
    private NotificationActivity mCurrent;
    private NotificationPresenter presenter;
    private RealmList<RealmCarStatus> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        init();
    }

    private void init() {
        try {
            mCurrent = NotificationActivity.this;
            ButterKnife.bind(this);
            presenter = new NotificationPresenter(mCurrent, this);
            viewAnimator.setDisplayedChild(0);
            initNotificationsList();

            presenter.loadNotifications();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initNotificationsList() {
        try {
            cars = new RealmList<>();
            LinearLayoutManager layoutManager = new LinearLayoutManager(mCurrent);
            mNotificationAdapter = new NotificationAdapter(cars, mCurrent);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mNotificationAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNotificationsLoaded(RealmList<RealmCarStatus> cars) {
        this.cars = cars;
        if (cars != null && !cars.isEmpty()) {
            mNotificationAdapter.notifyDataSetChanged();
            viewAnimator.setDisplayedChild(2);
        } else {
            viewAnimator.setDisplayedChild(1);
        }
    }
}
