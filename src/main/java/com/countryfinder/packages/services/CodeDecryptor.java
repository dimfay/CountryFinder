package com.countryfinder.packages.services;

import com.countryfinder.packages.domain.PhoneNumber;
import com.countryfinder.packages.repository.CountryCodesRepository;
import com.countryfinder.packages.validation.CoreError;
import com.countryfinder.packages.validation.PhoneNumberValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CodeDecryptor {
    CountryCodesRepository repository;
    PhoneNumberValidator validator;

    public PhoneNumber decrypt(PhoneNumber phoneNumber){
        if (phoneNumber.getNumber() != null)
            phoneNumber = removeUnnecessaryTokens(phoneNumber.getNumber());

        List<CoreError> errors = validator.validate(phoneNumber.getNumber());
        if (!errors.isEmpty()){
            var response = new ArrayList<CoreError>();
            for (CoreError error : errors){
                response.add(error);
                System.err.println(error.getMessage());
            }
            phoneNumber.setErrors(response);
            return phoneNumber;
        }

        String result = findOccurrence(phoneNumber);
        if (result == null){
            System.out.println("This number does not exist");
        }
        System.out.println("Possible country: " + result);
        phoneNumber.setCountry(result);
        return phoneNumber;
    }

    private PhoneNumber removeUnnecessaryTokens(String number){
        return new PhoneNumber(number.replaceAll("[ \\-()]", ""));
    }

    public String findOccurrence(PhoneNumber phoneNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        String result = null;
        stringBuilder.append("+");
        for (Character ch : phoneNumber.getNumber().substring(1, phoneNumber.getNumber().length()-1).toCharArray()){
            stringBuilder.append(ch);
            if (repository.getCodes().containsKey(stringBuilder.toString())){
                result  = repository.getCodes().get(stringBuilder.toString());
            }
        }
        return result;
    }

}
