package com.sdxxtop.mvprobot.di.module;

import android.app.Activity;

import com.sdxxtop.mvprobot.app.App;
import com.sdxxtop.mvprobot.di.qualifier.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    public Activity getActivity() {
        return activity;
    }
}
