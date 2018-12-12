package com.sdxxtop.mvprobot.utils;

import android.text.TextUtils;

import com.sdxxtop.mvprobot.model.bean.RequestBean;
import com.sdxxtop.mvprobot.model.http.exception.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> Observable.Transformer<RequestBean<T>, T> handleDataResult() {   //compose判断结果
        return new Observable.Transformer<RequestBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<RequestBean<T>> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<RequestBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(RequestBean<T> tRequestBean) {
                        if (!tRequestBean.getError()) {
                            return createData(tRequestBean.getData());
                        } else {
                            return Observable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    //单独处理responsebean的这中情况
    public static <T extends RequestBean> Observable.Transformer<T, T> handleBeanResult() {   //compose判断结果
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<T, Observable<T>>() {
                    @Override
                    public Observable<T> call(T tRequestBean) {
                        if (!tRequestBean.getError()) {
                            return createData(tRequestBean);
                        } else {
                            return Observable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
