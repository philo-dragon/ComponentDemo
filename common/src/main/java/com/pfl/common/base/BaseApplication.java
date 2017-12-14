package com.pfl.common.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.pfl.common.BuildConfig;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRouter(this);
    }

    private void initRouter(Application application) {

        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();//开启调试模式（如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭，否则有安全隐患）
        }

        ARouter.init(application);
    }
}
