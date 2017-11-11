package com.solstice.solsticechallenge.mvp.presenter;

import android.support.annotation.Nullable;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.mvp.model.MainModel;
import com.solstice.solsticechallenge.mvp.view.MainView;

import javax.inject.Inject;

public class MainPresenter {

    @Nullable
    private MainView view;
    private MainModel model;

    @Inject
    public MainPresenter (MainModel model) {
        this.model = model;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public void start() {
        if (view != null) {
            view.showMessage(R.string.app_name);
            model.getContacts();
        }
    }
}
