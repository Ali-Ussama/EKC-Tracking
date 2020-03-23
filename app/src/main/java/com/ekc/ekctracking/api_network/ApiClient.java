package com.ekc.ekctracking.api_network;

import android.annotation.SuppressLint;
import android.util.Log;

import com.ekc.ekctracking.models.LoginModel;
import com.ekc.ekctracking.models.OnGoingRequest;
import com.ekc.ekctracking.models.StatusRoot;
import com.ekc.ekctracking.models.User;
import com.ekc.ekctracking.models.findTrip.FindTrip;
import com.ekc.ekctracking.models.findTrip.FindTripRequest;
import com.ekc.ekctracking.models.hereMapRoutModel.HRoute;
import com.ekc.ekctracking.models.hereMapRoutModel.HereRoute;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String TAG = "ApiClient";
    private String BASE_URL = "https://apis.gulf-findme.com/";
    private String BASE_URL_HERE = "https://route.api.here.com/routing/7.2/";

    private static ApiClient remoteDataSource;
    private Retrofit retrofit;
    private CommonCallback<Object> callback;

    public interface CommonCallback<T> {
        void onSuccess(T response);

        void onFailure(Throwable throwable);
    }

    public static synchronized ApiClient getInstance() {
        if (remoteDataSource == null) {
            remoteDataSource = new ApiClient();
        }

        return remoteDataSource;
    }

    private NetworkAPIS getAPI() {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(60, TimeUnit.MINUTES)
                .writeTimeout(60, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.MINUTES);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.addInterceptor(interceptor);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpBuilder.build())
                .build();
        return retrofit.create(NetworkAPIS.class);
    }

    private NetworkAPIS getAPI_HERE() {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(60, TimeUnit.MINUTES)
                .writeTimeout(60, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.MINUTES);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.addInterceptor(interceptor);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_HERE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpBuilder.build())
                .build();
        return retrofit.create(NetworkAPIS.class);
    }

    @SuppressLint("CheckResult")
    public void login(LoginModel loginBody, final CommonCallback<Object> callback) {
        try {
            this.callback = callback;
            NetworkAPIS networkAPIS = ApiClient.getInstance().getAPI();
            Observable<User> cryptoObservable = networkAPIS.getToken(loginBody.getGrant_type(), loginBody.getUsername(), loginBody.getPassword());
            cryptoObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(this::handleUserResult, this::handleUserError);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleUserResult(User user) {
        try {
            Log.i(TAG, "handleResult(): username = " + user.getUsername());
            Log.i(TAG, "handleResult(): getToken = " + user.getToken());
            Log.i(TAG, "handleResult(): getToken_type = " + user.getToken_type());
            Log.i(TAG, "handleResult(): getExpiresIn = " + user.getExpiresIn());
            Log.i(TAG, "handleResult(): getIssued = " + user.getIssued());
            if (callback != null) {
                callback.onSuccess(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleUserError(Throwable t) {
        t.printStackTrace();
        if (callback != null) {
            callback.onFailure(t);
        }
    }

    @SuppressLint("CheckResult")
    public void getOnGoingStatus(OnGoingRequest onGoingRequest, CommonCallback<Object> callback) {
        try {
            this.callback = callback;
            NetworkAPIS networkAPIS = ApiClient.getInstance().getAPI();
            Observable<StatusRoot> cryptoObservable = networkAPIS.getOnGoingStatus(onGoingRequest.getAuthorization());
            cryptoObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(this::handleOnGoingResult, this::handleOnGoingError);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleOnGoingError(Throwable throwable) {
        if (callback != null) {
            callback.onFailure(throwable);
        }
    }

    private void handleOnGoingResult(StatusRoot statusRoot) {
        try {
            if (callback != null) {
                callback.onSuccess(statusRoot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("CheckResult")
    public void getFindTrip(FindTripRequest findTripRequest, CommonCallback<Object> callback) {
        try {
            this.callback = callback;
            NetworkAPIS networkAPIS = ApiClient.getInstance().getAPI();

            Observable<FindTrip> cryptoObservable = networkAPIS.findTrip(findTripRequest.getToken(),
                    findTripRequest.getGpsUnitNo(), findTripRequest.getFromDate(), findTripRequest.getFromTime(),
                    findTripRequest.getToDate(), findTripRequest.getToTime());

            cryptoObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(this::handleFindTripResult, this::handleFindTripError);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleFindTripError(Throwable throwable) {
        if (callback != null) {
            callback.onFailure(throwable);
        }
    }

    private void handleFindTripResult(FindTrip findTrip) {
        if (callback != null) {
            callback.onSuccess(findTrip);
        }
    }

    @SuppressLint("CheckResult")
    public void getHereRout(String wayPoint0, String wayPoint1, String mode, String app_id, String app_code, CommonCallback<Object> callback) {
        try {
            this.callback = callback;
            NetworkAPIS networkAPIS = ApiClient.getInstance().getAPI_HERE();

            Observable<HRoute> cryptoObservable = networkAPIS.getHereRouting(wayPoint0, wayPoint1, mode, app_id, app_code);

            cryptoObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(this::handleHereRouteResult, this::handleHereRoutError);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void handleHereRoutError(Throwable throwable) {
        if (callback != null) {
            callback.onFailure(throwable);
        }
    }

    private void handleHereRouteResult(HRoute hereRoute) {
        if (callback != null) {
            callback.onSuccess(hereRoute);
        }
    }
}
