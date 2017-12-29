package com.pfl.common.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by mertsimsek on 13/01/17.
 */

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

    Application getApplication();

    Retrofit getRetrofit();

}