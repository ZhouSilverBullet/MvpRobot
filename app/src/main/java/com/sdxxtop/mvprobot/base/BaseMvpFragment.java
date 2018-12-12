package com.sdxxtop.mvprobot.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sdxxtop.mvprobot.app.App;
import com.sdxxtop.mvprobot.di.component.AppComponent;
import com.sdxxtop.mvprobot.di.component.DaggerFragmentComponent;
import com.sdxxtop.mvprobot.di.component.FragmentComponent;
import com.sdxxtop.mvprobot.di.module.FragmentModule;

import javax.inject.Inject;

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {
    @Inject
    protected  T basePresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject();
        if (basePresenter != null) {
            basePresenter.attach(this);
        }
    }

    protected abstract void initInject();

    protected FragmentComponent getComponent() {
        return DaggerFragmentComponent.builder().
                appComponent(App.getAppComponent()).
                fragmentModule(new FragmentModule(this)).build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (basePresenter != null) {
            basePresenter.detach();
        }
    }
}
