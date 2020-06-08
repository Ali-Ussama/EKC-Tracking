package com.ekc.ekctracking.view.activities.login;

import android.util.Log;

import com.ekc.ekctracking.api_network.ApiClient;
import com.ekc.ekctracking.configs.PrefManager;
import com.ekc.ekctracking.models.pojo.LoginModel;
import com.ekc.ekctracking.models.pojo.User;
import com.ekc.ekctracking.models.pojo.carType.CarByType;
import com.ekc.ekctracking.models.pojo.carType.CarByTypeCars;
import com.ekc.ekctracking.models.pojo.carType.CarByTypeGroup;
import com.ekc.ekctracking.models.realmDB.RealmCarByType;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginPresenter {

    private static final String TAG = "LoginPresenter";
    private LoginActivity mCurrent;
    private LoginViewListener listener;
    private ApiClient mApiClient;
    private PrefManager mPrefManager;
    private Realm realm;

    public LoginPresenter(LoginActivity mCurrent, LoginViewListener listener) {
        this.mCurrent = mCurrent;
        this.listener = listener;
        this.mApiClient = new ApiClient();
        this.mPrefManager = new PrefManager(this.mCurrent);
        realm = Realm.getDefaultInstance();
    }

    public void loadCarsDetails() {
        try {
            Log.d(TAG, "loadCarsDetails: is called");
            String token = mPrefManager.readString(PrefManager.KEY_TOKEN);
            token = "bearer " + token;
            mApiClient.getCarByType(token, new ApiClient.CommonCallback<Object>() {
                @Override
                public void onSuccess(Object response) {
                    Log.d(TAG, "loadCarsDetails: onSuccess: is called");
                    if (response instanceof CarByType) {
                        Log.d(TAG, "loadCarsDetails: onSuccess: instance of CarByType");
                        CarByType carByType = (CarByType) response;
                        for (CarByTypeGroup carByTypeGroup : carByType.getRoot().getGroup()) {
                            for (CarByTypeCars car : carByTypeGroup.getCars()) {
                                Log.d(TAG, "onSuccess: car = " + car.getCarNo());
                                if (!carByTypeIsExists(car.getCarNo())) {
                                    Log.d(TAG, "loadCarsDetails: onSuccess: car not Exists in Database");
                                    realm.beginTransaction();
                                    RealmCarByType realmCarByType = realm.createObject(RealmCarByType.class, car.getCarNo());
                                    realmCarByType.setCarID(car.getCarID());
                                    realmCarByType.setDisabledCount(car.getDisabledCount());
                                    realmCarByType.setGpsUnitNo(car.getGpsUnitNo());
                                    realmCarByType.setPhoneNo(car.getPhoneNo());
                                    realm.commitTransaction();

                                    Log.d(TAG, "loadCarsDetails: onSuccess: car " + car.getCarNo() + "is saved into Database");

                                }
                            }
                        }
                        Log.d(TAG, "loadCarDetails: onSuccess: calling onLogin");
                        listener.onLogin(true, null);
                    }
                }

                @Override
                public void onFailure(Throwable throwable) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean carByTypeIsExists(String carNo) {
        realm.beginTransaction();
        RealmCarByType result = realm.where(RealmCarByType.class).equalTo("carNo", carNo).findFirst();
        realm.commitTransaction();
        return result != null;
    }

    public void login(LoginModel loginModel) {

        try {
            if (!loggedInBefore()) {
                mApiClient.login(loginModel, new ApiClient.CommonCallback<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        try {
                            if (response instanceof User) {
                                User user = (User) response;

                                if (mPrefManager != null) {
                                    mPrefManager.saveString(PrefManager.KEY_TOKEN, user.getToken());
                                    mPrefManager.saveString(PrefManager.KEY_TOKEN_TYPE, user.getToken_type());
                                    mPrefManager.saveString(PrefManager.KEY_EXPIRES_IN, user.getToken());
                                    mPrefManager.saveString(PrefManager.KEY_ISSUED, user.getToken());
                                    mPrefManager.saveString(PrefManager.KEY_USERNAME, user.getUsername());
                                    mPrefManager.saveString(PrefManager.KEY_PASSWORD, loginModel.getPassword());
                                }
                                loadCarsDetails();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onLogin(false, e);
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        try {
                            listener.onLogin(false, throwable);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onLogin(false, e);
                        }
                    }
                });
            } else {
                listener.onLogin(true, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            listener.onLogin(false, e);
        }
    }

    boolean loggedInBefore() {
        try {
            if (mPrefManager != null) {
                String token = mPrefManager.readString(PrefManager.KEY_TOKEN);
                String username = mPrefManager.readString(PrefManager.KEY_USERNAME);
                if (token != null && !token.isEmpty() && username != null && !username.isEmpty()) {
                    Log.i(TAG, "loggedInBefore: logged before true");
                    return true;
                }
                Log.i(TAG, "loggedInBefore: token = " + token);
                Log.i(TAG, "loggedInBefore: username = " + username);
                Log.i(TAG, "loggedInBefore: logged before false");
            } else {
                Log.i(TAG, "loggedInBefore: mPrefManager = null");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
