package com.countryfinder.packages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FindCountryResponse {
    private CountryData data;

    @Data
    @AllArgsConstructor
    public static class CountryData{
        private String countryName;
        private String phoneNumber;
    }
}
