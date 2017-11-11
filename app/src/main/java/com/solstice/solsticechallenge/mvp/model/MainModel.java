package com.solstice.solsticechallenge.mvp.model;

import com.solstice.solsticechallenge.event.ContactsDownloadedEvent;
import com.solstice.solsticechallenge.network.api.ContactsApi;
import com.solstice.solsticechallenge.pojo.Contact;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel {

    private final static int RESPONSE_CODE_OK = 200;

    private EventBus bus;
    private ContactsApi contactsApi;

    @Inject
    public MainModel(ContactsApi contactsApi, EventBus bus) {
        this.contactsApi = contactsApi;
        this.bus = bus;
    }

    public void getContacts() {
        contactsApi.getContactsList().enqueue(
                new Callback<List<Contact>>() {
                    @Override
                    public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                        if (response.code() != RESPONSE_CODE_OK) {
                            bus.post(new ContactsDownloadedEvent());
                        } else {
                            System.out.println(response.body());
                            bus.post(new ContactsDownloadedEvent(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Contact>> call, Throwable t) {
                        t.printStackTrace();
                        bus.post(new ContactsDownloadedEvent());
                    }
                }
        );
    }
}
