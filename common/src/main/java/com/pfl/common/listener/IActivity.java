package com.pfl.common.listener;

import com.pfl.common.di.AppComponent;

/**
 * Created by rocky on 2018/1/3.
 */

/**
 * Activity公共函数接口
 * 按方法书写顺序执行
 */
public interface IActivity {

    void componentInject(AppComponent appComponent);

    int getContextView();

    void initView();

    void initEvent();

    void initData();
}
