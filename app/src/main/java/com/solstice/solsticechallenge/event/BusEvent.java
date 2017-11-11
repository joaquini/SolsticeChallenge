package com.solstice.solsticechallenge.event;

public abstract class BusEvent {

    protected boolean success;

    public boolean isSuccess() {
        return success;
    }
}
