package com.pfl.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;

import com.pfl.common.utils.StatusBarUtil;
import com.pfl.component.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.immersive(this);
        super.onCreate(savedInstanceState);
        //setContextView();
    }

    private void setContextView() {
        View view = LayoutInflater.from(this).inflate(R.layout.base_layout, null, false);
        if (isNeedTitleBar()) {
            ViewStub titleStub = view.findViewById(R.id.titleStub);
            titleStub.inflate();
        }
        ViewStub viewStub = view.findViewById(R.id.viewStub);
        viewStub.setLayoutResource(getContextView());
        viewStub.inflate();
        setContentView(view);
    }

    protected abstract int getContextView();

    protected abstract boolean isNeedTitleBar();
}