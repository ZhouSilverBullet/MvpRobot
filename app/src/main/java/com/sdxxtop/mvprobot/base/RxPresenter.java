package com.sdxxtop.mvprobot.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T baseView;
    private CompositeSubscription mCompositeSubscription;

    public void subscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attach(T baseView) {
        this.baseView = baseView;
    }

    @Override
    public void detach() {
        if (baseView != null) {
            baseView = null;
        }
        unSubscribe();
    }
}
