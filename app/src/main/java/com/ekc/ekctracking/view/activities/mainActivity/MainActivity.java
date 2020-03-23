package com.ekc.ekctracking.view.activities.mainActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ekc.ekctracking.R;
import com.ekc.ekctracking.configs.AppUtils;
import com.ekc.ekctracking.view.activities.login.LoginActivity;
import com.ekc.ekctracking.view.fragments.home.HomeFragPresenter;
import com.ekc.ekctracking.view.fragments.home.HomeFragment;
import com.ekc.ekctracking.view.fragments.home.callbacks.HomeActivityCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityViewListener, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;

    FusedLocationProviderClient fusedLocationClient;
    MainActivity mCurrent;

    Location mCurrentLocation;

    MainActivityPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    DrawerLayout drawer;

    private HomeActivityCallback callback;

    private HomeFragPresenter mHomeFragPresenter;


    private HomeFragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//                getWindow().setExitTransition(new Explode());
//            }
            setContentView(R.layout.activity_main);

            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            mCurrent = MainActivity.this;

            presenter = new MainActivityPresenter(mCurrent, this);

            ButterKnife.bind(mCurrent);

//            setSupportActionBar(toolbar);

            initNavDrawer();

            initHomeFragmentViewModel();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initHomeFragmentViewModel() {
        try {

            mHomeFragment = HomeFragment.newInstance(mCurrent,this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initNavDrawer() {
        try {
            drawer = findViewById(R.id.drawer_layout);

            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_moving_report, R.id.nav_speed_report)
                    .setDrawerLayout(drawer)
                    .build();
            navigationView.setNavigationItemSelectedListener(this);

//            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//            NavigationUI.setupWithNavController(navigationView, navController);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLocation() {
        try {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(mCurrent);

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            mCurrentLocation = location;

                            if (callback != null) {
                                Log.i(TAG, "addOnSuccessListener(): sending google location to home fragment viewModel");
                                callback.onLocationChanged(location);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setLocationChangeListener(Object reference) {
        try {
            Log.i(TAG, "setLocationChangeListener(); is called");
            callback = (HomeActivityCallback) reference;
            initLocation();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logout() {
        AppUtils.showConfirmationDialog(mCurrent, getString(R.string.logout_dialog), getString(R.string.yes), getString(R.string.no), new AppUtils.CallBack() {
            @Override
            public void OnPositiveClicked(MaterialDialog dlg) {
                try {
                    presenter.logout();
                    dlg.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void OnNegativeClicked(MaterialDialog dlg) {
                dlg.dismiss();
            }
        });
    }

    @Override
    public void onLogoutSuccess() {
        try {
            Intent intent = new Intent(mCurrent, LoginActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void navDrawIconPressed() {
        try {
            if (drawer != null) {
                drawer.openDrawer(Gravity.START);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.i(TAG, "onNavigationItemSelected: is called");
        Log.d(TAG, "onNavigationItemSelected: item id = " + item.getItemId() + " - logout = " + R.id.nav_log_out);
        if (item.getItemId() == R.id.nav_log_out) {
            logout();
            return false;
        }
        return true;
    }
}
