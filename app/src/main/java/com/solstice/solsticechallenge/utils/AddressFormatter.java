package com.solstice.solsticechallenge.utils;

import com.solstice.solsticechallenge.data.entities.Address;

import javax.inject.Inject;

public class AddressFormatter {

    @Inject
    public AddressFormatter() {
    }

    public String getFormattedAddress(Address address) {
        return address.getStreet() + "\n" +
                address.getCity() + ", " + address.getState() + " " + address.getZipCode() + ", " + address.getCountry();
    }
}
