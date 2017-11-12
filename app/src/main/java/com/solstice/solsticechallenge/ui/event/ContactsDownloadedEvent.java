package com.solstice.solsticechallenge.ui.event;

import com.solstice.solsticechallenge.pojo.Contact;

import java.util.List;

public class ContactsDownloadedEvent extends BusEvent {

    private List<Contact> contactList;

    public ContactsDownloadedEvent() {
        success = false;
    }

    public ContactsDownloadedEvent(List<Contact> contactList) {
        success = true;
        this.contactList = contactList;
    }

    public List<Contact> getContactList() {
        return contactList;
    }
}
