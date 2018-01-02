package com.pfl.component.di.main;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.component.ui.activity.MainActivity;
import com.pfl.module2.ui.fragment.Module2Fragment;

import dagger.Component;

/**
 * Created by rocky on 2017/12/29.
 */

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
