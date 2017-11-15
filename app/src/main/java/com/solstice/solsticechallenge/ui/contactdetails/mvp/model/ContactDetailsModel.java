package com.solstice.solsticechallenge.ui.contactdetails.mvp.model;

import android.support.annotation.Nullable;

import com.solstice.solsticechallenge.data.dao.ContactsDao;
import com.solstice.solsticechallenge.data.entities.Address;
import com.solstice.solsticechallenge.data.entities.Contact;
import com.solstice.solsticechallenge.data.entities.Phone;
import com.solstice.solsticechallenge.ui.contactdetails.sections.ContactDetailsSection;
import com.solstice.solsticechallenge.utils.AddressFormatter;
import com.solstice.solsticechallenge.utils.BirthdateFormatter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.text.TextUtils.isEmpty;

public class ContactDetailsModel {

    @Nullable
    private Contact contact;
    private ContactsDao contactsDao;
    private AddressFormatter addressFormatter;
    private BirthdateFormatter birthdateFormatter;

    @Inject
    public ContactDetailsModel(ContactsDao contactsDao, AddressFormatter addressFormatter, BirthdateFormatter birthdateFormatter) {
        this.contactsDao = contactsDao;
        this.addressFormatter = addressFormatter;
        this.birthdateFormatter = birthdateFormatter;
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

    private Phone getPhone() {
        return contact != null ? contact.getPhone() : null;
    }

    private String getHomePhone() {
        return getPhone() != null ? getPhone().getHome() : null;
    }

    private String getMobilePhone() {
        return getPhone() != null ? getPhone().getMobile() : null;
    }

    private String getWorkPhone() {
        return getPhone() != null ? getPhone().getWork() : null;
    }

    private Address getAddress() {
        return contact != null ? contact.getAddress() : null;
    }

    private String getBirthdate() {
        return contact != null ? contact.getBirthdate() : null;
    }

    private String getEmailAddress() {
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

    public List<ContactDetailsSection> getContactDetailsSections() {
        List<ContactDetailsSection> sections = new ArrayList<>();
        add(new ContactDetailsSection("Phone", getHomePhone(), "Home"), sections);
        add(new ContactDetailsSection("Phone", getMobilePhone(), "Mobile"), sections);
        add(new ContactDetailsSection("Phone", getWorkPhone(), "Work"), sections);
        add(new ContactDetailsSection("Address", addressFormatter.getFormattedAddress(getAddress())), sections);
        add(new ContactDetailsSection("Birth Date", birthdateFormatter.getFormattedBirthdate(getBirthdate())), sections);
        add(new ContactDetailsSection("Email", getEmailAddress()), sections);

        return sections;
    }

    private void add(ContactDetailsSection section, List<ContactDetailsSection> sections) {
        if (!isEmpty(section.getSectionContent())) {
            sections.add(section);
        }
    }
}
