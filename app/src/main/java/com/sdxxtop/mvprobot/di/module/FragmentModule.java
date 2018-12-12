package com.sdxxtop.mvprobot.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.sdxxtop.mvprobot.di.qualifier.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @FragmentScope
    @Provides
    public Activity getActivity() {
        return fragment.getActivity();
    }
}
