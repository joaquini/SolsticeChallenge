package com.solstice.solsticechallenge.mvp.view;

import android.support.annotation.StringRes;

import com.solstice.solsticechallenge.pojo.Contact;

import java.util.List;

public interface MainView {
    void showMessage(@StringRes int stringId);

    void showContacts(List<Contact> contactList);
}
