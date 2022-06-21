package com.countryfinder.packages.validation;

import com.countryfinder.packages.validation.rules.ValidationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PhoneNumberValidator {
    @Autowired
    List<ValidationRule> validationRules;

    public List<CoreError> validate(String phoneNumber){
        List<CoreError> errors = new ArrayList<>();
        if (phoneNumber == null){
            errors.add(new CoreError("Phone number should not be null."));
            return errors;
        }

        for (ValidationRule rule : validationRules){
            try{
                rule.validate(phoneNumber);
            } catch (Exception e){
                errors.add(new CoreError(e.getMessage()));
            }
        }

        return errors;
    }

}
