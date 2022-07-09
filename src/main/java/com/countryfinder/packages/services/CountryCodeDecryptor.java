package com.countryfinder.packages.services;

import com.countryfinder.packages.dto.FindCountryRequest;
import com.countryfinder.packages.dto.FindCountryResponse;
import com.countryfinder.packages.repository.CountryCodesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CountryCodeDecryptor {
    CountryCodesRepository repository;

    public FindCountryResponse decrypt(FindCountryRequest request){
        String countryName = findOccurrence(request.getNumber());
        if (countryName == null){
            log.warn("This number does not exist");
        }
        log.info("Possible country: " + countryName);

        return new FindCountryResponse(new FindCountryResponse.CountryData(countryName, request.getNumber()));
    }

    private FindCountryRequest removeUnnecessaryTokens(String number){
        return new FindCountryRequest(number.replaceAll("[ \\-()]", ""));
    }

    public String findOccurrence(String number) {
        StringBuilder stringBuilder = new StringBuilder();
        String result = null;
        stringBuilder.append("+");
        for (Character ch : number.substring(1, number.length()-1).toCharArray()){
            stringBuilder.append(ch);
            if (repository.getCodes().containsKey(stringBuilder.toString())){
                result  = repository.getCodes().get(stringBuilder.toString());
            }
        }
        return result;
    }

}
