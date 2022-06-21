package com.countryfinder.packages.services;

import com.countryfinder.packages.repository.CountryCodesRepository;
import com.countryfinder.packages.validation.CoreError;
import com.countryfinder.packages.validation.PhoneNumberValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class CodeDecriptor {
    CountryCodesRepository repository;
    PhoneNumberValidator validator;

    public Collection<String> decript(String phoneNumber){
        if (phoneNumber != null){
            phoneNumber = removeUnnecessaryTokens(phoneNumber);
        }
        List<CoreError> errors = validator.validate(phoneNumber);
        if (!errors.isEmpty()){
            var response = new ArrayList<String>();
            for (CoreError error : errors){
                response.add(error.getMessage());
                System.err.println(error.getMessage());
            }
            return response;
        }
        StringBuilder stringBuilder = new StringBuilder();
        //Collection<String> result = new ArrayList<>();
        String result = null;
        stringBuilder.append("+");
        for (Character ch : phoneNumber.substring(1, phoneNumber.length()-1).toCharArray()){
            stringBuilder.append(ch);
            if (repository.getCodes().containsKey(stringBuilder.toString())){
                result  = repository.getCodes().get(stringBuilder.toString());
            }
        }
        if (result.isEmpty()){
            System.out.println("This number does not exist");
        }
        System.out.println("Possible countries: " + result);
        return List.of(result);
    }

    private String removeUnnecessaryTokens(String number){
        return number.replaceAll("[ \\-()]", "");
    }
}
