package com.ekc.ekctracking.configs;

import android.app.Application;

import io.realm.Realm;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            Realm.init(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
