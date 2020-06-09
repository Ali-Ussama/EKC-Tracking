package com.ekc.ekctracking.view.activities.notification;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ViewAnimator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ekc.ekctracking.R;
import com.ekc.ekctracking.models.realmDB.RealmCarStatus;
import com.ekc.ekctracking.view.adapters.NotificationAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

public class NotificationActivity extends AppCompatActivity implements NotificationViewListener, MaterialSearchView.OnQueryTextListener {

    private static final String TAG = "NotificationActivity";
    @BindView(R.id.notification_activity_rec_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.notification_view_animator)
    ViewAnimator viewAnimator;

    @BindView(R.id.notification_activity_toolbar)
    Toolbar toolbar;

    @BindView(R.id.notification_activity_search_view)
    MaterialSearchView searchView;

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

            setupToolbar();
            initNotificationsList();
            searchView.setOnQueryTextListener(this);

            presenter.loadNotifications();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.menu_notification));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initNotificationsList() {
        try {
            cars = new RealmList<>();
            LinearLayoutManager layoutManager = new LinearLayoutManager(mCurrent);
            mNotificationAdapter = new NotificationAdapter(cars, mCurrent,presenter);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mNotificationAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNotificationsLoaded(RealmList<RealmCarStatus> cars) {

       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               Log.d(TAG, "onNotificationsLoaded: is called");
               mCurrent.cars = cars;
               if (cars != null && !cars.isEmpty()) {
                   Log.d(TAG, "onNotificationsLoaded: cars != null ");
                   Log.d(TAG, "onNotificationsLoaded: cars size " + cars.size());
                   mNotificationAdapter.notifyDataSetChanged();
                   mNotificationAdapter.dataChanged(mCurrent.cars);
                   viewAnimator.setDisplayedChild(2);
               } else {
                   Log.d(TAG, "onNotificationsLoaded: cars == null ");
                   viewAnimator.setDisplayedChild(1);
               }
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu, menu);

        MenuItem item = menu.findItem(R.id.notification_action_search);
        searchView.setMenuItem(item);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
