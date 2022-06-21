package com.countryfinder.packages.validation.rules;

import com.countryfinder.packages.validation.PhoneNumberValidationException;
import org.springframework.stereotype.Component;


@Component
public class MinLengthValidationRule implements ValidationRule {
    @Override
    public void validate(String number) throws PhoneNumberValidationException {
        if (number.length() < 7){
            throw new PhoneNumberValidationException("Phone number should be at least 7 digits.");
        }
    }
}
