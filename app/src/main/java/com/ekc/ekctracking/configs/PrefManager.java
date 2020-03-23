package com.ekc.ekctracking.configs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefManager {


    public static final String KEY_TOKEN = "Token";
    public static final String KEY_TOKEN_TYPE = "Token_type";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_ISSUED = "issued";
    public static final String KEY_EXPIRES_IN = "expires_in";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_GRANT_TYPE = "grant_type";

//    public static final String KEY_ = "";
    private SharedPreferences prefs;

    public PrefManager(Context c) {
        prefs = PreferenceManager.getDefaultSharedPreferences(c);
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public void saveLong(String key, long value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putLong(key, value);
        edit.apply();
    }

    public void saveDouble(String key, double value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putFloat(key, (float) value);
        edit.apply();
    }

    public void saveBoolean(String key, boolean b) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(key, b);
        edit.apply();
    }

    public void saveInt(String key, int value){
        SharedPreferences.Editor edit = prefs.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public String readString(String key) {
        return prefs.getString(key, "");
    }

    public boolean readBoolean(String key) {
        return prefs.getBoolean(key, false);
    }

    public long readLong(String key) {
        return prefs.getLong(key, 0);
    }

    public long readInt(String key) {
        return prefs.getInt(key, 0);
    }

    public double readDouble(String key) {
        return (double) prefs.getFloat(key, 0);
    }
}
