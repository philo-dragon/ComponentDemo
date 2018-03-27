package com.pfl.common.base;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pfl.common.di.AppComponent;
import com.pfl.common.listener.IActivity;
import com.pfl.common.utils.App;
import com.pfl.common.utils.StatusBarUtil;

/**
 * Created by rocky on 2018/3/27.
 */

public abstract class BaseActivity2<T> extends AppCompatActivity implements IActivity {

    protected T mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isSupportDataBindind()) {
            mBinding = dataBinding();
        } else {
            setContentView(getContextView());
        }

        StatusBarUtil.immersive(this);
        componentInject(App.getInstance(BaseApplication.class).getAppComponent());
        initView(getWindow().getDecorView());
        initEvent();
        initData();
    }

    /**
     * DataBinding
     */
    public T dataBinding() {
        return (T) DataBindingUtil.setContentView(this, getContextView());
    }

    private boolean isSupportDataBindind() {
        return false;
    }

    protected void finishActivity() {
        finish();
    }
}
