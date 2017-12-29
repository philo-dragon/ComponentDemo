package com.pfl.component.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.animation.Animation;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.utils.AppManager;
import com.pfl.common.utils.RouteUtils;
import com.pfl.component.R;
import com.pfl.component.ui.activity.MainActivity;

@Route(path = RouteUtils.APP_SPLASH_ACTIVITY)
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!SPUtils.getInstance().getBoolean("isWelcome", false)) {
            // Activity使用ARouter启动另一个Activity 并finish掉自己会有闪烁问题
            //RouteUtils.actionStart(RouteUtils.APP_WELCOME_ACTIVITY, R.anim.alpha_enter, R.anim.alpha_exit);
            WelcomeActivity.actionStart(this);
            finish();
            overridePendingTransition(R.anim.alpha_enter, R.anim.alpha_exit);
        } else {
            // Activity使用ARouter启动另一个Activity 并finish掉自己会有闪烁问题
            //RouteUtils.actionStart(RouteUtils.APP_MAIN_ACTIVITY, R.anim.alpha_enter, R.anim.alpha_exit);
            MainActivity.actionStart(this);
            finish();
            overridePendingTransition(R.anim.alpha_enter, R.anim.alpha_exit);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
