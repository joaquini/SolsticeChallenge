package com.solstice.solsticechallenge.ui.adapter.sections;

import android.support.annotation.Nullable;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.ui.images.ImageLoader;
import com.solstice.solsticechallenge.ui.mvp.model.entities.Contact;

import java.util.List;

import javax.inject.Inject;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

public class FavoritesSection extends ContactsAbstractSection {

    private List<Contact> contactList;
    @Nullable
    private ItemPressedListener listener;

    @Inject
    public FavoritesSection(ImageLoader imageLoader) {
        super(new SectionParameters
                        .Builder(R.layout.contact_list_item)
                        .headerResourceId(R.layout.contact_list_favorite_header)
                        .build(),
                imageLoader);
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public void setListener(ItemPressedListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<Contact> getContactList() {
        return contactList;
    }

    @Override
    protected ItemPressedListener getItemPressedListener() {
        return listener;
    }
}
