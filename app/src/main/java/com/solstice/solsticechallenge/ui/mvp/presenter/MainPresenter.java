package com.solstice.solsticechallenge.ui.mvp.presenter;

import android.support.annotation.Nullable;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.ui.adapter.sections.ContactsAbstractSection;
import com.solstice.solsticechallenge.ui.event.ContactsFetchedEvent;
import com.solstice.solsticechallenge.ui.mvp.model.MainModel;
import com.solstice.solsticechallenge.ui.mvp.view.MainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class MainPresenter implements ContactsAbstractSection.ItemPressedListener {

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
        view.setTitle(R.string.main_title);
    }

    public void onResume() {
        registerBus();
        loadData();
    }

    public void onPause() {
        unregisterBus();
    }

    public void onRefresh() {
        loadData();
    }

    public void onDeleteContactsItemPressed() {
        if (view != null) {
            view.displayDeleteContactsConfirmationDialog();
        }
    }

    public void onDeleteContactsConfirmed() {
        model.deleteAllContactsFromDatabase();
        showContacts();
    }

    @Override
    public void onItemPressed(String id) {
        if (view != null) {
            view.navigateToContactDetails(id);
        }
    }

    private void loadData() {
        if (view != null) {
            view.showLoading();
            if (model.contactsDatabaseIsEmpty()) {
                model.fetchContacts();
            } else {
                showContacts();
            }
        }
    }

    private void showContacts() {
        if (view != null) {
            view.showContacts(model.getFavoriteContactsList(), model.getNonFavoriteContactsList());
            view.hideLoading();
        }
    }

    private void registerBus() {
        bus.register(this);
    }

    private void unregisterBus() {
        bus.unregister(this);
    }

    @Subscribe
    public void onContactsDownloaded(ContactsFetchedEvent event) {
        if (view != null) {
            view.hideLoading();
            if (event.isSuccess()) {
                showContacts();
            } else {
                view.showMessage(R.string.error_getting_contacts);
            }
        }
    }
}
