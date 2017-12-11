package com.pfl.common.base;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    private static BaseApplication sInstance;
    public static Context context;

    public static BaseApplication getIns() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        context = this.getApplicationContext();
           /* if (isAppDebug(context)) {
                //只有debug模式才会打印日志
                Logger.init("Demo").logLevel(LogLevel.FULL);
            } else {
                Logger.init("Demo").logLevel(LogLevel.NONE);
            }*/
    }
}