package com.sdxxtop.mvprobot.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        unbinder = ButterKnife.bind(this);
        initVariable();
        initView();

    }

    protected void initVariable() {

    }

    protected void initView() {

    }

    protected abstract int getLayoutView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void getActivityComponent() {

    }
}
