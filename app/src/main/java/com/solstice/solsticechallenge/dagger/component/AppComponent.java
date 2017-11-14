package com.solstice.solsticechallenge.dagger.component;

import com.solstice.solsticechallenge.dagger.module.ImageLoadingModule;
import com.solstice.solsticechallenge.dagger.module.RoomDatabaseModule;
import com.solstice.solsticechallenge.dagger.module.SectionsModule;
import com.solstice.solsticechallenge.ui.activities.MainActivity;
import com.solstice.solsticechallenge.dagger.module.AppModule;
import com.solstice.solsticechallenge.dagger.module.BusModule;
import com.solstice.solsticechallenge.dagger.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, BusModule.class, RoomDatabaseModule.class, ImageLoadingModule.class, SectionsModule.class})
public interface AppComponent {

    void inject(MainActivity target);
}
