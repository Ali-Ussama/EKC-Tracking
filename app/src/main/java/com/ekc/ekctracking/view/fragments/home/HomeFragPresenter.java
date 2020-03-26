package com.ekc.ekctracking.view.fragments.home;

import android.location.Location;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.ekc.ekctracking.models.findTrip.FindTrip;
import com.ekc.ekctracking.models.findTrip.FindTripRequest;
import com.ekc.ekctracking.models.hereMapRoutModel.Maneuver;
import com.ekc.ekctracking.models.homeFragmentModels.CarUtils;
import com.ekc.ekctracking.models.homeFragmentModels.HomeFragPresenterListener;
import com.ekc.ekctracking.models.pojo.CarStatus;
import com.ekc.ekctracking.models.pojo.StatusRoot;
import com.ekc.ekctracking.view.activities.mainActivity.MainActivity;
import com.ekc.ekctracking.view.fragments.home.callbacks.HomeActivityCallback;
import com.ekc.ekctracking.view.fragments.home.callbacks.HomeViewCallback;
import com.esri.arcgisruntime.geometry.SpatialReference;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomeFragPresenter extends ViewModel implements HomeActivityCallback, HomeFragPresenterListener {

    private static final String TAG = "HomeFragPresenter";
    private MainActivity mContext;
    private HomeViewCallback viewListener;
    private CarUtils carUtils;

    HomeFragPresenter(MainActivity context, HomeViewCallback callback) {
        try {
            mContext = context;
            viewListener = callback;
            mContext.setLocationChangeListener(this);
            carUtils = new CarUtils(context, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            if (viewListener != null) {
                Log.i(TAG, "onLocationChanged(): mHomeViewCallback is not null");
                viewListener.onLocationChanged(location);
            } else {
                Log.i(TAG, "onLocationChanged(): mHomeViewCallback is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void requestToken() {
        try {
            Log.d(TAG, "requestToken: is called");
            carUtils.requestToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void requestOnGoingCarsStatus() {
        try {
            Log.d(TAG, "requestOnGoingCarsStatus: is called");
            ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(5);

            // This schedule a runnable task every 2 minutes
            scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    Log.i(TAG, "run: calling requestOnGoingCarsStatus");
                    carUtils.requestOnGoingCarsStatus();
                }
            }, 0, 30, TimeUnit.SECONDS);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String reArrangeAddress(String address) {
        return carUtils.reArrangeAddress(address);
    }

    CarStatus getCar(ArrayList<CarStatus> cars, String filter) {
        return carUtils.getCar(cars, filter);
    }

    String getFromDate(String selectedDateRange) {
        return carUtils.getFromDate(selectedDateRange);
    }

    String getToDate(String selectedDateRange) {
        return carUtils.getToDate(selectedDateRange);
    }

    String getToken() {
        return carUtils.getToken();
    }

    void requestFindTrip(FindTripRequest findTripRequest) {
        try {
            carUtils.requestFindTrip(findTripRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void sortPoints(FindTrip findTrip, SpatialReference decimalSR, SpatialReference mapSpatialReference) {
        try {
            Log.i(TAG, "sortPoints: is called");
            carUtils.sortPoints(findTrip, decimalSR, mapSpatialReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ArrayList<CarStatus> calcAngle(ArrayList<CarStatus> newCars, ArrayList<CarStatus> oldCars) {
        return carUtils.calcAngle(newCars, oldCars);
    }

    @Override
    public void onOnGoingStatus(StatusRoot statusRoot) {
        try {
            if (viewListener != null) {
                viewListener.onOnGoingStatus(statusRoot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFindTripSuccess(FindTrip findTrip) {
        try {
            if (viewListener != null) {
                viewListener.onFindTripSuccess(findTrip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFindTripFailure(Throwable throwable) {
        try {
            if (viewListener != null) {
                viewListener.onFindTripFailure(throwable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onHereRoutSuccess(ArrayList<Maneuver> positionsResult) {
        try {
            if (viewListener != null) {
                viewListener.onHereRoutSuccess(positionsResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onHereRoutFailure(Throwable throwable) {
        try {
            if (viewListener != null) {
                viewListener.onHereRoutFailure(throwable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSortPoint(FindTrip findTrip) {
        try {
            if (viewListener != null) {
                viewListener.onSortPoints(findTrip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}