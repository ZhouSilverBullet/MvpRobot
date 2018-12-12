package com.sdxxtop.mvprobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.sdxxtop.mvprobot.R;
import com.sdxxtop.mvprobot.base.BaseMvpActivity;
import com.sdxxtop.mvprobot.model.bean.ChatBean;
import com.sdxxtop.mvprobot.model.bean.RequestBean;
import com.sdxxtop.mvprobot.presenter.MainPresenter;
import com.sdxxtop.mvprobot.presenter.contact.MainContact;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContact.ContactView {

    public static final String TAG = "MainActivity";

    @BindView(R.id.main_text)
    TextView textView;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        presenter.loadData();
    }

    @Override
    protected void initInject() {
        getComponent().inject(this);
    }

    @Override
    public void showError(int code, String message) {
        textView.setText(message);
    }

    @Override
    public void onData(ChatBean requestBean) {
        Log.e(TAG, "onData: " + requestBean);
    }
}
