package com.ekc.ekctracking.view.activities.settings;

public class SettingsPresenter {

    SettingsActivity mContext;
    SettingsListener listener;

    public SettingsPresenter(SettingsActivity mContext, SettingsListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }


}
