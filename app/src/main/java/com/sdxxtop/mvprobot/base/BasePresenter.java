package com.sdxxtop.mvprobot.base;

public interface BasePresenter<T> {
    void attach(T t);
    void detach();
}
