package com.sdxxtop.mvprobot.di.component;


import android.app.Activity;
import android.support.v4.app.Fragment;

import com.sdxxtop.mvprobot.di.module.FragmentModule;
import com.sdxxtop.mvprobot.di.qualifier.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(Fragment fragment);
}
