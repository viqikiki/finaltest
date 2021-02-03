package com.dasriach.model;

import java.util.List;

public class ResponseProduct {

    private List<ProductRequest> offer;
    private Transaksi transaksi;

    public void setOffer(List<ProductRequest> offer) {
        this.offer = offer;
    }

    public List<ProductRequest> getOffer(){
        return offer;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }
}
