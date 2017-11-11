package com.solstice.solsticechallenge;

import android.app.Application;

import com.solstice.solsticechallenge.dagger.component.AppComponent;
import com.solstice.solsticechallenge.dagger.component.DaggerAppComponent;
import com.solstice.solsticechallenge.dagger.module.AppModule;

public class SolsticeApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(SolsticeApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
