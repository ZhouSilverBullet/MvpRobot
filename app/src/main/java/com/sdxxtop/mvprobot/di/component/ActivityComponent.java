package com.sdxxtop.mvprobot.di.component;

import android.app.Activity;

import com.sdxxtop.mvprobot.MainActivity;
import com.sdxxtop.mvprobot.app.App;
import com.sdxxtop.mvprobot.di.module.ActivityModule;
import com.sdxxtop.mvprobot.di.qualifier.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);
}
