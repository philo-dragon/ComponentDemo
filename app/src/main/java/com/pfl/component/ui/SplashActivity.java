package com.pfl.component.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;

import com.pfl.component.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
        finish();
        overridePendingTransition(R.anim.alpha_enter, R.anim.alpha_exit);
    }
}
