package com.pfl.common.di;

import android.app.Application;

import com.pfl.common.di.scope.FragmentScope;
import com.pfl.common.http.RetrofitService;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by mertsimsek on 13/01/17.
 */

@FragmentScope
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

    Application getApplication();

    RetrofitService getRetrofitService();

}