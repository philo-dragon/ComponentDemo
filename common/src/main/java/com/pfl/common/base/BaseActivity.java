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

import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.swipbackhelper.SwipeListener;
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
        initSwipeBack();
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                init();
            }
        });
    }

    protected void initSwipeBack() {
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)//获取当前页面
                .setSwipeBackEnable(isSwipeBackEnable())//设置是否可滑动
                .setSwipeEdge(200)//可滑动的范围。px。200表示为左边200px的屏幕
                .setSwipeEdgePercent(0.2f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                .setSwipeSensitivity(1f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
                .setScrimColor(Color.parseColor("#99000000"))//底层阴影颜色
                .setClosePercent(0.4f)//触发关闭Activity百分比
                .setSwipeRelateEnable(isSwipeRelateEnable())//是否与下一级activity联动(微信效果)。默认关
                .setSwipeRelateOffset(500)//activity联动时的偏移量。默认500px。
                .setDisallowInterceptTouchEvent(true)//不抢占事件，默认关（事件将先由子View处理再由滑动关闭处理）
                .addListener(new SwipeListener() {//滑动监听

                    @Override
                    public void onScroll(float percent, int px) {//滑动的百分比与距离
                    }

                    @Override
                    public void onEdgeTouch() {//当开始滑动
                    }

                    @Override
                    public void onScrollToClose() {//当滑动关闭
                    }
                });
    }

    ;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    private void init() {
        StatusBarUtil.immersive(this);
        componentInject(App.getInstance(BaseApplication.class).getAppComponent());
        initToolbar();
        initView(getWindow().getDecorView());
        initEvent();
        initData();
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
            titleBar.setActionTextColor(setActionTextColor());
            titleBar.setDividerColor(setToolBarDividerColor());

            titleBar.setLeftClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishActivity();
                }
            });
        }
    }

    public void finishActivity() {
        finish();
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
     * actionText color
     *
     * @return
     */
    private int setActionTextColor() {
        return Color.WHITE;
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

    /**
     * 是否滑动退出
     *
     * @return
     */
    public boolean isSwipeBackEnable() {
        return true;
    }

    /**
     * 是否与下一级activity联动
     *
     * @return
     */
    public boolean isSwipeRelateEnable() {
        return true;
    }

}