package com.countryfinder.packages.validation.rules;

import com.countryfinder.packages.validation.PhoneNumberValidationException;
import org.springframework.stereotype.Component;


@Component
public class PlusSignValidationRule implements ValidationRule{
    @Override
    public void validate(String number) throws PhoneNumberValidationException {
        if (number.charAt(0) != '+'){
            throw new PhoneNumberValidationException("Number should have country code.");
        }
    }
}
