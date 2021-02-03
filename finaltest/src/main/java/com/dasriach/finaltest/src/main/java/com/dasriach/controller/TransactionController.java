package com.dasriach.controller;

import com.dasriach.model.Package;
import com.dasriach.model.Trans;
import com.dasriach.repository.PackageRepository;
import com.dasriach.repository.ProductTransRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductTransRepository productTransRepository;

    @GetMapping(value = "/transaction/history")
    public List<Trans> getTransaction(){
        return productTransRepository.findAll();
    }
    @GetMapping("/transaction/history/{id}")
    public Optional<Trans> findById(@PathVariable(value = "id")String id){
        return productTransRepository.findById(id);
    }
}
