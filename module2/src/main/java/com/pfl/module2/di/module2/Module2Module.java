package com.pfl.module2.di.module2;

import com.pfl.common.http.RetrofitService;
import com.pfl.module2.mvp.module2.Module2Persenter;
import com.pfl.module2.mvp.module2.Module2View;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class Module2Module {

    private Module2View view;

    public Module2Module(Module2View module2View) {
        this.view = module2View;
    }

    @Provides
    Module2View provideModule2View() {
        return view;
    }

   @Provides
    Module2Persenter provideModule2Persenter(RetrofitService service, Module2View view) {

        return new Module2Persenter(service, view);
    }

}
