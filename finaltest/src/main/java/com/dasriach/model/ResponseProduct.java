package com.dasriach.model;

import java.util.List;

public class ResponseProduct {

    private List<ProductRequest> offer;

    public void setOffer(List<ProductRequest> offer) {
        this.offer = offer;
    }

    public List<ProductRequest> getOffer(){
        return offer;
    }
}
