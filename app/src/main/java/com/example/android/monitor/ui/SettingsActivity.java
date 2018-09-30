package com.example.android.monitor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Locale;

import com.example.android.monitor.AppConst;
import com.example.android.monitor.BuildConfig;
import com.example.android.monitor.R;
import com.example.android.monitor.util.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    Switch mSwitchSystem;
    Switch mSwitchUninstall;
    Switch mSwitchEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.settings);
        }

        // disable monitoring
//        mSwitchEnable = findViewById(R.id.disable_switch);
//        mSwitchEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (PreferenceManager.getInstance().getSystemSettings(PreferenceManager.PREF_SETTINGS_HIDE_SYSTEM_APPS) != b) {
//                    PreferenceManager.getInstance().putBoolean(PreferenceManager.PREF_SETTINGS_HIDE_SYSTEM_APPS, b);
//                    setResult(1);
//                }
//            }
//        });
//
//        findViewById(R.id.group_disable).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mSwitchSystem.performClick();
//            }
//        });

        // hide system
        mSwitchSystem = findViewById(R.id.switch_system_apps);
        mSwitchSystem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PreferenceManager.getInstance().getSystemSettings(PreferenceManager.PREF_SETTINGS_HIDE_SYSTEM_APPS) != b) {
                    PreferenceManager.getInstance().putBoolean(PreferenceManager.PREF_SETTINGS_HIDE_SYSTEM_APPS, b);
                    setResult(1);
                }
            }
        });

        findViewById(R.id.group_system).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwitchSystem.performClick();
            }
        });

        // hide uninstall
        mSwitchUninstall = findViewById(R.id.switch_uninstall_apps);
        mSwitchUninstall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PreferenceManager.getInstance().getUninstallSettings(PreferenceManager.PREF_SETTINGS_HIDE_UNINSTALL_APPS) != b) {
                    PreferenceManager.getInstance().putBoolean(PreferenceManager.PREF_SETTINGS_HIDE_UNINSTALL_APPS, b);
                    setResult(1);
                }
            }
        });

        findViewById(R.id.group_uninstall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwitchUninstall.performClick();
            }
        });

        // ignore list
        findViewById(R.id.group_ignore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, IgnoreActivity.class));
            }
        });

        restoreStatus();
    }

    private void restoreStatus() {
        mSwitchSystem.setChecked(PreferenceManager.getInstance().getSystemSettings(PreferenceManager.PREF_SETTINGS_HIDE_SYSTEM_APPS));
        mSwitchUninstall.setChecked(PreferenceManager.getInstance().getUninstallSettings(PreferenceManager.PREF_SETTINGS_HIDE_UNINSTALL_APPS));
    }
}
