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
        UUID uuid = Generators.randomBasedGenerator().generate();
        pack.setMsisdn(uuid.toString());
        packageRepository.savePackage(pack);
        response.setStatus(HttpServletResponse.SC_OK);
        return pack;
    }
    @PostMapping("/package-activation/confirmation")
    public ResponseEntity<String> confirmPackage(@RequestBody PackageActivation packageActivation){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Activation Response", "Package Response");
        return new ResponseEntity<String>("Package has been Activated", responseHeaders, HttpStatus.OK);
    }
    @GetMapping("/package-activation/confirmation")
    public List<Package> getAllPackage(){
        return packageRepository.findAll();
    }
    @RequestMapping(value = "/order/reseller", method = RequestMethod.POST)
    public String createProducts(@RequestBody Reseller reseller) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Reseller> entity = new HttpEntity<Reseller>(reseller,headers);

        return restTemplate.exchange(
                "http://dev3.dansmultipro.co.id/mock/sit-web/secure/esb/v1/order/reseller", HttpMethod.POST, entity, String.class).getBody();
    }
}

