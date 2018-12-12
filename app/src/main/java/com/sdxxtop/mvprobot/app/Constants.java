package com.sdxxtop.mvprobot.app;

import java.io.File;

public interface Constants {
    String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    String PATH_CACHE = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "NetCache";

    public static final String REQUEST_TYPE = "2";

}
