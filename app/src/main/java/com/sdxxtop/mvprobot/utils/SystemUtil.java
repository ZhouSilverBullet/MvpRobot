package com.sdxxtop.mvprobot.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.text.TextUtils;

import com.sdxxtop.mvprobot.app.App;


public class SystemUtil {
    public static boolean isNetworkConnect() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo() != null;
        }
        return false;
    }

    public static String getVersionCode(Context context) {
        if (context == null) {
            return "10001";
        }
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            String versionCode = String.valueOf(pi.versionCode);
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return "10001";
        }
    }
}
