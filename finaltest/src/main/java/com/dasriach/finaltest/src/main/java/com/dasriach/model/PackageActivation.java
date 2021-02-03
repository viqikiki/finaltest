package com.dasriach.model;

import java.io.Serializable;

public class PackageActivation implements Serializable {
    private String token;
    private String pin;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
