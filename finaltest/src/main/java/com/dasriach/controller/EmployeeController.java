package com.dasriach.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin()
public class EmployeeController {
    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
    public String getEmployees() {
        return "Login success!";
    }
}