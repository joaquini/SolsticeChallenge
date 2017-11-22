package com.solstice.solsticechallenge.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.solstice.solsticechallenge.data.db.ContactsDatabase;
import com.solstice.solsticechallenge.data.entities.Contact;

import java.util.List;

import javax.inject.Inject;


public class ContactsViewModel extends AndroidViewModel {

    private final LiveData<List<Contact>> favoriteContactList;
    private ContactsDatabase contactsDatabase;

    @Inject
    public ContactsViewModel(Context context, ContactsDatabase contactsDatabase) {
        super((Application) context);
        this.contactsDatabase = contactsDatabase;
        favoriteContactList = contactsDatabase.contactsDao().findFavoriteContacts();
    }
}
