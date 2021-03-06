package com.ekc.ekctracking.view.fragments.home;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ViewAnimator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import com.ekc.ekctracking.R;
import com.ekc.ekctracking.configs.AppUtils;
import com.ekc.ekctracking.configs.PrefManager;
import com.ekc.ekctracking.models.Logger;
import com.ekc.ekctracking.models.findTrip.FindTrip;
import com.ekc.ekctracking.models.findTrip.FindTripRequest;
import com.ekc.ekctracking.models.findTrip.SpeedFilter;
import com.ekc.ekctracking.models.hereMapRoutModel.HereRoute;
import com.ekc.ekctracking.models.hereMapRoutModel.Maneuver;
import com.ekc.ekctracking.models.pojo.CarGroupStatus;
import com.ekc.ekctracking.models.pojo.CarStatus;
import com.ekc.ekctracking.models.pojo.StatusRoot;
import com.ekc.ekctracking.view.activities.mainActivity.MainActivity;
import com.ekc.ekctracking.view.activities.mainActivity.MainActivityViewListener;
import com.ekc.ekctracking.view.activities.mainActivity.MapSingleTapListener;
import com.ekc.ekctracking.view.activities.mainActivity.SingleTapListener;
import com.ekc.ekctracking.view.adapters.CarsListAdapter;
import com.ekc.ekctracking.view.adapters.SpeedAdapter;
import com.ekc.ekctracking.view.fragments.home.callbacks.HomeViewCallback;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.loadable.LoadStatusChangedEvent;
import com.esri.arcgisruntime.loadable.LoadStatusChangedListener;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedEvent;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedListener;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.view.WrapAroundMode;
import com.esri.arcgisruntime.portal.Portal;
import com.esri.arcgisruntime.security.AuthenticationChallengeHandler;
import com.esri.arcgisruntime.security.AuthenticationManager;
import com.esri.arcgisruntime.security.DefaultAuthenticationChallengeHandler;
import com.esri.arcgisruntime.security.UserCredential;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.tasks.networkanalysis.PointBarrier;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteParameters;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteResult;
import com.esri.arcgisruntime.tasks.networkanalysis.RouteTask;
import com.esri.arcgisruntime.tasks.networkanalysis.Stop;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

