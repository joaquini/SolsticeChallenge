package com.solstice.solsticechallenge.ui.mvp.presenter;

import android.support.annotation.Nullable;

import com.solstice.solsticechallenge.R;
import com.solstice.solsticechallenge.ui.mvp.model.ContactDetailsModel;
import com.solstice.solsticechallenge.ui.mvp.view.ContactDetailsView;

import javax.inject.Inject;

public class ContactDetailsPresenter {

    @Nullable
    private ContactDetailsView view;
    private ContactDetailsModel model;

    @Inject
    public ContactDetailsPresenter(ContactDetailsModel model) {
        this.model = model;
    }

    public void setView(ContactDetailsView view) {
        this.view = view;
        view.setTitle(R.string.contact_details_title);
    }

    public void onResume() {
        loadData();
    }

    public void onFavoriteIconPressed() {
        if (model.isFavorite()) {
            model.setUnfavorite();
        } else {
            model.setFavorite();
        }
        updateFavoriteIcon();
    }

    private void loadData() {
        if (view != null) {
            if (view.getContactId() != null) {
                model.fetchContact(view.getContactId());
                showContactDetails();
            } else {
                view.showMessage(R.string.error_getting_contact_details);
            }
        }
    }

    private void showContactDetails() {
        if (view != null) {
            view.showName(model.getName());
            view.showCompanyName(model.getCompanyName());
            view.showProfileImage(model.getLargeImageURL());
            view.showPhone(model.getPhone());
            view.showAddress(model.getAddress());
            view.showBirthDate(model.getBirthdate());
            view.showEmail(model.getEmailAddress());

            updateFavoriteIcon();
        }
    }

    private void updateFavoriteIcon() {
        if (view != null) {
            if (model.isFavorite()) {
                view.setFavoritedIcon();
            } else {
                view.setUnfavoritedIcon();
            }
        }
    }

    public void onArrowBackPressed() {
        if (view != null) {
            view.close();
        }
    }
}
