package com.countryfinder.packages.validation.rules;

import com.countryfinder.packages.validation.PhoneNumberValidationException;
import org.springframework.stereotype.Component;

@Component
public class MaxLengthValidationRule implements ValidationRule {
    public void validate(String number) throws PhoneNumberValidationException {
        //+ included
        if (number.length() > 16){
            throw new PhoneNumberValidationException("Phone number should be at most 15 digits.");
        }
    }
}
