package com.sdxxtop.mvprobot.model.http.net;


import com.sdxxtop.mvprobot.BuildConfig;
import com.sdxxtop.mvprobot.app.Constants;
import com.sdxxtop.mvprobot.model.http.api.ApiService;
import com.sdxxtop.mvprobot.utils.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static OkHttpClient okHttpClient;
    private static ApiService apiService;

    public static ApiService getXXApi() {
        initOkHttp();
        if (apiService == null) {
            apiService = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService.class);
        }
        return apiService;
    }

    private static void initOkHttp() {
        if (okHttpClient != null) {
            return;
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnect()) {
                    //只进行缓存中读取
                    request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                int tryCount = 0;
                Response response = chain.proceed(request);
                while (!response.isSuccessful() && tryCount < 3) {
                    ++tryCount;
                    response = chain.proceed(request);
                }

                if (SystemUtil.isNetworkConnect()) {
                    int maxAge = 0;
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma").build();
                } else {
                    int maxStale = 60 * 60 * 24 * 7;
                    response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma").build();
                }

                return response;
            }
        };

        builder.addNetworkInterceptor(interceptor);
        builder.addInterceptor(interceptor);
        File file = new File(Constants.PATH_CACHE);
        //最多缓存100m
        builder.cache(new Cache(file, 100 * 1024 * 1024));

        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        builder.retryOnConnectionFailure(true);

        okHttpClient = builder.build();

    }
}
