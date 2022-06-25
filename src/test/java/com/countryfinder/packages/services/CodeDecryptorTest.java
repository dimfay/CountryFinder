package com.countryfinder.packages.services;

import com.countryfinder.packages.domain.PhoneNumber;
import com.countryfinder.packages.repository.CountryCodesRepository;
import com.countryfinder.packages.validation.PhoneNumberValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CodeDecryptorTest {
    @Spy
    CountryCodesRepository repository;
    @Mock
    PhoneNumberValidator validator;
    @InjectMocks
    CodeDecryptor codeDecryptor;

    @Test
    public void shouldReturnCorrectCountry(){
        PhoneNumber phoneNumber = new PhoneNumber("+37126944323");
        when(validator.validate(phoneNumber.getNumber())).thenReturn(List.of());
        when(repository.getCodes()).thenReturn(new HashMap<>(Map.of("+371", "Latvia")));

        PhoneNumber result = codeDecryptor.decrypt(phoneNumber);
        PhoneNumber expected = new PhoneNumber();
        expected.setCountry("Latvia");
        expected.setNumber("+37126944323");

        verify(validator).validate(any());
        verify(repository, atLeastOnce()).getCodes();
        assertEquals(expected, result);
    }
}