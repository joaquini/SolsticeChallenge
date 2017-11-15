package com.solstice.solsticechallenge.ui.contactdetails.mvp.view;

import android.support.annotation.StringRes;

import com.solstice.solsticechallenge.ui.contactdetails.sections.ContactDetailsSection;

import java.util.List;

public interface ContactDetailsView {
    String getContactId();

    void setTitle(@StringRes int stringId);

    void setFavoritedIcon(@StringRes int stringId);

    void setUnfavoritedIcon(@StringRes int stringId);

    void showMessage(@StringRes int stringId);

    void showName(String name);

    void showCompanyName(String companyName);

    void showProfileImage(String largeImageURL);

    void close();

    void showDetailsSections(List<ContactDetailsSection> sections);

}
