package com.solstice.solsticechallenge.ui.contactsmain.adapter.sections;

import android.support.annotation.Nullable;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.ui.images.ImageLoader;
import com.solstice.solsticechallenge.data.entities.Contact;

import java.util.List;

import javax.inject.Inject;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

public class NonFavoritesSection extends ContactsAbstractSection {

    private List<Contact> contactList;
    @Nullable
    private ItemPressedListener listener;

    @Inject
    public NonFavoritesSection(ImageLoader imageLoader) {
        super(new SectionParameters
                        .Builder(R.layout.contact_list_item)
                        .headerResourceId(R.layout.contact_list_non_favorite_header)
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
