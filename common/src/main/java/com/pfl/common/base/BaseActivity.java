package com.pfl.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;

import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.App;
import com.pfl.common.utils.StatusBarUtil;
import com.pfl.component.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.immersive(this);
        super.onCreate(savedInstanceState);
        setContextView();
        componentInject(App.getInstance(BaseApplication.class).getAppComponent());

        initView();
        initEvent();
    }

    private void setContextView() {
        View view = LayoutInflater.from(this).inflate(R.layout.base_layout, null, false);
        if (isNeedToolBar()) {
            ViewStub titleStub = view.findViewById(R.id.titleStub);
            titleStub.inflate();
        }
        ViewStub viewStub = view.findViewById(R.id.viewStub);
        viewStub.setLayoutResource(getContextView());
        viewStub.inflate();
        setContentView(view);
    }

    /**
     * 依赖注入的入口
     */
    protected void componentInject(AppComponent appComponent) {
    }

    /**
     * layoutId
     *
     * @return
     */
    protected abstract int getContextView();

    /**
     * 是否需要ToolBar
     *
     * @return
     */
    protected abstract boolean isNeedToolBar();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化event
     */
    protected abstract void initEvent();
}