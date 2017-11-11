package com.solstice.solsticechallenge.mvp.presenter;

import android.support.annotation.Nullable;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.mvp.view.MainView;

public class MainPresenter {

    @Nullable
    private MainView view;

    public void setView(MainView view) {
        this.view = view;
    }

    public void start() {
        if (view != null) {
            view.showMessage(R.string.app_name);
        }
    }
}
