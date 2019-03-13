package com.pfl.module_user.init;

import android.util.Log;

import com.pfl.common.base.BaseApplication;
import com.pfl.common.listener.IApplicationLike;

public class UserApplicationLike implements IApplicationLike {
    @Override
    public void init(BaseApplication application) {
        Log.e("IApplicationLike","user module start init == " + application.getPackageName());
    }
}
