package com.sdxxtop.mvprobot.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sdxxtop.mvprobot.app.App;
import com.sdxxtop.mvprobot.di.component.ActivityComponent;
import com.sdxxtop.mvprobot.di.component.DaggerActivityComponent;
import com.sdxxtop.mvprobot.di.component.DaggerAppComponent;
import com.sdxxtop.mvprobot.di.module.ActivityModule;
import com.sdxxtop.mvprobot.di.module.AppModule;

import javax.inject.Inject;

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initInject();
        if (presenter != null) {
            presenter.attach(this);
        }
        super.onCreate(savedInstanceState);
    }

    protected abstract void initInject();

    protected ActivityComponent getComponent() {
        return DaggerActivityComponent.builder().
                appComponent(App.getAppComponent())
                .activityModule(new ActivityModule(this)).build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
