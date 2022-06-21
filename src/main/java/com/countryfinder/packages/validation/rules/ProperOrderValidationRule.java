package com.countryfinder.packages.validation.rules;

import com.countryfinder.packages.validation.PhoneNumberValidationException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProperOrderValidationRule implements ValidationRule{
    @Override
    public void validate(String number) throws PhoneNumberValidationException {
        Pattern pattern = Pattern.compile("^\\+[0-9]{6,14}[0-9]$");
        Matcher matcher = pattern.matcher(number);
        if (!matcher.matches()){
            throw new PhoneNumberValidationException("Wrong number format.");
        }
    }
}
