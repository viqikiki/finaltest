package com.dasriach.model;

import java.util.List;

public class ResponseProduct {

    private List<ProductRequest> product;
    private Trans transaction;

    public List<ProductRequest> getProduct(){
        return product;
    }

    public Trans getTransaction(){
        return transaction;
    }
}
