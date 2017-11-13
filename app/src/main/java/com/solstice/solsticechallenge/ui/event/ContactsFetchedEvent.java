package com.solstice.solsticechallenge.ui.event;

public class ContactsFetchedEvent extends BusEvent {

    public ContactsFetchedEvent(boolean success) {
        this.success = success;
    }
}
