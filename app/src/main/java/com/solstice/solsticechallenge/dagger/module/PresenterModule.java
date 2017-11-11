package com.solstice.solsticechallenge.dagger.module;

import com.solstice.solsticechallenge.mvp.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }
}
