package com.pfl.common.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mertsimsek on 13/01/17.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {
}