package com.dasriach.controller;

import com.dasriach.model.Trans;
import com.dasriach.repository.ProductTransRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
