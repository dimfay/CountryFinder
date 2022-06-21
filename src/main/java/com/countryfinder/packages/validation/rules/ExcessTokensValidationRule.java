package com.countryfinder.packages.validation.rules;

import com.countryfinder.packages.validation.PhoneNumberValidationException;
import org.springframework.stereotype.Component;

@Component
public class ExcessTokensValidationRule implements ValidationRule{
    @Override
    public void validate(String number) throws PhoneNumberValidationException {
        if (!isProperToken(number)){
            throw new PhoneNumberValidationException("Wrong tokens are detected.");
        }
    }

    private boolean isProperToken(String number){
        for (Character ch : number.toCharArray()) {
            if (!Character.isDigit(ch) && ch != '+') {
                return false;
            }
        }
        return true;
    }
}
