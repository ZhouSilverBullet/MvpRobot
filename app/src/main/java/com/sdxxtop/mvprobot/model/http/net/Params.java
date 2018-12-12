package com.sdxxtop.mvprobot.model.http.net;

import android.content.Context;
import android.text.TextUtils;

import com.sdxxtop.mvprobot.app.App;
import com.sdxxtop.mvprobot.app.Constants;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/7.
 */

public class Params {
    protected Context context;
    protected HashMap<String, String> map;

    public Params() {
        this.context = App.getInstance().getBaseContext();
        map = new HashMap<>();
        defaultValue();
    }

    private void defaultValue() {
        map.put("tp", Constants.REQUEST_TYPE);
    }


    public void removeKey(String key) {
        if (map.containsKey(key)) {
            map.remove(key);
        }
    }

    public void put(String key, String value) {
        map.put(key, stringNotNull(value));
    }

    public void put(String key, long value) {
        map.put(key, value + "");
    }

    public void put(String key, int value) {
        map.put(key, value + "");
    }

    public String getData() {
        return NetHelper.getBase64Data(map);
    }

    @Override
    public String toString() {
        return NetHelper.getBase64Data(map);
    }

    private String stringNotNull(String value) {
        String temp = "";
        if (!TextUtils.isEmpty(value)) {
            temp = value;
        }
        return temp;
    }
}
