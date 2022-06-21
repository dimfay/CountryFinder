package com.countryfinder.packages.controllers.rest;

import com.countryfinder.packages.services.CodeDecriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/findcountry")
public class MainController {
    @Autowired
    CodeDecriptor codeDecriptor;

    @PostMapping("/{number}")
    public Collection<String> findCountry(@PathVariable(name = "number") String phoneNumber){
        return codeDecriptor.decript(phoneNumber);
    }
}
