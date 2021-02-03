package com.dasriach.controller;

import com.dasriach.model.Package;
import com.dasriach.model.PackageActivation;
import com.dasriach.model.Reseller;
import com.dasriach.repository.PackageRepository;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PackageController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private PackageRepository packageRepository;


    @PostMapping("/package-activation")
    public Package savePackage(@RequestBody Package pack, HttpServletResponse response){
        UUID token = Generators.randomBasedGenerator().generate();
        pack.setToken(token.toString());
        packageRepository.savePackage(pack);
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
    public Package findById(@PathVariable(value = "id")String id){
        return packageRepository.findById(id);
    }

    @GetMapping("/package-activation/confirmation")
    public List<Package> getAllPackage(){
        return packageRepository.findAll();
    }

    @PostMapping(value = "/package-activation/confirmation")
    public String actProduct(@RequestBody PackageActivation packageActivation) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PackageActivation> entity = new HttpEntity<PackageActivation>(packageActivation,headers);

        return restTemplate.exchange(
                "http://dev3.dansmultipro.co.id/mock/sit-web/secure/esb/v1/order/reseller",
                HttpMethod.POST,
                entity,
                String.class).getBody();
    }
}

