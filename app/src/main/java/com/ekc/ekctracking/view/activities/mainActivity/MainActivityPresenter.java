package com.ekc.ekctracking.view.activities.mainActivity;

import com.ekc.ekctracking.configs.PrefManager;

public class MainActivityPresenter {

    private MainActivity mCurrent;
    private PrefManager mPrefManager;
    private MainActivityViewListener listener;

    public MainActivityPresenter(MainActivity mCurrent, MainActivityViewListener listener) {
        this.mCurrent = mCurrent;
        this.mPrefManager = new PrefManager(this.mCurrent);
        this.listener = listener;
    }

    void logout() {
        try {
            if (mPrefManager != null) {
                mPrefManager.saveString(PrefManager.KEY_PASSWORD, "");
                mPrefManager.saveString(PrefManager.KEY_USERNAME, "");
                mPrefManager.saveString(PrefManager.KEY_TOKEN, "");
                mPrefManager.saveString(PrefManager.KEY_ISSUED, "");
                mPrefManager.saveString(PrefManager.KEY_EXPIRES_IN, "");
                mPrefManager.saveString(PrefManager.KEY_TOKEN_TYPE, "");
                mPrefManager.saveString(PrefManager.KEY_GRANT_TYPE, "");

                listener.onLogoutSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
