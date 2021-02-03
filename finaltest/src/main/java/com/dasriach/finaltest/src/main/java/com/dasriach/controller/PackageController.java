package com.dasriach.controller;

import com.dasriach.model.*;
import com.dasriach.model.Package;
import com.dasriach.repository.PackageRepository;
import com.dasriach.repository.ProductRepository;
import com.dasriach.repository.ProductTransRepository;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PackageController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private ProductTransRepository productTransRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/package-activation")
    public Package savePackage(@RequestBody Package pack, HttpServletResponse response){
        UUID token = Generators.randomBasedGenerator().generate();
        pack.setToken(token.toString());
        packageRepository.save(pack);
        return pack;
    }
/*
    @PostMapping("/package-activation/confirmation")
    public ResponseEntity<String> confirmPackage(@RequestBody PackageActivation packageActivation){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Activation Response", "Package Response");
        return new ResponseEntity<String>("Package has been Activated", responseHeaders, HttpStatus.OK);
    }
*/


    @GetMapping("/package-activation/{id}")
    public Optional<Package> findById(@PathVariable(value = "id")String id){
        return packageRepository.findById(id);
    }

    @GetMapping("/package-activation/confirmation")
    public Iterable<Package> getAllPackage(){
        return packageRepository.findAll();
    }


    @PostMapping(value = "/package-activation/confirmation")
    public ActResponse actProduct(@RequestBody PackageActivation packageActivation) {
        Optional<Package> pack = packageRepository.findById(packageActivation.getToken());
        String id = pack.get().getProduct_id();
        Optional<ProductRedis> productRedis = productRepository.findById(id);

        ActResponse res = new ActResponse();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PackageActivation> entity = new HttpEntity<PackageActivation>(packageActivation,headers);
        ResponseEntity<ActResponse> responseEntity = restTemplate.exchange(
                "http://dev3.dansmultipro.co.id/mock/sit-web/secure/esb/v1/order/reseller",
                HttpMethod.POST,
                entity,
                ActResponse.class);
        String activ_id = responseEntity.getBody().getTransaction().getTransactionId();
        Trans trans = new Trans();
        trans.setTransaction_id(activ_id);
        trans.setMsisdn(pack.get().getMsisdn());
        trans.setProduct_id(productRedis.get().getId());
        trans.setProduct_name(productRedis.get().getName());
        trans.setProduct_price(productRedis.get().getPrice());
        trans.setCreated_by(1);
        productTransRepository.save(trans);
        return responseEntity.getBody();
    }
    /*@PostMapping(value = "/package-activation/confirmation")
    public String actProduct(@RequestBody PackageActivation packageActivation) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PackageActivation> entity = new HttpEntity<PackageActivation>(packageActivation,headers);

        return restTemplate.exchange(
                "http://dev3.dansmultipro.co.id/mock/sit-web/secure/esb/v1/order/reseller",
                HttpMethod.POST,
                entity,
                String.class).getBody();*/
}

