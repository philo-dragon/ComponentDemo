package com.pfl.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pfl.common.utils.StatusBarModelUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        StatusBarModelUtils.setStatusBar(this, false, false);
        super.onCreate(savedInstanceState);
    }
}