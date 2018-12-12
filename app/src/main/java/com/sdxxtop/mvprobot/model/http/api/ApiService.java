package com.sdxxtop.mvprobot.model.http.api;

import com.sdxxtop.mvprobot.model.bean.ChatBean;
import com.sdxxtop.mvprobot.model.bean.RequestBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiService {
    String BASE_URL = "http://ai.sdxxtop.com/robot/";

    @FormUrlEncoded
    @POST("chat/word")
    Observable<RequestBean> getRequestBean(@Field("data") String data);
}
