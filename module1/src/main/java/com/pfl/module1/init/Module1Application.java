package com.pfl.module1.init;

import android.util.Log;

import com.pfl.common.base.BaseApplication;
import com.pfl.common.listener.IApplicationLike;

public class Module1Application implements IApplicationLike {
    @Override
    public void init(BaseApplication application) {
        Log.e("IApplicationLike","Module1 module start init == " + application.getPackageName());
    }
}
