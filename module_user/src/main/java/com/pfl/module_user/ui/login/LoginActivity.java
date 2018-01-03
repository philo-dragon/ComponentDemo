package com.pfl.module_user.ui.login;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.component.R;

@Route(path = RouteUtils.MODULE_USER_LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity {


    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public int getContextView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }
}
