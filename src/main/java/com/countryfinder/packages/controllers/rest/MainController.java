package com.countryfinder.packages.controllers.rest;

import com.countryfinder.packages.domain.PhoneNumber;
import com.countryfinder.packages.services.CodeDecriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/find-country")
public class MainController {
    @Autowired
    CodeDecriptor codeDecriptor;

    @PostMapping
    public PhoneNumber findCountry(@RequestBody PhoneNumber phoneNumber){
        return codeDecriptor.decrypt(phoneNumber);
    }
}
