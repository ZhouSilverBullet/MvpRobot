package com.sdxxtop.mvprobot.di.module;

import com.sdxxtop.mvprobot.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public App getApplication() {
        return application;
    }
}
