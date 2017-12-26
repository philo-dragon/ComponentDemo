package com.pfl.component.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.animation.Animation;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.pfl.common.utils.RouteUtils;
import com.pfl.component.R;

@Route(path = RouteUtils.APP_SPLASH_ACTIVITY)
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RouteUtils.actionStart(RouteUtils.APP_WELCOME_ACTIVITY, R.anim.alpha_enter, R.anim.alpha_exit);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
