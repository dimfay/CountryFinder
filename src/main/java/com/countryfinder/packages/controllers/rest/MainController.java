package com.countryfinder.packages.controllers.rest;

import com.countryfinder.packages.domain.PhoneNumber;
import com.countryfinder.packages.services.CodeDecryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/find-country")
public class MainController {
    @Autowired
    CodeDecryptor codeDecryptor;

    @PostMapping
    public PhoneNumber findCountry(@RequestBody PhoneNumber phoneNumber){
        return codeDecryptor.decrypt(phoneNumber);
    }
}