public class HomeFragment extends Fragment implements
        HomeViewCallback, SingleTapListener,
        View.OnClickListener, HomeFragmentListener,
        MaterialSearchView.OnQueryTextListener,
        MapScaleChangedListener {

    //Speed
    @BindView(R.id.home_frag_speeds_recycler_view)
    RecyclerView mSpeedRV;

    @BindView(R.id.moving_report_filter_speed_to_outlinedTextField)
    TextInputLayout mSpeedToContainer;

    @BindView(R.id.moving_report_filter_speed_to_et)
    TextInputEditText mSpeedToET;

    @BindView(R.id.moving_report_filter_speed_from_outlinedTextField)
    TextInputLayout mSpeedFromContainer;

    @BindView(R.id.moving_report_filter_speed_from_et)
    TextInputEditText mSpeedFromET;

    //Home Layout
    @BindView(R.id.home_fragment_view_animator)
    ViewAnimator rootViewAnimator;

    @BindView(R.id.mapView)
    MapView mapView;

    @BindView(R.id.home_frag_location_fab)
    FloatingActionButton mLocationFab;

    //Car Details Bottom Sheet
    @BindView(R.id.home_bottom_sheet_car_details_toolbar)
    View carDetailsToolbar;

    @BindView(R.id.bottom_sheet)
    LinearLayout mBottomSheetLayout;

    @BindView(R.id.home_bottom_sheet_car_details_view_animator)
    ViewAnimator mBottomSheetViewAnimator;

    @BindView(R.id.home_bottom_sheet_car_details_container)
    ConstraintLayout mCarDetailsContainer;

    @BindView(R.id.home_bottom_sheet_car_details_close_ic)
    ImageView mBottomSheetCloseIcon;

    @BindView(R.id.home_bottom_sheet_car_details_expand_collapse_ic)
    ImageView mBottomSheetCollapseExpandIcon;

    @BindView(R.id.home_bottom_sheet_car_details_title_tv)
    TextView mBottomSheetCarDetailsTitleTV;

    @BindView(R.id.home_bottom_sheet_car_details_car_no_value)
    TextView mCarNoTV;

    @BindView(R.id.home_bottom_sheet_car_details_car_date_value)
    TextView mCarDateTV;

    @BindView(R.id.home_bottom_sheet_car_details_car_time_value)
    TextView mCarTimeTV;

    @BindView(R.id.home_bottom_sheet_car_details_car_address_value)
    TextView mCarAddressTV;

    @BindView(R.id.home_bottom_sheet_car_details_gps_unit_ic)
    ImageView mCarStatusIcon;

    @BindView(R.id.home_bottom_sheet_car_details_gps_unit_value)
    TextView mCarGPSUnitNumberTV;

    @BindView(R.id.home_bottom_sheet_car_details_speed_value)
    TextView mCarSpeedTV;

    @BindView(R.id.home_bottom_sheet_car_details_period_report_container)
    ConstraintLayout mMovingReportBottomSheetAction;
//
    @BindView(R.id.home_bottom_sheet_car_details_settings_container)
    ConstraintLayout mSettingBottomSheetAction;
//
//    @BindView(R.id.home_bottom_sheet_car_details_speed_report_container)
//    ConstraintLayout mSpeedReportBottomSheetAction;
//
//    @BindView(R.id.home_bottom_sheet_car_details_distance_report_container)
//    ConstraintLayout mDistanceReportBottomSheetAction;

    //Cars List
    @BindView(R.id.home_cars_list_container)
    ConstraintLayout mHomeCarsListContainer;

    @BindView(R.id.home_cars_list)
    RecyclerView mHomeCarsListRV;

    // Moving Report Filter
    @BindView(R.id.moving_report_filter_car_no_tv)
    TextView movingFilterCarTitle;

    @BindView(R.id.moving_report_filter_close_ic)
    ImageView movingFilterCloseIV;

    @BindView(R.id.moving_report_filter_save_ic)
    ImageView movingFilterSaveIV;

    @BindView(R.id.moving_report_filter_title_tv)
    TextView movingFilterTitleIV;

    @BindView(R.id.moving_report_filter_date_et)
    TextInputEditText movingFilterDateET;

    @BindView(R.id.moving_report_filter_time_from_et)
    TextInputEditText movingFilterTimeFromET;

    @BindView(R.id.moving_report_filter_time_to_et)
    TextInputEditText movingFilterTimeToET;

    @BindView(R.id.home_nav_drawer_ic)
    ImageView mNavDrawerIc;

    @BindView(R.id.home_toolbar)
    Toolbar toolbar;

    @BindView(R.id.home_notification_tv_count)
    TextView notificationCount;

    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    BottomSheetBehavior sheetBehavior;

    ServiceFeatureTable featureTable;
    private CarsListAdapter mCarsListAdapter;
    private ArrayList<CarStatus> carsListData;
    private List<CarStatus> speedRecData;
    private PointCollection speedRecPoint;
    private SpeedAdapter mSpeedAdapter;

    private HomeFragPresenter presenter;
    private static final int ACCESS_LOCATION = 1;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST = 2;
    private final int MY_LOCATION_REQUEST_CODE = 2;
    private static final int RQC_PICK_DATE_TIME_RANGE = 4;
    private static final String TAG = "HomeFragment";
    private GoogleMap mMap;
    private static MainActivity mCurrent;
    private PrefManager mPrefManager;

    private ArrayList<MarkerOptions> markerOptions;
    private ArcGISMap baseMap;
    private GraphicsOverlay graphicsOverlay, drawGraphicLayer;
    private PictureMarkerSymbol pictureMarkerSymbol, greenMarker, redMarker, yellowMarker, blueMarker, startBannerMarker, endBannerMarker;
    private Point mCurrentLocation;
    private SpatialReference spatialReference;

    private ArrayList<Point> carsPoints;
    MapSingleTapListener mapSingleTapListener;
    private ArrayList<CarStatus> cars;
    private ArrayList<CarStatus> oldCars;

    private StatusRoot mStatusRoot;
    private CarStatus selectedCar;
    private FindTrip findTripResponse;
    private ArrayList<HereRoute> hereRoutes;
    private ArrayList<Maneuver.Position> hereLocations;

    private static MainActivityViewListener mActivityListener;

    private final int carDetailsPeekHeight = 130;

    private boolean queryStatus = false;
    private Portal portal;
    private MaterialDialog mProgressDlg;

    private Logger logger;

    private static Realm mRealm;
    private SpeedFilter speedFilter;

    public static HomeFragment newInstance(MainActivity current, Realm realm, MainActivityViewListener activityListener) {
        mCurrent = current;
        mActivityListener = activityListener;
        mRealm = realm;
        return new HomeFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = null;
        try {
            root = inflater.inflate(R.layout.fragment_home, container, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {

            ButterKnife.bind(this, view);
            setHasOptionsMenu(true);

            init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.home_frag_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_cars_menu) {
            handleCarList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleCarList() {
        try {
            if (mHomeCarsListContainer.getVisibility() == View.GONE) {
                displayCarsList();
            } else if (mHomeCarsListContainer.getVisibility() == View.VISIBLE) {
                hideCarsList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayCarsList() {
        try {
            if (mHomeCarsListContainer.getVisibility() == View.GONE) {
                mHomeCarsListContainer.setVisibility(View.VISIBLE);

                Animation anim = new ScaleAnimation(
                        0.5f, 1f, // Start and end values for the X axis scaling
                        0f, 1f, // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 1f, // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 0f); // Pivot point of Y scaling
                anim.setFillAfter(true); // Needed to keep the result of the animation
                anim.setDuration(400);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.d(TAG, "onAnimationEnd: is called");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mHomeCarsListContainer.startAnimation(anim);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideCarsList() {
        if (mHomeCarsListContainer.getVisibility() == View.VISIBLE) {
            Animation anim = new ScaleAnimation(
                    1f, 0.5f, // Start and end values for the X axis scaling
                    1f, 0f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 1f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 0f); // Pivot point of Y scaling
            anim.setDuration(300);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.d(TAG, "onAnimationEnd: is called");
                    mHomeCarsListContainer.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mHomeCarsListContainer.startAnimation(anim);
        }
    }

    private void init() {
        try {
            searchView.setVisibility(View.VISIBLE);
            mCurrent.setSupportActionBar(toolbar);

            logger = new Logger();

            ActionBar actionBar = mCurrent.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowTitleEnabled(false);
            }

            mPrefManager = new PrefManager(mCurrent);
            mRealm = Realm.getDefaultInstance();

            presenter = new HomeFragPresenter(mCurrent, this, mRealm, mActivityListener);

            presenter.requestToken();
            presenter.requestOnGoingCarsStatus();

            displayHomeLayout();

            initCarsList();

            initBottomSheet();

            initMap();

            initActions();

            initSpeedList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSpeedList() {
        try {
            speedRecData = new ArrayList<>();
            LinearLayoutManager layoutManager = new LinearLayoutManager(mCurrent, RecyclerView.HORIZONTAL, false);
            mSpeedAdapter = new SpeedAdapter(speedRecData, speedRecPoint, mCurrent, this);
            mSpeedRV.setLayoutManager(layoutManager);
            mSpeedRV.setAdapter(mSpeedAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCarsList() {
        try {
            Log.d(TAG, "initCarsList: is called");
            cars = new ArrayList<>();
            mCarsListAdapter = new CarsListAdapter(cars, mCurrent, this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mCurrent);
            mHomeCarsListRV.setLayoutManager(layoutManager);
            mHomeCarsListRV.setAdapter(mCarsListAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initActions() {
        try {
            movingFilterTimeFromET.setOnClickListener(this);
            movingFilterTimeToET.setOnClickListener(this);

            movingFilterDateET.setOnClickListener(this);
            movingFilterSaveIV.setOnClickListener(this);
            movingFilterCloseIV.setOnClickListener(this);
            mLocationFab.setOnClickListener(this);

            mMovingReportBottomSheetAction.setOnClickListener(this);
//            mFindTripReportBottomSheetAction.setOnClickListener(this);
//            mSpeedReportBottomSheetAction.setOnClickListener(this);
//            mDistanceReportBottomSheetAction.setOnClickListener(this);

            mNavDrawerIc.setOnClickListener(this);
            searchView.setOnQueryTextListener(this);

            mapView.addMapScaleChangedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loginIntoArcServer() {
        UserCredential userCredential = new UserCredential("AliUssama", "ali199571");
        featureTable = new ServiceFeatureTable(getString(R.string.network_analysis_service));
        // set up an authentication handler to take credentials for access to the protected map service
        AuthenticationChallengeHandler handler = new DefaultAuthenticationChallengeHandler(mCurrent);
        AuthenticationManager.setAuthenticationChallengeHandler(handler);

        // create a portal to ArcGIS Online
        portal = new Portal("http://www.arcgis.com", true);

        portal.loadAsync();

        portal.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "loginIntoArcServer: portal loaded");
                    Log.i(TAG, "loginIntoArcServer: portal.getUser().getEmail() + " + portal.getUser().getEmail().toString());
                    Log.i(TAG, "loginIntoArcServer: portal.getUser().getEmail() + " + portal.getUser().getAccess().toString());

//                    startTaskRout(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void initBottomSheet() {
        try {
            sheetBehavior = BottomSheetBehavior.from(mBottomSheetLayout);
            sheetBehavior.setState(STATE_HIDDEN);

            mBottomSheetCloseIcon.setOnClickListener(this);
            mBottomSheetCollapseExpandIcon.setOnClickListener(this);
            sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == STATE_HIDDEN) {
                        sheetBehavior.setState(STATE_COLLAPSED);
                    } else if ((newState == STATE_EXPANDED || newState == STATE_HALF_EXPANDED) && mBottomSheetViewAnimator.getDisplayedChild() == 1) {
                        sheetBehavior.setState(STATE_EXPANDED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initMap() {
        try {

//            if (ActivityCompat.checkSelfPermission(mCurrent, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//                    ActivityCompat.checkSelfPermission(mCurrent, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
//            {
//                ActivityCompat.requestPermissions((MainActivity) mCurrent,
//                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION);
//            } else {
            spatialReference = SpatialReference.create(4326);

            baseMap = new ArcGISMap(Basemap.createOpenStreetMap());
            mapView.setMap(baseMap);
            mapView.setWrapAroundMode(WrapAroundMode.ENABLE_WHEN_SUPPORTED);

            drawGraphicLayer = new GraphicsOverlay();
            graphicsOverlay = new GraphicsOverlay();
            mapView.getGraphicsOverlays().add(graphicsOverlay);
            mapView.getGraphicsOverlays().add(drawGraphicLayer);

            pictureMarkerSymbol = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.ic_car_test_3));
            greenMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.green));
            redMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.reed));
            yellowMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.yellow));
            blueMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.blue));


            startBannerMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.ic_start_marker_green));
            endBannerMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.ic_end_trip_marker_flag));
            if (checkLocationPermissions()) {

                // displaying user location on map
                showDeviceLocation();
            }
//            }

            initSingleTap();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSingleTap() {
        try {
            mapSingleTapListener = new MapSingleTapListener(mCurrent, mapView, this);
            mapView.setOnTouchListener(mapSingleTapListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkLocationPermissions() {
        try {
            if (ContextCompat.checkSelfPermission(mCurrent, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions((Activity) mCurrent,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_LOCATION_REQUEST_CODE);

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            try {
                if ((permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) || (permissions[0].equals(Manifest.permission.ACCESS_COARSE_LOCATION) &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    showDeviceLocation();
                } else {
                    AppUtils.showToast(mCurrent, getString(R.string.please_open_gps_location));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showDeviceLocation() {
        try {
            Log.i(TAG, "showDeviceLocation(): is called");

            LocationDisplay locationDisplay = mapView.getLocationDisplay();
            locationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.NAVIGATION);

            locationDisplay.addLocationChangedListener(new LocationDisplay.LocationChangedListener() {
                @Override
                public void onLocationChanged(LocationDisplay.LocationChangedEvent locationChangedEvent) {
                    //reading location changing
                    mCurrentLocation = locationChangedEvent.getLocation().getPosition();

                    if (mCurrentLocation != null) {
                        double accuracy = locationChangedEvent.getLocation().getHorizontalAccuracy();
                        int mAccuracy = (int) (accuracy * 100);
                        accuracy = (double) mAccuracy / 100;

                    }


                }
            });

            locationDisplay.addDataSourceStatusChangedListener(dataSourceStatusChangedEvent -> {

                if (dataSourceStatusChangedEvent.getSource().getLocationDataSource().getError() == null) {
                    Log.i(TAG, "Location Display Started=" + dataSourceStatusChangedEvent.isStarted());
                } else {
                    dataSourceStatusChangedEvent.getSource().getLocationDataSource().getError().printStackTrace();
                }
            });
            locationDisplay.startAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Log.i(TAG, "onLocationChanged(): is called");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOnGoingStatus(StatusRoot statusRoot) {
        try {
            mStatusRoot = statusRoot;
            displayCarsOnMap(mStatusRoot);
            Log.i(TAG, "onOnGoingStatus: graphics size = " + graphicsOverlay.getGraphics().size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayCarsOnMap(StatusRoot statusRoot) {
        try {
            if (cars != null && !cars.isEmpty()) {
                for (CarGroupStatus carGroupStatus : statusRoot.getRoot().getCarGroupStatus()) {
                    carGroupStatus.setCars(presenter.calcAngle(carGroupStatus.getCars(), cars,spatialReference,mapView.getSpatialReference()));
                }
            }
            oldCars = new ArrayList<>();
            oldCars.addAll(cars);

            cars = new ArrayList<>();
            graphicsOverlay.clearSelection();
            graphicsOverlay.getGraphics().clear();
            carsPoints = new ArrayList<>();
            markerOptions = new ArrayList<>();
            Log.i(TAG, "onOnGoingStatus: " + mapView.getSpatialReference().getWkid());


            for (CarGroupStatus carGroupStatus : statusRoot.getRoot().getCarGroupStatus()) {

                Log.i(TAG, "onOnGoingStatus(): Client Name = " + carGroupStatus.getClientName());

                for (CarStatus car : carGroupStatus.getCars()) {

                    if (car != null) {
                        cars.add(car);

                        Point point = (Point) GeometryEngine.project(new Point(car.getLongitude(), car.getLatitude(), spatialReference), mapView.getSpatialReference());
                        Graphic carGraphic = null;

                        if (car.getStatus().equals("Moving")) {
                            greenMarker = null;
                            greenMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.green));
                            greenMarker.setWidth(18f);
                            greenMarker.setHeight(36f);
                            greenMarker.setAngle((float) car.getAngle());
                            carGraphic = new Graphic(point, greenMarker);
                        } else if (car.getStatus().equals("Stopped")) {
                            redMarker = null;
                            redMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.reed));
                            redMarker.setWidth(18f);
                            redMarker.setHeight(36f);
                            redMarker.setAngle((float) car.getAngle());
                            carGraphic = new Graphic(point, redMarker);
                        } else if (car.getStatus().equals("Disconnected")) {
                            yellowMarker = null;
                            yellowMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.yellow));
                            yellowMarker.setWidth(18f);
                            yellowMarker.setHeight(36f);
                            yellowMarker.setAngle((float) car.getAngle());
                            carGraphic = new Graphic(point, yellowMarker);
                        } else if (car.getStatus().equals("Disabled")) {
                            blueMarker = null;
                            blueMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.blue));
                            blueMarker.setWidth(18f);
                            blueMarker.setHeight(36f);
                            blueMarker.setAngle((float) car.getAngle());
                            carGraphic = new Graphic(point, blueMarker);
                        }

                        if (drawGraphicLayer != null && drawGraphicLayer.getGraphics() != null && drawGraphicLayer.getGraphics().isEmpty()) {

                            graphicsOverlay.getGraphics().add(carGraphic);
                        }

                        carsPoints.add(point);

                    }
                }
            }

            mCarsListAdapter.onDataChanged(cars);

            Log.i(TAG, "onOnGoingStatus: cars size = " + cars.size());

            String[] query_suggestions = new String[cars.size()];
            for (int i = 0; i < cars.size(); i++) {
                //                if (cars.get(i).getGPSUnitNumber() != null) {
                //                    Log.i(TAG, "onOnGoingStatus: unit = " + cars.get(i).getGPSUnitNumber());
                //                    query_suggestions[i] = cars.get(i).getGPSUnitNumber();
                //                }
                if (cars.get(i).getCarNo() != null) {
                    String carNo = cars.get(i).getCarNo();
                    carNo = getCarNoWithoutText(carNo);
                    Log.i(TAG, "onOnGoingStatus: Car No = " + carNo.trim());
                    query_suggestions[(i)] = carNo.trim();
                }
            }
            searchView.setSuggestionIcon(mCurrent.getResources().getDrawable(R.drawable.ic_directions_car_grey_24dp));
            searchView.setSuggestions(query_suggestions);


            if (oldCars != null && !oldCars.isEmpty()) {
                Log.d(TAG, "displayCarsOnMap: calling checkCarsStatusChanged");
                checkCarsStatusChanged(oldCars, cars);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void checkCarsStatusChanged(ArrayList<CarStatus> oldCars, ArrayList<CarStatus> newCars) {
        Log.d(TAG, "checkCarsStatusChanged: is called");
        Log.d(TAG, "checkCarsStatusChanged: calling presenter checkCarsStatusChanged");
        presenter.checkCarsStatusChanged(oldCars, newCars);
    }

    private String getCarNoWithoutText(String carNo) {
        String result = "";
        try {
            for (int i = 0; i < carNo.length(); i++) {
                if (TextUtils.isDigitsOnly(String.valueOf(carNo.charAt(i)))) {
                    result = result.concat(String.valueOf(carNo.charAt(i)));
                }
            }
            result = result.replaceAll("", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onSingleTap(Point point) {
        if (mHomeCarsListContainer.getVisibility() == View.VISIBLE) {
            hideCarsList();
        } else if (!queryStatus && drawGraphicLayer != null && drawGraphicLayer.getGraphics() != null && drawGraphicLayer.getGraphics().isEmpty()) {
            if (sheetBehavior.getState() != STATE_HIDDEN) {
                hideCarDetailsBottomSheet();
            }

            selectCar(point);
        } else if (drawGraphicLayer != null && drawGraphicLayer.getGraphics() != null && !drawGraphicLayer.getGraphics().isEmpty()) {
            //TODO display Car Speed within Find Trip query
        }
    }

    private void selectCar(Point point) {
        try {
            Log.i(TAG, "selectCar: is called");
            Point foundedPoint = null;
            CarStatus foundedCar = null;
            double shortestDistance = 1000000000;

            for (Point carsPoint : carsPoints) {
                double distance = getDistance(carsPoint, point);

                if (distance <= shortestDistance) {
                    shortestDistance = distance;

                    foundedPoint = carsPoint;
                }
            }

            if (shortestDistance <= 1000) {
                if (cars != null && !cars.isEmpty()) {
                    Log.i(TAG, "selectCar: cars size = " + cars.size());
                    for (CarStatus car : cars) {
                        Point mProjectCarPoint = (Point) GeometryEngine.project(new Point(car.getLongitude(), car.getLatitude(), spatialReference), mapView.getSpatialReference());
                        Log.i(TAG, "selectCar: mProjectCarPoint X = " + mProjectCarPoint.getX() + " Y = " + mProjectCarPoint.getY());

                        if (mProjectCarPoint.getX() == foundedPoint.getX() && mProjectCarPoint.getY() == foundedPoint.getY()) {
                            foundedCar = car;
                            Log.i(TAG, "selectCar: car founded with id = " + foundedCar.getCarID() + " car No = " + car.getCarNo());
                            break;
                        }
                    }
                } else {
                    Log.i(TAG, "selectCar: cars = null");
                }

                if (foundedCar != null) {
                    Log.i(TAG, "selectCar: car is founded");
                    zoomToPoint(foundedPoint);
                    displayBottomSheet(foundedCar);
                } else {
                    Log.i(TAG, "selectCar: car not founded");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void zoomToPoint(Point foundedPoint) {
        try {
            if (foundedPoint != null) {
                mapView.setViewpoint(new Viewpoint(foundedPoint, 7000.0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayBottomSheet(CarStatus foundedCar) {
        try {
            hideCarsList();
            selectedCar = foundedCar;

            mCarDetailsContainer.setVisibility(View.VISIBLE);
            mBottomSheetLayout.setBackgroundColor(mCurrent.getResources().getColor(R.color.transparent));
            mBottomSheetViewAnimator.setDisplayedChild(0);
            sheetBehavior.setPeekHeight(carDetailsPeekHeight, true);

            hide(mLocationFab);
            show(mBottomSheetLayout);

            sheetBehavior.setState(STATE_HALF_EXPANDED);
            sheetBehavior.setPeekHeight(carDetailsPeekHeight, true);
            mBottomSheetCollapseExpandIcon.setImageResource(R.drawable.ic_keyboard_arrow_down_white_24dp);

            String carNo = foundedCar.getCarNo();
            String date = foundedCar.getDate();
            String time = foundedCar.getTime();
            String address = foundedCar.getAddress();

            String gpsUnitNumber = foundedCar.getGPSUnitNumber();

            mCarNoTV.setText(carNo);
            mCarDateTV.setText(date);
            mCarTimeTV.setText(time);
            mCarAddressTV.setText(address);
            mCarGPSUnitNumberTV.setText(gpsUnitNumber);

            handleBottomSheetStatus(foundedCar);
            String speed = foundedCar.getSpeed();
            speed = speed.concat(" كم/ساعة");
            mCarSpeedTV.setText(speed);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleBottomSheetStatus(CarStatus foundedCar) {
        if (foundedCar.getStatus().matches("Stopped")) {
            carDetailsToolbar.setBackgroundResource(R.color.color_dark_solid_red);
            mBottomSheetCarDetailsTitleTV.setText(getString(R.string.stopped));
        } else if (foundedCar.getStatus().matches("Moving")) {
            carDetailsToolbar.setBackgroundResource(R.color.color_dark_green);
            mBottomSheetCarDetailsTitleTV.setText(getString(R.string.moving));
        } else if (foundedCar.getStatus().matches("Disconnected")) {
            carDetailsToolbar.setBackgroundResource(R.color.color_orange_light);
            mBottomSheetCarDetailsTitleTV.setText(getString(R.string.disconnected));
        } else {
            carDetailsToolbar.setBackgroundResource(R.color.blue);
            mBottomSheetCarDetailsTitleTV.setText(getString(R.string.disabled));
        }

    }

    private String getCarNo(String carNo) {
        String result = "";
        try {

            result = carNo.substring(7);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private double getDistance(Point endPoint, Point pointOnMap) {
        double distance = 0;

        if (endPoint != null) {
            Point selectedPoint = new Point(pointOnMap.getX(), pointOnMap.getY(), mapView.getSpatialReference());
            Point mEndPoint = new Point(endPoint.getX(), endPoint.getY(), mapView.getSpatialReference());

            // Create a world equidistant cylindrical spatial reference for measuring planar distance.

            // Project the points from geographic to the projected coordinate system.
            Point startP = (Point) GeometryEngine.project(selectedPoint, mapView.getSpatialReference());
            Point endP = (Point) GeometryEngine.project(mEndPoint, mapView.getSpatialReference());

            // Get the planar distance between the points in the spatial reference unit (meters).
            distance = GeometryEngine.distanceBetween(startP, endP);
            // Result = 7,372,671.29511302 (around 7,372.67 kilometers)

//            Log.i(TAG, "getDistance(): Start Point X = " + startP.getX() + " Start Point Y = " + startP.getY());
//            Log.i(TAG, "getDistance(): End Point X = " + endP.getX() + " Enf Point Y = " + endP.getY());
//            Log.i(TAG, "getDistance(): distance = " + distance);
        }
        return distance;
    }

    @Override
    public void onClick(View v) {
        try {
            if (v.equals(mBottomSheetCloseIcon)) {
                hideCarDetailsBottomSheet();
            } else if (mLocationFab.equals(v)) {
                zoomToPoint(mCurrentLocation);
            } else if (mMovingReportBottomSheetAction.equals(v)) {
                displayMovingReportFilter();
            } else if (movingFilterCloseIV.equals(v)) {
                cancelMovingFilter();
            } else if (movingFilterSaveIV.equals(v)) {
                SaveMovingFilter();
            } else if (movingFilterDateET.equals(v)) {
                displayDateRangePicker(v);
            } else if (movingFilterTimeFromET.equals(v)) {
                displayTimeRangePicker(movingFilterTimeFromET);
            } else if (movingFilterTimeToET.equals(v)) {
                displayTimeRangePicker(movingFilterTimeToET);
            } else if (mNavDrawerIc.equals(v)) {
                handleNavigation();
            } else if (mBottomSheetCollapseExpandIcon.equals(v)) {
                handleBottomSheetExpandCollapse();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleBottomSheetExpandCollapse() {
        try {
            Log.i(TAG, "handleBottomSheetExpandCollapse: is called");
            if (sheetBehavior.getState() == STATE_EXPANDED || sheetBehavior.getState() == STATE_HALF_EXPANDED) {
                Log.i(TAG, "handleBottomSheetExpandCollapse: is STATE_HALF_EXPANDED");
                sheetBehavior.setState(STATE_COLLAPSED);
                mBottomSheetCollapseExpandIcon.setImageResource(R.drawable.ic_keyboard_arrow_up_white_24dp);
            } else if (sheetBehavior.getState() == STATE_COLLAPSED) {
                Log.i(TAG, "handleBottomSheetExpandCollapse: is STATE_COLLAPSED");
                sheetBehavior.setState(STATE_HALF_EXPANDED);
                mBottomSheetCollapseExpandIcon.setImageResource(R.drawable.ic_keyboard_arrow_down_white_24dp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleNavigation() {
        if (mActivityListener != null) {
            mActivityListener.navDrawIconPressed();
        }
    }

    private void displayHomeLayout() {
        try {
            rootViewAnimator.setDisplayedChild(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideCarDetailsBottomSheet() {
        try {

            show(mLocationFab);
            hide(mBottomSheetLayout);

            if (drawGraphicLayer.getGraphics() != null && drawGraphicLayer.getGraphics().size() > 0) {
                drawGraphicLayer.getGraphics().clear();

                resetSpeedList();
                displayCarsOnMap(mStatusRoot);
            }
//            if (sheetBehavior != null) {
//                sheetBehavior.setState(STATE_COLLAPSED);
//                mBottomSheetLayout.setBackgroundColor(mCurrent.getResources().getColor(R.color.transparent));
//                sheetBehavior.setPeekHeight(homeOptionPeekHeight, true);
//                mBottomSheetViewAnimator.setDisplayedChild(1);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hide(View view) {
        view.setVisibility(View.GONE);
    }

    private void show(View view) {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * ---------------------------------Moving Report Filters--------------------------------
     */

    private void displayTimeRangePicker(TextInputEditText editText) {
        try {
            MaterialDialog dateDialog = AppUtils.showAlertDialogWithCustomView(mCurrent, R.layout.time_range_picker);
            TimePicker timePicker = (TimePicker) dateDialog.findViewById(R.id.time_picker_range);

            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    String status = "am", hoursString = "", minuteString = "";

                    int mHourOfDay = hourOfDay;
                    int mMinute = minute;


                    Log.i(TAG, "onTimeChanged: hours = " + mHourOfDay);
                    if (mHourOfDay >= 12) {
                        status = "pm";
                        mHourOfDay = mHourOfDay - 12;
                        Log.i(TAG, "onTimeChanged: hours of day > 12 = " + mHourOfDay);
                    }
                    Log.i(TAG, "onTimeChanged: hours of day #1 = " + mHourOfDay);

                    if (mHourOfDay == 0) {
                        mHourOfDay = 12;
                    }
                    Log.i(TAG, "onTimeChanged: hours of day #2 = " + mHourOfDay);

                    hoursString = String.valueOf(mHourOfDay);
                    minuteString = String.valueOf(mMinute);

                    if (mHourOfDay < 10) {
                        hoursString = "0".concat(String.valueOf(mHourOfDay));
                    }
                    Log.i(TAG, "onTimeChanged: hours of day #3 = " + mHourOfDay);

                    if (mMinute < 10) {
                        minuteString = "0".concat(String.valueOf(mMinute));
                    }
                    Log.i(TAG, "onTimeChanged: hours of day #4 = " + mHourOfDay);

                    Log.i(TAG, "onTimeChanged: hoursString = " + hoursString + " - minuteString = " + minuteString);


                    String time = hoursString.concat(":").concat(minuteString).concat(status);
                    Log.i(TAG, "onTimeChanged: time = " + time);

                    editText.setText(time);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayDateRangePicker(View v) {
        try {
            MaterialDialog dateDialog = AppUtils.showAlertDialogWithCustomView(mCurrent, R.layout.date_range_picker);
            DateRangeCalendarView calendar = (DateRangeCalendarView) dateDialog.findViewById(R.id.calendar);
            calendar.setCalendarListener(new DateRangeCalendarView.CalendarListener() {
                @Override
                public void onFirstDateSelected(@NonNull Calendar startDate) {
                    String start = startDate.get(Calendar.DAY_OF_MONTH) + "/" + (startDate.get(Calendar.MONTH) + 1) + "/" + startDate.get(Calendar.YEAR);
                    movingFilterDateET.setText(start);

                }

                @Override
                public void onDateRangeSelected(@NonNull Calendar startDate, @NonNull Calendar endDate) {
                    String start = startDate.get(Calendar.DAY_OF_MONTH) + "/" + (endDate.get(Calendar.MONTH) + 1) + "/" + startDate.get(Calendar.YEAR);
                    String end = endDate.get(Calendar.DAY_OF_MONTH) + "/" + (endDate.get(Calendar.MONTH) + 1) + "/" + endDate.get(Calendar.YEAR);
                    String all = start.concat(" - ").concat(end);

                    movingFilterDateET.setText(all);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cancelMovingFilter() {
        try {

            movingFilterDateET.clearComposingText();
            movingFilterTimeFromET.clearComposingText();
            movingFilterTimeToET.clearComposingText();

            rootViewAnimator.setDisplayedChild(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SaveMovingFilter() {
        try {
            if (movingFilterDateET.getText() == null || movingFilterDateET.getText().toString().trim().isEmpty()) {
                movingFilterDateET.setError(getString(R.string.please_enter_date_range));
            } else if (movingFilterTimeFromET.getText() == null || movingFilterTimeFromET.getText().toString().trim().isEmpty()) {
                movingFilterTimeFromET.setError(getString(R.string.please_enter_time_range));
            } else if (movingFilterTimeToET.getText() == null || movingFilterTimeToET.getText().toString().trim().isEmpty()) {
                movingFilterTimeToET.setError(getString(R.string.please_enter_time_range));
            } else {

                FindTripRequest findTripRequest = getFindTripRequest();
                showLoading();
                presenter.requestFindTrip(findTripRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private FindTripRequest getFindTripRequest() {

        FindTripRequest findTripRequest = new FindTripRequest();

        try {
            String selectedDateRange = movingFilterDateET.getText().toString().trim();
            String fromDate = presenter.getFromDate(selectedDateRange);
            String toDate = presenter.getToDate(selectedDateRange);
            String fromTime = movingFilterTimeFromET.getText().toString().trim().replaceAll(":", "");
            String toTime = movingFilterTimeToET.getText().toString().trim().replaceAll(":", "");
            String token = presenter.getToken();
            String gpsUnit = selectedCar.getGPSUnitNumber();
            int speedFrom = -1;
            int speedTo = -1;

            if (mSpeedFromET.getText() != null && !mSpeedFromET.getText().toString().trim().isEmpty()) {
                speedFrom = Integer.parseInt(mSpeedFromET.getText().toString());
            }

            if (mSpeedToET.getText() != null && !mSpeedToET.getText().toString().trim().isEmpty()) {
                speedTo = Integer.parseInt(mSpeedToET.getText().toString());
            }
            speedFilter = new SpeedFilter(speedFrom, speedTo);

            findTripRequest.setToken(token);
            findTripRequest.setFromDate(fromDate);
            findTripRequest.setFromTime(fromTime);
            findTripRequest.setToDate(toDate);
            findTripRequest.setToTime(toTime);
            findTripRequest.setGpsUnitNo(gpsUnit);

            movingFilterDateET.clearComposingText();
            movingFilterTimeFromET.clearComposingText();
            movingFilterTimeToET.clearComposingText();
            logger.logFindTripRequest(token, gpsUnit, fromDate, toDate, fromTime, toTime, TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return findTripRequest;
    }

    private void showLoading() {
        mProgressDlg = AppUtils.showProgressDialog(mCurrent, mCurrent.getString(R.string.loading));
    }

    private void dismissLoading() {
        if (mProgressDlg != null) {
            mProgressDlg.dismiss();
        }
    }

    private void displayMovingReportFilter() {
        try {
            try {
                if (selectedCar != null && selectedCar.getCarNo() != null) {
                    movingFilterCarTitle.setText(selectedCar.getCarNo());
                }

                rootViewAnimator.setDisplayedChild(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFindTripSuccess(FindTrip findTrip) {
        try {
            Log.d(TAG, "onFindTripSuccess: is called");
            dismissLoading();
            rootViewAnimator.setDisplayedChild(0);
            if (findTrip.getFoundCars().getLocations() != null && !findTrip.getFoundCars().getLocations().isEmpty()) {
                presenter.sortPoints(findTrip, spatialReference, mapView.getSpatialReference());
            } else {
                AppUtils.showToast(mCurrent, getString(R.string.no_history_found));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFindTripFailure(Throwable throwable) {
        rootViewAnimator.setDisplayedChild(0);
        dismissLoading();
        throwable.printStackTrace();
        AppUtils.showToast(mCurrent, getString(R.string.no_history_found));

    }

    @Override
    public void onHereRoutSuccess(ArrayList<Maneuver> positionsResult) {

        try {
            Log.i(TAG, "onHereRoutSuccess: is called");
//            rootViewAnimator.setDisplayedChild(0);
            dismissLoading();

            if (positionsResult != null && !positionsResult.isEmpty()) {
                Log.i(TAG, "onHereRoutSuccess: hPosition != null");
                PointCollection pointCollection = new PointCollection(mapView.getSpatialReference());
                drawGraphicLayer.getGraphics().clear();
                graphicsOverlay.getGraphics().clear(); // TODO NOT Sure What To Do

                for (Maneuver maneuver : positionsResult) {

                    Log.i(TAG, "onHereRoutSuccess: position lat = " + maneuver.getPosition().getLatitude() + " - long = " + maneuver.getPosition().getLongitude());

                    Point mPoint = new Point(maneuver.getPosition().getLongitude(), maneuver.getPosition().getLatitude(), spatialReference);
                    Point point = (Point) GeometryEngine.project(mPoint, mapView.getSpatialReference());
                    pointCollection.add(point);

                }

                // create a new point collection for polyline
                Polyline polyline = new Polyline(pointCollection);
                //define a line symbol
                SimpleLineSymbol lineSymbol =
                        new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.argb(255, 0, 60, 143), 2.0f);
                Graphic line = new Graphic(polyline, lineSymbol);
                drawGraphicLayer.getGraphics().add(line);
//                    zoomToLine(line);

            } else {
                Log.i(TAG, "onHereRoutSuccess: hPosition = null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onHereRoutFailure(Throwable throwable) {
        dismissLoading();
        throwable.printStackTrace();
    }

    @Override
    public void onSortPoints(FindTrip findTrip) {
        try {
            Log.d(TAG, "onSortPoints: is called");
            if (mCurrent != null) {
                Log.d(TAG, "onSortPoints: mCurrent not null");
                mCurrent.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        findTripResponse = findTrip;
                        Log.d(TAG, "run: calling drawFindTripLine method");
                        drawFindTripLine(findTrip);
                    }
                });
            } else {
                Log.d(TAG, "onSortPoints: mCurrent is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCarSelected(CarStatus car) {
        try {
            if (cars != null && !cars.isEmpty()) {
                Point foundedCar = null;
                Log.i(TAG, "selectCar: cars size = " + cars.size());
                Point mProjectCarPoint = (Point) GeometryEngine.project(new Point(car.getLongitude(), car.getLatitude(), spatialReference), mapView.getSpatialReference());

                for (Point carPoint : carsPoints) {
                    Log.i(TAG, "selectCar: mProjectCarPoint X = " + mProjectCarPoint.getX() + " Y = " + mProjectCarPoint.getY());

                    if (mProjectCarPoint.getX() == carPoint.getX() && mProjectCarPoint.getY() == carPoint.getY()) {
                        foundedCar = carPoint;
                        Log.i(TAG, "selectCar: car founded with id = " + car.getCarID() + " car No = " + car.getCarNo());
                        zoomToPoint(foundedCar);
                        displayBottomSheet(car);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSpeedSelected(int position, String speed, Point point) {
        try {
            zoomToPoint(point);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startTaskRout(FindTrip findTrip) {
        try {
//            RouteTask task = new RouteTask(mCurrent, "http://route.arcgis.com/arcgis/rest/services/World/Route/NAServer");
            RouteTask task = new RouteTask(mCurrent, "http://sampleserver6.arcgisonline.com/arcgis/rest/services/NetworkAnalysis/SanDiego/NAServer/Route");
            task.loadAsync();
            task.addLoadStatusChangedListener(new LoadStatusChangedListener() {
                @Override
                public void loadStatusChanged(LoadStatusChangedEvent loadStatusChangedEvent) {
                    if (task.getLoadError() == null && task.getLoadStatus() == LoadStatus.LOADED) {
                        Log.i(TAG, "loadStatusChanged: task is loaded");
                    } else {
                        Log.i(TAG, "loadStatusChanged: task is not loaded");
                    }
                }
            });

            // get default route parameters
            RouteParameters routeParameters = task.createDefaultParametersAsync().get();
            // set flags to return stops and directions
            routeParameters.setReturnStops(true);

            Point stop1Loc = new Point(-1.3018598562659847E7, 3863191.8817135547, mapView.getSpatialReference());
            Point stop2Loc = new Point(-1.3036911787723785E7, 3839935.706521739, mapView.getSpatialReference());
            routeParameters.setStops(Arrays.asList(new Stop(stop1Loc), new Stop(stop2Loc)));
            // create barriers
            PointBarrier pointBarrier = new PointBarrier(new Point(-1.302759917994629E7, 3853256.753745117, mapView.getSpatialReference()));
            // add barriers to routeParameters
            routeParameters.setPointBarriers(Arrays.asList(pointBarrier));

            try {
                RouteResult result = task.solveRouteAsync(routeParameters).get();
                result.getPolylineBarriers().get(0).getGeometry();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawFindTripLine(FindTrip findTrip) {
        try {
            Log.d(TAG, "drawFindTripLine: is called");

            PointCollection pointCollection = new PointCollection(mapView.getSpatialReference());
            drawGraphicLayer.getGraphics().clear();
            graphicsOverlay.getGraphics().clear(); // TODO NOT Sure What To Do
            for (CarStatus location : findTrip.getFoundCars().getLocations()) {
                Point point = (Point) GeometryEngine.project(new Point(location.getLongitude(), location.getLatitude(), spatialReference), mapView.getSpatialReference());
                pointCollection.add(point);
            }

            if (pointCollection.size() > 1) {
                drawGraphicLayer.getGraphics().add(new Graphic(pointCollection.get(0), startBannerMarker));
                drawGraphicLayer.getGraphics().add(new Graphic(pointCollection.get(pointCollection.size() - 1), endBannerMarker));
            }

            displaySpeedList(pointCollection, findTrip);

            Polyline polyline = new Polyline(pointCollection);

            //define a line symbol
            SimpleLineSymbol lineSymbol =
                    new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.argb(255, 0, 60, 143), 4.0f);

            lineSymbol.setAntiAlias(true);

            Graphic line = new Graphic(polyline, lineSymbol);

            drawGraphicLayer.getGraphics().add(line);

            zoomToLine(line);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void zoomToLine(Graphic line) {
        try {
            mapView.setViewpoint(new Viewpoint(line.getGeometry().getExtent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * -------------------------------------Speed Filter----------------------------------------
     */

    private void displaySpeedList(PointCollection pointCollection, FindTrip findTrip) {
        try {
            Log.d(TAG, "displaySpeedList: is called");
            if (findTrip.getFoundCars().getLocations() != null) {
                if (speedFilter != null && speedFilter.getSpeedFrom() == -1 && speedFilter.getSpeedTo() == -1) {
                    handleWithoutSpeed(pointCollection, findTrip);

                } else if (speedFilter != null && speedFilter.getSpeedFrom() != -1 && speedFilter.getSpeedTo() == -1) {
                    handleSpeedFrom(pointCollection, findTrip);
                } else if (speedFilter != null && speedFilter.getSpeedFrom() == -1 && speedFilter.getSpeedTo() != -1) {
                    handleSpeedTo(pointCollection, findTrip);
                } else {
                    handleSpeedFromTo(pointCollection, findTrip);
                }
                Log.d(TAG, "displaySpeedList: speed size = " + speedRecData.size());
                if (!speedRecData.isEmpty()) {
                    mSpeedRV.setVisibility(View.VISIBLE);
                    mSpeedAdapter.dataChanged(speedRecPoint, speedRecData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleWithoutSpeed(PointCollection pointCollection, FindTrip findTrip) {
        try {
            Log.d(TAG, "handleWithoutSpeed: is called");
            speedRecData = findTrip.getFoundCars().getLocations();
            speedRecPoint = new PointCollection(pointCollection.getSpatialReference());
            speedRecPoint.addAll(pointCollection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleSpeedFromTo(PointCollection pointCollection, FindTrip findTrip) {
        try {
            Log.d(TAG, "handleSpeedFromTo: is called");

            speedRecData = new ArrayList<>();
            speedRecPoint = new PointCollection(pointCollection.getSpatialReference());

            for (int i = 0; i < findTrip.getFoundCars().getLocations().size(); i++) {
                CarStatus location = findTrip.getFoundCars().getLocations().get(i);
                if (Integer.parseInt(location.getSpeed2()) <= speedFilter.getSpeedTo() && Integer.parseInt(location.getSpeed2()) >= speedFilter.getSpeedFrom()) {
                    Log.d(TAG, "handleSpeedFromTo: location speed = " + location.getSpeed2());
                    speedRecData.add(location);
                    speedRecPoint.add(pointCollection.get(i));
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void handleSpeedTo(PointCollection pointCollection, FindTrip findTrip) {
        try {
            Log.d(TAG, "handleSpeedTo: is called");
            speedRecData = new ArrayList<>();
            speedRecPoint = new PointCollection(pointCollection.getSpatialReference());

            for (int i = 0; i < findTrip.getFoundCars().getLocations().size(); i++) {
                CarStatus location = findTrip.getFoundCars().getLocations().get(i);
                if (Integer.parseInt(location.getSpeed2()) <= speedFilter.getSpeedTo()) {
                    Log.d(TAG, "handleSpeedTo: location speed = " + location.getSpeed2());
                    speedRecData.add(location);
                    speedRecPoint.add(pointCollection.get(i));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void handleSpeedFrom(PointCollection pointCollection, FindTrip findTrip) {
        try {
            Log.d(TAG, "handleSpeedFrom: is called");
            speedRecData = new ArrayList<>();
            speedRecPoint = new PointCollection(pointCollection.getSpatialReference());

            for (int i = 0; i < findTrip.getFoundCars().getLocations().size(); i++) {
                CarStatus location = findTrip.getFoundCars().getLocations().get(i);
                if (Integer.parseInt(location.getSpeed2()) >= speedFilter.getSpeedFrom()) {
                    Log.d(TAG, "handleSpeedFrom: location speed = " + location.getSpeed2());
                    speedRecData.add(location);
                    speedRecPoint.add(pointCollection.get(i));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void resetSpeedList() {
        if (mSpeedRV.getVisibility() == View.VISIBLE) {
            speedRecData.clear();
            speedRecPoint.clear();
            mSpeedRV.setVisibility(View.GONE);
        }

    }

    /**
     * ---------------------------------Search Listeners--------------------------------------
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i(TAG, "onQueryTextSubmit: newTextChanged = " + query);
        CarStatus car = presenter.getCar(cars, query);
        if (car != null) {
            Point mProjectCarPoint = (Point) GeometryEngine.project(new Point(car.getLongitude(), car.getLatitude(), spatialReference), mapView.getSpatialReference());
            Log.i(TAG, "selectCar: mProjectCarPoint X = " + mProjectCarPoint.getX() + " Y = " + mProjectCarPoint.getY());
            zoomToPoint(mProjectCarPoint);
            displayBottomSheet(car);
        } else {
            AppUtils.showToast(mCurrent, "لا يوجد سيار مسجلة بهذا الرقم");
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i(TAG, "onQueryTextChange: newTextChanged = " + newText);
        return false;
    }

    /**
     * ------------------------------Map Scale Changed Listener---------------------------------
     */
    @Override
    public void mapScaleChanged(MapScaleChangedEvent mapScaleChangedEvent) {
//        if (drawGraphicLayer.getGraphics() == null || drawGraphicLayer.getGraphics().size() == 0) {
//            if (mapScaleChangedEvent.getSource().getMapScale() >= 94249 && cars != null) {
//                Log.i(TAG, "mapScaleChanged: Zooming Out");
//                graphicsOverlay.clearSelection();
//                graphicsOverlay.getGraphics().clear();
//
//
//                for (CarStatus car : cars) {
//
//                    Point point = (Point) GeometryEngine.project(new Point(car.getLongitude(), car.getLatitude(), spatialReference), mapView.getSpatialReference());
//                    Graphic carGraphic = null;
//
//
//                    if (car.getStatus().equals("Moving")) {
//                        greenMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.green));
//                        greenMarker.setWidth(18f);
//                        greenMarker.setHeight(36f);
//                        greenMarker.setAngle((float) car.getAngle());
//                        carGraphic = new Graphic(point, greenMarker);
//                    } else if (car.getStatus().equals("Stopped")) {
//                        redMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.reed));
//                        redMarker.setWidth(18f);
//                        redMarker.setHeight(36f);
//                        redMarker.setAngle((float) car.getAngle());
//                        carGraphic = new Graphic(point, redMarker);
//                    } else if (car.getStatus().equals("Disconnected")) {
//                        yellowMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.yellow));
//                        yellowMarker.setWidth(18f);
//                        yellowMarker.setHeight(36f);
//                        yellowMarker.setAngle((float) car.getAngle());
//                        carGraphic = new Graphic(point, yellowMarker);
//                    }else if (car.getStatus().equals("Disabled")) {
//                        blueMarker = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.drawable.blue));
//                        blueMarker.setWidth(18f);
//                        blueMarker.setHeight(36f);
//                        blueMarker.setAngle((float) car.getAngle());
//                        carGraphic = new Graphic(point, blueMarker);
//                    }
//                    graphicsOverlay.getGraphics().add(carGraphic);
//                }
//            } else if (mapScaleChangedEvent.getSource().getMapScale() < 94249 && cars != null) {
//                Log.i(TAG, "mapScaleChanged: Zooming In");
//                graphicsOverlay.clearSelection();
//                graphicsOverlay.getGraphics().clear();
//
//                for (CarStatus car : cars) {
//
//                    Point point = (Point) GeometryEngine.project(new Point(car.getLongitude(), car.getLatitude(), spatialReference), mapView.getSpatialReference());
//                    Graphic carGraphic = null;
//
//                    if (car.getStatus().equals("Moving")) {
//                        greenMarker.setWidth(32f);
//                        greenMarker.setHeight(64f);
//                        carGraphic = new Graphic(point, greenMarker);
//                    } else if (car.getStatus().equals("Stopped")) {
//                        redMarker.setWidth(32f);
//                        redMarker.setHeight(64f);
//                        carGraphic = new Graphic(point, redMarker);
//                    } else if (car.getStatus().equals("Disconnected") || car.getStatus().equals("Disabled")) {
//                        yellowMarker.setWidth(32f);
//                        yellowMarker.setHeight(64f);
//                        carGraphic = new Graphic(point, yellowMarker);
//                    }
//                    graphicsOverlay.getGraphics().add(carGraphic);
//                }
//            }
//        }
    }

    /**
     * ---------------------------------Notification----------------------------------------------
     */


    @Override
    public void onNotifyCarsDisconnected(int count) {
        try {
            if (notificationCount.getText() != null && !notificationCount.getText().toString().isEmpty())
                count += Integer.parseInt(notificationCount.getText().toString());

            notificationCount.setText(String.valueOf(count));

            notificationCount.setVisibility(View.VISIBLE);
            if (count > 99) {
                count = 99;
            }
            if (count == 99) {
                notificationCount.setText(String.valueOf(count).concat("+"));
            } else {
                notificationCount.setText(String.valueOf(count));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}