package com.sdxxtop.mvprobot.di.component;

import com.sdxxtop.mvprobot.app.App;
import com.sdxxtop.mvprobot.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    App getAppContext();
}
