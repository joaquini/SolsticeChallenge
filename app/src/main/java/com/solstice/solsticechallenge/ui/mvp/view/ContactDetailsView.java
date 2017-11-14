package com.solstice.solsticechallenge.ui.mvp.view;

import android.support.annotation.StringRes;

import com.solstice.solsticechallenge.ui.mvp.model.entities.Address;
import com.solstice.solsticechallenge.ui.mvp.model.entities.Phone;

public interface ContactDetailsView {
    String getContactId();

    void setTitle(@StringRes int stringId);

    void showProfileImage(String url);

    void setFavoritedIcon();

    void setUnfavoritedIcon();

    void showMessage(@StringRes int stringId);

    void showName(String name);

    void showCompanyName(String companyName);

    void showPhone(Phone phone);

    void showAddress(Address address);

    void showBirthDate(String birthdate);

    void showEmail(String emailAddress);

    void close();
}
