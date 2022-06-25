package com.countryfinder.packages.console;


import com.countryfinder.packages.domain.PhoneNumber;
import com.countryfinder.packages.repository.CountryCodesRepository;
import com.countryfinder.packages.services.CodeDecryptor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Scanner;

@Component
@AllArgsConstructor
public class InputManager {
    private CodeDecryptor codeDecryptor;
    private CountryCodesRepository repository;

    public void run(){
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the phone number: ");
            String number = scanner.nextLine();
            PhoneNumber phoneNumber = new PhoneNumber(number);
            codeDecryptor.decrypt(phoneNumber);
        }
    }
}
