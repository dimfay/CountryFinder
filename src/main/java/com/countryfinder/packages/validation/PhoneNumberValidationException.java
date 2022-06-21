package com.countryfinder.packages.validation;

import lombok.Getter;

@Getter
public class PhoneNumberValidationException extends RuntimeException{
    private final String message;
    public PhoneNumberValidationException(String message){
        this.message = message;
    }
}
