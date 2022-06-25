package com.countryfinder.packages.domain;

import com.countryfinder.packages.validation.CoreError;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Data
public class PhoneNumber {
    private Collection<CoreError> errors;
    private String number;
    private String country;

    public PhoneNumber(String number){
        this.number = number;
    }

    public PhoneNumber() {

    }
}
