package com.ekc.ekctracking.view.fragments.home;

import android.location.Location;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.ekc.ekctracking.R;
import com.ekc.ekctracking.api_network.ApiClient;
import com.ekc.ekctracking.configs.PrefManager;
import com.ekc.ekctracking.models.CarGroupStatus;
import com.ekc.ekctracking.models.CarStatus;
import com.ekc.ekctracking.models.LoginModel;
import com.ekc.ekctracking.models.OnGoingRequest;
import com.ekc.ekctracking.models.StatusRoot;
import com.ekc.ekctracking.models.User;
import com.ekc.ekctracking.models.findTrip.FindTrip;
import com.ekc.ekctracking.models.findTrip.FindTripRequest;
import com.ekc.ekctracking.models.hereMapRoutModel.HRoute;
import com.ekc.ekctracking.models.hereMapRoutModel.HereRoute;
import com.ekc.ekctracking.models.hereMapRoutModel.Maneuver;
import com.ekc.ekctracking.view.activities.mainActivity.MainActivity;
import com.ekc.ekctracking.view.fragments.home.callbacks.HomeActivityCallback;
import com.ekc.ekctracking.view.fragments.home.callbacks.HomeViewCallback;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.SpatialReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeFragPresenter extends ViewModel implements HomeActivityCallback {

    private static final String TAG = "HomeFragPresenter";
    private MainActivity mContext;
    private HomeViewCallback viewListener;
    private HomeFragment mHomeFragment;
    private ApiClient mApiClient;
    private PrefManager mPrefManager;

    public HomeFragPresenter(MainActivity context, HomeViewCallback callback, PrefManager mPrefManager) {
        try {
            mContext = context;
            viewListener = callback;
            mContext.setLocationChangeListener(this);
            mApiClient = new ApiClient();
            this.mPrefManager = mPrefManager;

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
            Log.i(TAG, "requestToken: is called");
            LoginModel loginModel = new LoginModel();
            loginModel.setGrant_type("password");
////            loginModel.setPassword("01122158170");
////            loginModel.setUsername("bait");
//            loginModel.setPassword("01122158170");
//            loginModel.setUsername("bait");

            loginModel.setUsername(mPrefManager.readString(PrefManager.KEY_USERNAME));
            loginModel.setPassword(mPrefManager.readString(PrefManager.KEY_PASSWORD));

            mApiClient.login(loginModel, new ApiClient.CommonCallback<Object>() {
                @Override
                public void onSuccess(Object response) {
                    if (response instanceof User) {
                        User user = (User) response;

                        if (mPrefManager != null) {
                            mPrefManager.saveString(PrefManager.KEY_TOKEN, user.getToken());
                            mPrefManager.saveString(PrefManager.KEY_TOKEN_TYPE, user.getToken_type());
                            mPrefManager.saveString(PrefManager.KEY_EXPIRES_IN, user.getToken());
                            mPrefManager.saveString(PrefManager.KEY_ISSUED, user.getToken());
                            mPrefManager.saveString(PrefManager.KEY_USERNAME, user.getUsername());
                            requestOnGoingCarsStatus();
                        }
                    }
                }

                @Override
                public void onFailure(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void requestOnGoingCarsStatus() {
        try {
            OnGoingRequest onGoingRequest = new OnGoingRequest();

            String auth = "";
            if (mPrefManager != null) {
                String token_type = mPrefManager.readString(PrefManager.KEY_TOKEN_TYPE);
                String token = mPrefManager.readString(PrefManager.KEY_TOKEN);

                auth = token_type.concat(" ").concat(token);

                onGoingRequest.setAuthorization(auth);

                if (mApiClient != null) {
                    mApiClient.getOnGoingStatus(onGoingRequest, new ApiClient.CommonCallback<Object>() {
                        @Override
                        public void onSuccess(Object response) {
                            try {
                                if (response instanceof StatusRoot) {
                                    StatusRoot statusRoot = (StatusRoot) response;
                                    Log.i(TAG, "requestOnGoingCarsStatus(): statusRoot != null");
                                    for (CarGroupStatus carGroupStatus : statusRoot.getRoot().getCarGroupStatus()) {

                                        Log.i(TAG, "requestOnGoingCarsStatus(): Client Name = " + carGroupStatus.getClientName());
                                        for (CarStatus car : carGroupStatus.getCars()) {
                                            Log.i(TAG, "requestOnGoingCarsStatus(): lat = " + car.getLatitude());
                                            Log.i(TAG, "requestOnGoingCarsStatus(): lang = " + car.getLongitude());
                                            Log.i(TAG, "requestOnGoingCarsStatus(): Car No = " + car.getCarNo());
                                            Log.i(TAG, "requestOnGoingCarsStatus(): Date = " + car.getDate());
                                            Log.i(TAG, "requestOnGoingCarsStatus(): Time = " + car.getTime());

                                            Log.i(TAG, "requestOnGoingCarsStatus: address = " + reArrangeAddress(car.getAddress()));
                                            car.setAddress(reArrangeAddress(car.getAddress()));
                                        }
                                    }

                                    viewListener.onOnGoingStatus(statusRoot);
                                } else {
                                    Log.i(TAG, "requestOnGoingCarsStatus(): statusRoot = null");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            try {
                                throwable.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String reArrangeAddress(String address) {
        String mAddress = "";

        if (address != null) {
            mAddress = address.replaceAll("(Street:)|(Unnamed)|(Road)|(District:)|(Country:)|(<br />\r\n|:|/)", "");
            mAddress = mAddress.replaceAll("(Area)", " - ");

        }
        return mAddress;
    }

    public CarStatus getCar(ArrayList<CarStatus> cars, String filter) {

        for (CarStatus car : cars) {
            String carNo = car.getCarNo().toLowerCase().split("(car no:)")[1];
            if (car.getGPSUnitNumber().equals(filter) || carNo.contains(filter)) {
                return car;
            }
        }
        return null;
    }

    public String getFromDate(String selectedDateRange) {
        String result = "";

        try {
            String[] split_1 = selectedDateRange.split("-");
            if (split_1.length > 0) {
                result = split_1[0].replace("/", "-");
            } else {
                result = selectedDateRange.replace("/", "-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getToDate(String selectedDateRange) {
        String result = "";
        try {
            String[] split_1 = selectedDateRange.split("-");
            if (split_1.length > 1) {
                result = split_1[1].replace("/", "-");
            } else if (split_1.length == 1) {
                result = split_1[0].replace("/", "-");
            } else {
                result = selectedDateRange.replace("/", "-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getToken() {
        String result = null;
        if (mPrefManager != null) {
            result = mPrefManager.readString(PrefManager.KEY_TOKEN_TYPE) + " " + mPrefManager.readString(PrefManager.KEY_TOKEN);
        }
        return result;
    }

    public void requestFindTrip(FindTripRequest findTripRequest) {
        try {
            mApiClient.getFindTrip(findTripRequest, new ApiClient.CommonCallback<Object>() {
                @Override
                public void onSuccess(Object response) {
                    try {
                        Log.d(TAG, "requestFindTrip(): onSuccess: is called");
                        if (response instanceof FindTrip) {
                            FindTrip result = (FindTrip) response;
//                            result = createDateTime(result);
                            if (viewListener != null) {
                                Log.d(TAG, "onSuccess: viewListener != null");
                                viewListener.onFindTripSuccess(result);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable throwable) {
                    if (viewListener != null) {
                        viewListener.onFindTripFailure(throwable);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private FindTrip createDateTime(FindTrip result) {
        try {
            Log.i(TAG, "createDateTime: is called");
            if (result != null && result.getFoundCars() != null && result.getFoundCars().getLocations() != null && !result.getFoundCars().getLocations().isEmpty()) {
                for (CarStatus location : result.getFoundCars().getLocations()) {

                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
                        String dateTime = location.getDate().replace("/", "-") + " " + location.getTime();
                        dateTime = dateFormat.parse(dateTime).toString();
                        location.setDataTime(dateTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void requestHereRout(FindTrip findTripResponse) {
        final int[] resultCount = {0};

//        for (int i = 0; i < findTripResponse.getFoundCars().getLocations().size() - 1; i++) {
        CarStatus currentPoint = findTripResponse.getFoundCars().getLocations().get(0);
        CarStatus nextPoint = findTripResponse.getFoundCars().getLocations().get(findTripResponse.getFoundCars().getLocations().size() - 1);

        String query = "";

//            query = "waypoint".concat(String.valueOf(i)).concat("=").concat(String.valueOf(currentPoint.getLatitude())).concat(",").concat(String.valueOf(currentPoint.getLongitude())).concat("&")
//                    .concat("waypoint").concat(String.valueOf(i)).concat("=").concat(String.valueOf(nextPoint.getLatitude())).concat(",").concat(String.valueOf(nextPoint.getLongitude()))
//                    .concat("&").concat("mode=fastest;car&app_id=").concat(mContext.getString(R.string.here_app_id)).concat("&app_code=").concat(mContext.getString(R.string.here_app_code));

        String wayPoint0 = String.valueOf(currentPoint.getLatitude()).concat(",").concat(String.valueOf(currentPoint.getLongitude()));
        String wayPoint1 = String.valueOf(nextPoint.getLatitude()).concat(",").concat(String.valueOf(nextPoint.getLongitude()));
        String mode = "fastest;car";
        String app_id = mContext.getString(R.string.here_app_id);
        String app_code = mContext.getString(R.string.here_app_code);

        if (mApiClient != null) {
            mApiClient.getHereRout(wayPoint0, wayPoint1, mode, app_id, app_code, new ApiClient.CommonCallback<Object>() {
                ArrayList<Maneuver> positions = new ArrayList<>();

                @Override
                public void onSuccess(Object response) {
                    try {
//                            if (response instanceof HRoute) {
                        Log.i(TAG, "requestHereRout: response instanceof HRoute");
                        resultCount[0]++;
                        HRoute response1 = (HRoute) response;
                        HereRoute hResponse = response1.getResponse();

                        for (int i1 = 0; i1 < hResponse.getRoute().size(); i1++) {
                            Log.i(TAG, "requestHereRout: hResponse.getRoute() index = " + i1);
                            for (int i2 = 0; i2 < hResponse.getRoute().size(); i2++) {

                                Log.i(TAG, "requestHereRout: route index = " + i2);
                                for (int i3 = 0; i3 < hResponse.getRoute().get(i2).getLeg().size(); i3++) {
                                    Log.i(TAG, "requestHereRout: leg index = " + i3);
                                    for (int i4 = 0; i4 < hResponse.getRoute().get(i2).getLeg().get(i3).getManeuver().size(); i4++) {
                                        Maneuver maneuver = hResponse.getRoute().get(i2).getLeg().get(i3).getManeuver().get(i4);
                                        Log.i(TAG, "requestHereRout: maneuver index = " + i4);
                                        Log.i(TAG, "requestHereRout: maneuver position lat = " + maneuver.getPosition().getLatitude());
                                        Log.i(TAG, "requestHereRout: maneuver position long = " + maneuver.getPosition().getLatitude());
                                        positions.add(hResponse.getRoute().get(0).getLeg().get(0).getManeuver().get(i4));
                                    }
                                }

                            }
                        }
//                                if (hResponse.getRoute() != null && hResponse.getRoute().get(0) != null) {
//                                    positions.add(hResponse.getRoute().get(0).getLeg().get(0).getManeuver().get(0));
//                                    Log.i(TAG, "requestHereRout: positions size = " + positions.size());
//                                }
                        Log.i(TAG, "onSuccess: resultCount[0] = " + resultCount[0]);
//                                if (resultCount[0] == (findTripResponse.getFoundCars().getLocations().size() - 1)) {
                        viewListener.onHereRoutSuccess(positions);
//                                }

//                            }else{
//                                Log.i(TAG, "requestHereRout: response not instanceof HRoute");
//
//                            }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable throwable) {
                    viewListener.onHereRoutFailure(throwable);

                }
            });
        }
//        }
    }

    public FindTrip sortPoints(FindTrip findTrip, SpatialReference decimalSR, SpatialReference mapSpatialReference) {
        try {
            Log.i(TAG, "sortPoints: is called");
            synchronized (this) {
                Collections.sort(findTrip.getFoundCars().getLocations(), new Comparator<CarStatus>() {
                    @Override
                    public int compare(CarStatus o1, CarStatus o2) {
                        return o1.getDate().compareTo(o2.getDate()) & o1.getTime().compareTo(o2.getTime());
                    }
                });

                ArrayList<CarStatus> location = findTrip.getFoundCars().getLocations();
                ArrayList<CarStatus> wrongPoints = new ArrayList<>();
                double distance = 0;
                for (int i = 0; i < location.size(); i++) {
                    if (i != location.size() - 1) {
                        Point point = (Point) GeometryEngine.project(new Point(location.get(i).getLongitude(), location.get(i).getLatitude(), decimalSR), mapSpatialReference);
                        Point nextPoint = (Point) GeometryEngine.project(new Point(location.get(i + 1).getLongitude(), location.get(i + 1).getLatitude(), decimalSR), mapSpatialReference);
                        distance = getDistance(nextPoint, point, mapSpatialReference);

                        if (distance >= 5000) {
                            Log.i(TAG, "drawFindTripLine: wrong point (lat,long) y,x = " + nextPoint.getY() + "," + nextPoint.getX() + " lat,long = " + location.get(i + 1).getLatitude() + "," + location.get(i + 1).getLongitude());
                            wrongPoints.add(location.get(i + 1));
                            i++;
                        }
                    }
                    Log.i(TAG, "sortPoints: date = " + location.get(i).getDate() + " - time = " + location.get(i).getTime() + " - Speed = " + location.get(i).getSpeed2() + " - distance = " + distance + " i = " + i);
                }

                Log.i(TAG, "drawFindTripLine: wrong points size = " + wrongPoints.size());
                location = removeGaps(wrongPoints, location);

                /*--------------------------------------------------------*/

                Log.i(TAG, "sortPoints: display distances after correcting gaps\n\n");
                wrongPoints.clear();
                for (int i = 0; i < location.size(); i++) {
                    if (i != location.size() - 1) {
                        Point point = (Point) GeometryEngine.project(new Point(location.get(i).getLongitude(), location.get(i).getLatitude(), decimalSR), mapSpatialReference);
                        Point nextPoint = (Point) GeometryEngine.project(new Point(location.get(i + 1).getLongitude(), location.get(i + 1).getLatitude(), decimalSR), mapSpatialReference);
                        distance = getDistance(nextPoint, point, mapSpatialReference);

                        if (distance >= 1000) {
                            Log.i(TAG, "drawFindTripLine: wrong point (lat,long) y,x = " + nextPoint.getY() + "," + nextPoint.getX() + " lat,long = " + location.get(i + 1).getLatitude() + "," + location.get(i + 1).getLongitude());
                            wrongPoints.add(location.get(i + 1));
                            i++;
                        }
                    }
                    Log.i(TAG, "sortPoints: date = " + location.get(i).getDate() + " - time = " + location.get(i).getTime() + " - Speed = " + location.get(i).getSpeed2() + " - distance = " + distance + " i = " + i);
                }
                Log.i(TAG, "drawFindTripLine: wrong points size = " + wrongPoints.size());

                location = removeGaps(wrongPoints, location);

                /*--------------------------------------------------------*/
                sortDistanceThenTime(location);

                findTrip.getFoundCars().setLocations(location);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findTrip;
    }

    private void sortDistanceThenTime(ArrayList<CarStatus> location) {
        try {
            ArrayList<CarStatus> temp = new ArrayList<>();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double getDistance(Point endPoint, Point pointOnMap, SpatialReference
            mapSpatialReference) {
        double distance = -1;

        if (endPoint != null) {
            Point selectedPoint = new Point(pointOnMap.getX(), pointOnMap.getY(), mapSpatialReference);
            Point mEndPoint = new Point(endPoint.getX(), endPoint.getY(), mapSpatialReference);

            // Create a world equidistant cylindrical spatial reference for measuring planar distance.

            // Project the points from geographic to the projected coordinate system.
            Point startP = (Point) GeometryEngine.project(selectedPoint, mapSpatialReference);
            Point endP = (Point) GeometryEngine.project(mEndPoint, mapSpatialReference);

            // Get the planar distance between the points in the spatial reference unit (meters).
            distance = GeometryEngine.distanceBetween(startP, endP);
            // Result = 7,372,671.29511302 (around 7,372.67 kilometers)

//            Log.i(TAG, "getDistance(): Start Point X = " + startP.getX() + " Start Point Y = " + startP.getY());
//            Log.i(TAG, "getDistance(): End Point X = " + endP.getX() + " Enf Point Y = " + endP.getY());
//            Log.i(TAG, "getDistance(): distance = " + distance);
        }
        return distance;
    }

    private ArrayList<CarStatus> removeGaps(ArrayList<CarStatus> wrongPoints, ArrayList<CarStatus> location) {
        try {
            Log.i(TAG, "removeGaps: locations size = " + location.size());
            for (CarStatus wrongPoint : wrongPoints) {
                for (CarStatus point : location) {
                    if (wrongPoint.getLatitude() == point.getLatitude() && wrongPoint.getLongitude() == point.getLongitude()) {
                        location.remove(point);
                        break;
                    }
                }
            }

            Log.i(TAG, "removeGaps: locations size = " + location.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public ArrayList<PointCollection> getTrips(PointCollection pointCollection, ArrayList<CarStatus> locations) {
        ArrayList<PointCollection> trips = new ArrayList<>();
        for (int i = 0; i < locations.size(); i++) {

            if (i != locations.size() - 1) {
                CarStatus startPoint = locations.get(i);
                CarStatus endPoint = locations.get(i + 1);
                String startPointTime = startPoint.getTime().replaceAll("(AM)|(PM)| ", "");
                String endPointTime = endPoint.getTime().replaceAll("(AM)|(PM)| ", "");

                if (getHourDifference(startPointTime, endPointTime) >= 1) {

                }
            }
        }
        return trips;
    }

    private int getHourDifference(String startPointTime, String endPointTime) {

        return 0;
    }
}