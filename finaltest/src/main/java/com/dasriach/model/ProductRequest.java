package com.dasriach.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Products")
public class ProductRequest implements Serializable {
    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private String productPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
