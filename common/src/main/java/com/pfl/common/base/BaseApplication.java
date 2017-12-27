package com.pfl.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.pfl.common.utils.AppManager;
import com.pfl.component.BuildConfig;
import com.yan.inflaterauto.InflaterAuto;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRouter(this);
        registerLifecycleCallbacks();
    }

    /**
     * 如果你使用了LayoutInflater.from(getApplicationContext())或者LayoutInflater.from(getApplication())
     * 就需要以下操作，如果没有，以下方法不必重写
     */
    @Override
    protected void attachBaseContext(Context base) {
        //替换Inflater
        super.attachBaseContext(InflaterAuto.wrap(base));
    }

    private void initUI(){

    }

    private void initRouter(Application application) {

        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();//开启调试模式（如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭，否则有安全隐患）
        }

        ARouter.init(application);
    }

    public void registerLifecycleCallbacks() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    AppManager.getAppManager().addActivity(activity);
                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    AppManager.getAppManager().finishActivity(activity);
                }
            });
        }
    }

}

