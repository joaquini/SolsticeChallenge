package com.solstice.solsticechallenge.ui.contactsmain.mvp.view;

import android.support.annotation.StringRes;

import com.solstice.solsticechallenge.data.entities.Contact;

import java.util.List;

public interface MainView {
    void setTitle(@StringRes int stringId);

    void showLoading();

    void hideLoading();

    void showMessage(@StringRes int stringId);

    void showContacts(List<Contact> contactList, List<Contact> nonFavoriteContactsList);

    void displayDeleteContactsConfirmationDialog();

    void navigateToContactDetails(String id);

    void removeAllSections();
}
