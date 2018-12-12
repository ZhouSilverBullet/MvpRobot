package com.sdxxtop.mvprobot.presenter;

import android.util.Log;

import com.sdxxtop.mvprobot.base.RxPresenter;
import com.sdxxtop.mvprobot.model.bean.ChatBean;
import com.sdxxtop.mvprobot.model.bean.RequestBean;
import com.sdxxtop.mvprobot.model.http.net.NetHelper;
import com.sdxxtop.mvprobot.model.http.net.Params;
import com.sdxxtop.mvprobot.model.http.net.RetrofitHelper;
import com.sdxxtop.mvprobot.presenter.contact.MainContact;
import com.sdxxtop.mvprobot.utils.RxUtil;

import java.util.HashMap;

import javax.inject.Inject;

import rx.functions.Action1;

public class MainPresenter extends RxPresenter<MainContact.ContactView> implements MainContact.ContactPresenter {

    @Inject
    public MainPresenter() {
    }


    public void loadData() {

        Params params = new Params();
        params.put("it", "你好");

        RetrofitHelper.getXXApi().getRequestBean(params.getData())
                .compose(RxUtil.<RequestBean>rxSchedulerHelper())
                .compose(RxUtil.<RequestBean>handleBeanResult())
                .subscribe(new Action1<RequestBean>() {
                    @Override
                    public void call(RequestBean requestBean) {
                        if (baseView != null) {
//                            baseView.onData(requestBean);
                            Log.e("mainPresenter", "requestBean :" + requestBean);
                        }
                    }
                });
        baseView.showError(0, "11111");
    }
}
