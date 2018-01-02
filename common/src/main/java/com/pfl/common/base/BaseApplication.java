package com.pfl.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.di.AppComponent;
import com.pfl.common.di.AppModule;
import com.pfl.common.di.DaggerAppComponent;
import com.pfl.common.di.NetworkModule;
import com.pfl.common.utils.AppManager;
import com.pfl.common.utils.BaseUrlManager;
import com.pfl.common.utils.CallBacks;
import com.pfl.component.BuildConfig;
import com.pfl.component.R;
import com.yan.inflaterauto.InflaterAuto;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        BaseUrlManager.init("http://apitest.topzuqiu.cn/", "http://apitest.topzuqiu.cn/", false);//动态切换BaseUrl
        initRouter(this);//初始化Router
        registerLifecycleCallbacks();//注册Activity生命周期监听
        initAppComponent();//Dagger2 初始化全局参数
    }

    private void initAppComponent() {

        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .networkModule(new NetworkModule())
                    .build();
        }

    }

    public AppComponent getAppComponent() {
        initAppComponent();
        return appComponent;
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
            registerActivityLifecycleCallbacks(new CallBacks() {
                @Override
                public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
                    AppManager.getAppManager().addActivity(activity);

                   /* //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
                    if (activity.findViewById(R.id.toolbar) != null) { //找到 Toolbar 并且替换 Actionbar
                        if (activity instanceof AppCompatActivity) {
                            ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
                            ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
                                activity.getActionBar().setDisplayShowTitleEnabled(false);
                            }
                        }
                    }
                    if (activity.findViewById(R.id.toolbar_title) != null) { //找到 Toolbar 的标题栏并设置标题名
                        ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
                    }
                    if (activity.findViewById(R.id.toolbar_back) != null) { //找到 Toolbar 的返回按钮,并且设置点击事件,点击关闭这个 Activity
                        activity.findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.onBackPressed();
                            }
                        });
                    }*/
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    AppManager.getAppManager().finishActivity(activity);
                }
            });
        }
    }

}

