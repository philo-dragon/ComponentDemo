package com.pfl.common.base;

import android.content.Context;
import android.graphics.Color;
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
import com.pfl.common.weidget.TitleBar;
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
                initView(getWindow().getDecorView());
                initEvent();
                initData();
            }
        });
    }

    private void initToolbar() {
        if (findViewById(R.id.toolbar) != null) {
            final TitleBar titleBar = findViewById(R.id.title_bar);
            titleBar.setImmersive(isImmersive());
            titleBar.setBackgroundColor(setBackGroundColor());
            if (isNeedBack()) {
                titleBar.setLeftImageResource(getLeftImageResource());
                titleBar.setLeftText("返回");
            }
            titleBar.setLeftTextColor(setLeftTextColor());
            titleBar.setTitle(getTitle());
            titleBar.setTitleColor(setTitleColor());
            titleBar.setDividerColor(setToolBarDividerColor());

            titleBar.setLeftClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    /**
     * is visible back button
     *
     * @return
     */
    protected boolean isNeedBack() {
        return true;
    }

    /**
     * set ivider color
     *
     * @return
     */
    protected int setToolBarDividerColor() {
        return Color.TRANSPARENT;
    }

    /**
     * set title color
     *
     * @return
     */
    protected int setTitleColor() {
        return Color.WHITE;
    }

    /**
     * set left text color
     *
     * @return
     */
    protected int setLeftTextColor() {
        return Color.WHITE;
    }

    /**
     * set background color
     *
     * @return
     */
    protected int setBackGroundColor() {
        return Color.parseColor("#64b4ff");
    }

    /**
     * set left image resid
     *
     * @return
     */
    protected int getLeftImageResource() {
        return R.mipmap.back_green;
    }

    /**
     * 是否沉浸式
     *
     * @return
     */
    @Override
    public boolean isImmersive() {
        return true;
    }

}