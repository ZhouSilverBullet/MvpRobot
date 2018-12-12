package com.sdxxtop.mvprobot.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected View rootView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutView(), container, false);
        rootView = view;
        unbinder = ButterKnife.bind(this, view);
        initVariable();
        initView(view);
        initEvent(view);
        initData();
        return view;
    }

    protected void initVariable() {

    }

    protected void initData() {

    }

    protected void initEvent(View view) {

    }

    protected void initView(View view) {

    }

    protected abstract int getLayoutView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
