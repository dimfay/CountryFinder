package com.countryfinder.packages.validation.rules;

import com.countryfinder.packages.validation.PhoneNumberValidationException;

public interface ValidationRule {
    void validate(String number) throws PhoneNumberValidationException;
}
