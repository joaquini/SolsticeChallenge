package com.solstice.solsticechallenge.ui.mvp.model;

import com.solstice.solsticechallenge.network.api.ContactsApi;
import com.solstice.solsticechallenge.ui.event.ContactsFetchedEvent;
import com.solstice.solsticechallenge.ui.mvp.model.dao.ContactsDao;
import com.solstice.solsticechallenge.ui.mvp.model.entities.Contact;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.solstice.solsticechallenge.Constants.RESPONSE_CODE_OK;

public class MainModel {

    private ContactsDao contactsDao;
    private EventBus bus;
    private ContactsApi contactsApi;

    @Inject
    public MainModel(ContactsApi contactsApi, EventBus bus, ContactsDao contactsDao) {
        this.contactsApi = contactsApi;
        this.bus = bus;
        this.contactsDao = contactsDao;
    }

    public boolean contactsDatabaseIsEmpty() {
        return contactsDao.countContacts() == 0;
    }

    public void fetchContacts() {
        contactsApi.getContactsList().enqueue(
                new Callback<List<Contact>>() {
                    @Override
                    public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                        if (RESPONSE_CODE_OK == response.code()) {
                            contactsDao.insertContacts(response.body());
                            postFetchedContacts(true);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Contact>> call, Throwable t) {
                        t.printStackTrace();
                        postFetchedContacts(false);
                    }
                }
        );
    }

    private void postFetchedContacts(boolean success) {
        bus.post(new ContactsFetchedEvent(success));
    }

    public List<Contact> getFavoriteContactsList() {
        return contactsDao.findFavoriteContacts();
    }

    public List<Contact> getNonFavoriteContactsList() {
        return contactsDao.findNonFavoriteContacts();
    }

    public void deleteAllContactsFromDatabase() {
        contactsDao.deleteAllContacts();
    }
}
