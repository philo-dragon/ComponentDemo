package com.pfl.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.pfl.common.di.AppComponent;
import com.pfl.common.listener.IActivity;
import com.pfl.common.utils.App;
import com.pfl.common.utils.StatusBarUtil;
import com.pfl.component.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yan.inflaterauto.InflaterAuto;

public abstract class BaseActivity extends RxAppCompatActivity implements IActivity {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(InflaterAuto.wrap(base));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContextView());
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                StatusBarUtil.immersive(BaseActivity.this);
                componentInject(App.getInstance(BaseApplication.class).getAppComponent());
                initToolbar();
                initView();
                initEvent();
                initData();
            }
        });
    }

    private void initToolbar() {
        StatusBarUtil.darkMode(this, true);
        if (findViewById(R.id.toolbar) != null) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            StatusBarUtil.setPadding(this, toolbar);
            toolbar.setTitle("");
            ((TextView) findViewById(R.id.toolbar_title)).setText(getTitle());
            findViewById(R.id.toolbar_back).setVisibility(isNeedBack() ? View.VISIBLE : View.INVISIBLE);
            findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public boolean isNeedBack() {
        return true;
    }

}