package com.countryfinder.packages.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneNumberValidatorTest {
    @Autowired
    PhoneNumberValidator validator;

    @Test
    public void shouldValidateWithoutErrors(){
        String number = "+37123345678";
        var result = validator.validate(number);
        assertEquals(List.of(), result);
    }

    @Test
    public void shouldExceedLength(){
        String number = "+3711231231321321";
        var result = validator.validate(number);
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("Phone number should be at most 15 digits."));
        expected.add(new CoreError("Wrong number format."));
        assertEquals(expected, result);
    }

    @Test
    public void shouldLackLength(){
        String number = "+37133";
        var result = validator.validate(number);
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("Phone number should be at least 7 digits."));
        expected.add(new CoreError("Wrong number format."));
        assertEquals(expected, result);
    }

    @Test
    public void shouldBeWithoutPlus(){
        String number = "37121333444";
        var result = validator.validate(number);
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("Number should have country code."));
        expected.add(new CoreError("Wrong number format."));
        assertEquals(expected, result);
    }

    @Test
    public void shouldBeWrongFormatAndTokens(){
        String number = "+371%43211^43";
        var result = validator.validate(number);
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("Wrong tokens are detected."));
        expected.add(new CoreError("Wrong number format."));
        assertEquals(expected, result);
    }
}