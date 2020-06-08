package com.ekc.ekctracking.view.activities.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ekc.ekctracking.R;

public class SettingsActivity extends AppCompatActivity implements SettingsListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
