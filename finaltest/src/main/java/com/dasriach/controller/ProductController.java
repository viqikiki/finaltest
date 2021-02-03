package com.dasriach.controller;
import com.dasriach.model.ProductRequest;
import com.dasriach.model.ResponseProduct;
import org.hibernate.annotations.Cache;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin()
@RequestMapping("/api")
@EnableCaching
public class ProductController {
    private static String uri = "http://dev3.dansmultipro.co.id/mock/preprod-web/scrt/esb/v1/offer/reseller?menu_id=ML3_DP_03";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/product")
    @Cacheable(value = "Products", key = "{#id, #name, #price}")
    public @ResponseBody List<ProductRequest> getPro(){
            final RestTemplate restTemplate = new RestTemplate();

            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);

        ResponseEntity<ResponseProduct> respond = restTemplate.getForEntity(uri, ResponseProduct.class);
        ResponseProduct produk = respond.getBody();
        return produk.getOffer();
    }
}

