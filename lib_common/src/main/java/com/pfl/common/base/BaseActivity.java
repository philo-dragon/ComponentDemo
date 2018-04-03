package com.pfl.common.base;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pfl.common.listener.IActivity;
import com.pfl.common.utils.App;
import com.pfl.common.utils.StatusBarUtil;
import com.pfl.component.R;

/**
 * Created by rocky on 2018/3/27.
 */

public abstract class BaseActivity<T> extends AppCompatActivity implements IActivity {

    protected T mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        StatusBarUtil.immersive(this);
        componentInject(App.getInstance(BaseApplication.class).getAppComponent());
        initView(getWindow().getDecorView());
        initEvent();
        initData();
    }

    private void setContentView() {
        if (isSupportDataBindind()) {
            mBinding = dataBinding();
        } else {
            setContentView(getContextView());
        }
    }

    /**
     * DataBinding
     */
    private T dataBinding() {
        return (T) DataBindingUtil.setContentView(this, getContextView());
    }

    private boolean isSupportDataBindind() {
        return true;
    }
}
