package com.solstice.solsticechallenge.ui.mvp.presenter;

import android.support.annotation.Nullable;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.ui.adapter.ContactsAdapter;
import com.solstice.solsticechallenge.ui.event.ContactsFetchedEvent;
import com.solstice.solsticechallenge.ui.mvp.model.MainModel;
import com.solstice.solsticechallenge.ui.mvp.view.MainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class MainPresenter implements ContactsAdapter.ItemPressedListener {

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

    public void loadData() {
        if (view != null) {
            view.showLoading();
            if (model.contactsDatabaseIsEmpty()) {
                model.fetchContacts();
            } else {
                showContacts();
            }
        }
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

    private void showContacts() {
        if (view != null) {
            view.showContacts(model.getFavoriteContactsList(), model.getNonFavoriteContactsList());
            view.hideLoading();
        }
    }

    public void subscribeBus() {
        bus.register(this);
    }

    public void unsubscribeBus() {
        bus.unregister(this);
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
}
