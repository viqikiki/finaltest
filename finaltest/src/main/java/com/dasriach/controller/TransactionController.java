package com.dasriach.controller;

import com.dasriach.model.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/transaction/history")
    public String transHistory(@RequestBody Trans trans) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Trans> entity = new HttpEntity<Trans>(trans,headers);

        return restTemplate.exchange(
                "http://dev3.dansmultipro.co.id/mock/sit-web/secure/esb/v1/order/reseller", HttpMethod.POST, entity, String.class).getBody();
    }
}
