package com.countryfinder.packages.controllers.rest;

import com.countryfinder.packages.dto.FindCountryRequest;
import com.countryfinder.packages.dto.FindCountryResponse;
import com.countryfinder.packages.services.CountryCodeDecryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/find-country")
public class MainController {
    @Autowired
    CountryCodeDecryptor countryCodeDecryptor;

    @PostMapping
    public FindCountryResponse findCountry(@Valid @RequestBody FindCountryRequest request){
        return countryCodeDecryptor.decrypt(request);
    }

}
