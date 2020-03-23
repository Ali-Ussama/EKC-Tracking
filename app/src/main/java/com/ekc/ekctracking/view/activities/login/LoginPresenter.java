package com.ekc.ekctracking.view.activities.login;

import android.util.Log;

import com.ekc.ekctracking.api_network.ApiClient;
import com.ekc.ekctracking.configs.PrefManager;
import com.ekc.ekctracking.models.LoginModel;
import com.ekc.ekctracking.models.User;

public class LoginPresenter {

    private static final String TAG = "LoginPresenter";
    private LoginActivity mCurrent;
    private LoginViewListener listener;
    private ApiClient mApiClient;
    private PrefManager mPrefManager;

    public LoginPresenter(LoginActivity mCurrent, LoginViewListener listener) {
        this.mCurrent = mCurrent;
        this.listener = listener;
        this.mApiClient = new ApiClient();
        this.mPrefManager = new PrefManager(this.mCurrent);
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

                                listener.onLogin(true, null);
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
