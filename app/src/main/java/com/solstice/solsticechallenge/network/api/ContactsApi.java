package com.solstice.solsticechallenge.network.api;

import com.solstice.solsticechallenge.pojo.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactsApi {

    @GET("?")
    Call<List<Contact>> getContactsList();
}
