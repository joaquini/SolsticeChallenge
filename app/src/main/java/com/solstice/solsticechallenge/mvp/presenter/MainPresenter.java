package com.solstice.solsticechallenge.mvp.presenter;

import android.support.annotation.Nullable;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.event.ContactsDownloadedEvent;
import com.solstice.solsticechallenge.mvp.model.MainModel;
import com.solstice.solsticechallenge.mvp.view.MainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class MainPresenter {

    @Nullable
    private MainView view;
    private MainModel model;
    private EventBus bus;

    @Inject
    public MainPresenter (MainModel model, EventBus bus) {
        this.model = model;
        this.bus = bus;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public void loadData() {
        if (view != null) {
            model.getContacts();
        }
    }

    @Subscribe
    public void onContactsDownloaded(ContactsDownloadedEvent event) {
        if (view != null) {
            if (event.isSuccess()) {
                view.showContacts(event.getContactList());
            } else {
                view.showMessage(R.string.error_getting_contacts);
            }
        }
    }

    public void subscribeBus() {
        bus.register(this);
    }

    public void unsubscribeBus() {
        bus.unregister(this);
    }
}
