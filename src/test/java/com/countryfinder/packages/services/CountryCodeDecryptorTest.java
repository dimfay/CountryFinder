package com.countryfinder.packages.services;

import com.countryfinder.packages.dto.FindCountryRequest;
import com.countryfinder.packages.dto.FindCountryResponse;
import com.countryfinder.packages.repository.CountryCodesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CountryCodeDecryptorTest {
    @Spy
    CountryCodesRepository repository;
    @InjectMocks
    CountryCodeDecryptor countryCodeDecryptor;

    @Test
    public void shouldReturnCorrectCountry(){
        var findCountryRequest = new FindCountryRequest("+37126944323");
        when(repository.getCodes()).thenReturn(new HashMap<>(Map.of("+371", "Latvia")));

        var result = countryCodeDecryptor.decrypt(findCountryRequest);
        var expected = new FindCountryResponse
                (new FindCountryResponse.CountryData("Latvia", "+37126944323"));

        verify(repository, atLeastOnce()).getCodes();
        assertEquals(expected, result);
    }
}