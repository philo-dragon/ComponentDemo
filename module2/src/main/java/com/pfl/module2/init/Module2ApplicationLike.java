package com.pfl.module2.init;

import android.util.Log;

import com.pfl.common.listener.IApplicationLike;

public class Module2ApplicationLike implements IApplicationLike {
    @Override
    public void init() {
        Log.e("IApplicationLike", "Module2 module start init");
    }
}
