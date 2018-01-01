package com.pfl.module2.di.module2;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.FragmentScope;
import com.pfl.module2.ui.fragment.Module2Fragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/12/30 0030.
 */

@FragmentScope
@Component(modules = Module2Module.class, dependencies = AppComponent.class)
public interface Module2Component {

    void inject(Module2Fragment fragment);
}
