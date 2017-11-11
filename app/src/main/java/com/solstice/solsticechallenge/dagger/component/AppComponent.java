package com.solstice.solsticechallenge.dagger.component;

import com.solstice.solsticechallenge.MainActivity;
import com.solstice.solsticechallenge.dagger.module.AppModule;
import com.solstice.solsticechallenge.dagger.module.NetworkModule;
import com.solstice.solsticechallenge.dagger.module.PresenterModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity target);
}
