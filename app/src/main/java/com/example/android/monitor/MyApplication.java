package com.example.android.monitor;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.android.monitor.BuildConfig;
import com.example.android.monitor.data.AppItem;
import com.example.android.monitor.data.DataManager;
import com.example.android.monitor.db.DbHistoryExecutor;
import com.example.android.monitor.db.DbIgnoreExecutor;
import com.example.android.monitor.service.AppService;
import com.example.android.monitor.util.PreferenceManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceManager.init(this);
        getApplicationContext().startService(new Intent(getApplicationContext(), AppService.class));
        DbIgnoreExecutor.init(getApplicationContext());
        DbHistoryExecutor.init(getApplicationContext());
        DataManager.init();
        addDefaultIgnoreAppsToDB();
    }

    private void addDefaultIgnoreAppsToDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> mDefaults = new ArrayList<>();
                mDefaults.add("com.android.settings");
                mDefaults.add(BuildConfig.APPLICATION_ID);
                for (String packageName : mDefaults) {
                    AppItem item = new AppItem();
                    item.mPackageName = packageName;
                    item.mEventTime = System.currentTimeMillis();
                    DbIgnoreExecutor.getInstance().insertItem(item);
                }
            }
        }).run();
    }
}
