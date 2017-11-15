package com.solstice.solsticechallenge.network.api;

import com.solstice.solsticechallenge.data.entities.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactsApi {

    @GET("contacts.json")
    Call<List<Contact>> getContactsList();
}
