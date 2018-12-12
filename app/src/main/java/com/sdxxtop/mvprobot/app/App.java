package com.sdxxtop.mvprobot.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.sdxxtop.mvprobot.di.component.AppComponent;
import com.sdxxtop.mvprobot.di.component.DaggerAppComponent;
import com.sdxxtop.mvprobot.di.module.AppModule;

public class App extends Application {
    private static AppComponent appComponent;
    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }



    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent =  DaggerAppComponent.builder()
                    .appModule(new AppModule(instance)).build();
        }
        return appComponent;
    }
}
