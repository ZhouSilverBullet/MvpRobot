package com.sdxxtop.mvprobot.presenter.contact;

import com.sdxxtop.mvprobot.base.BasePresenter;
import com.sdxxtop.mvprobot.base.BaseView;
import com.sdxxtop.mvprobot.model.bean.ChatBean;
import com.sdxxtop.mvprobot.model.bean.RequestBean;

public interface MainContact {
    interface ContactPresenter extends BasePresenter<ContactView> {

    }

    interface ContactView extends BaseView {
        void onData(ChatBean requestBean);
    }
}
