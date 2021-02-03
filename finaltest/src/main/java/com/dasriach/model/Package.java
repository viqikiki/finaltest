package com.dasriach.model;


import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Package implements Serializable {
    @Id
    private String token;

    private String msisdn;
    private String productId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}