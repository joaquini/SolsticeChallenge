package com.solstice.solsticechallenge.ui.mvp.model;

import android.support.annotation.Nullable;

import com.solstice.solsticechallenge.ui.mvp.model.dao.ContactsDao;
import com.solstice.solsticechallenge.ui.mvp.model.entities.Address;
import com.solstice.solsticechallenge.ui.mvp.model.entities.Contact;
import com.solstice.solsticechallenge.ui.mvp.model.entities.Phone;

import javax.inject.Inject;

public class ContactDetailsModel {

    @Nullable
    private Contact contact;
    private ContactsDao contactsDao;

    @Inject
    public ContactDetailsModel(ContactsDao contactsDao) {
        this.contactsDao = contactsDao;
    }

    public void fetchContact(String contactId) {
        contact = findContact(contactId);
    }

    private Contact findContact(String contactId) {
        return contactsDao.findContact(contactId);
    }

    public String getName() {
        return contact != null ? contact.getName() : null;
    }

    public String getCompanyName() {
        return contact != null ? contact.getCompanyName() : null;
    }

    public String getLargeImageURL() {
        return contact != null ? contact.getLargeImageURL() : null;
    }

    public Phone getPhone() {
        return contact != null ? contact.getPhone() : null;
    }

    public Address getAddress() {
        return contact != null ? contact.getAddress() : null;
    }

    public String getBirthdate() {
        return contact != null ? contact.getBirthdate() : null;
    }

    public String getEmailAddress() {
        return contact != null ? contact.getEmailAddress() : null;
    }

    public boolean isFavorite() {
        return contact != null && contact.isFavorite();
    }

    public void setFavorite() {
        if (contact != null) {
            contact.setFavorite(true);
            contactsDao.updateContact(contact);
        }
    }

    public void setUnfavorite() {
        if (contact != null) {
            contact.setFavorite(false);
            contactsDao.updateContact(contact);
        }
    }
}
