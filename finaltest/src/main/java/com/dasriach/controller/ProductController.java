package com.dasriach.controller;
import com.dasriach.model.ProductRequest;
import com.dasriach.model.ResponseProduct;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin()
@RequestMapping("/api")
public class ProductController {

    private static String uri = "http://dev3.dansmultipro.co.id/mock/preprod-web/scrt/esb/v1/offer/reseller?menu_id=ML3_DP_03";

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/product")
    public @ResponseBody List<ProductRequest> getPro(){
        ResponseEntity<ResponseProduct> respond = restTemplate.getForEntity(uri, ResponseProduct.class);
        ResponseProduct produk = respond.getBody();
        return produk.getProduct();
    }
}

