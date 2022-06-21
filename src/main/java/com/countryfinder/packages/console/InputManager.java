package com.countryfinder.packages.console;


import com.countryfinder.packages.repository.CountryCodesRepository;
import com.countryfinder.packages.services.CodeDecriptor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Scanner;

@Component
@AllArgsConstructor
public class InputManager {
    private CodeDecriptor codeDecriptor;
    private CountryCodesRepository repository;

    public void run(){
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the phone number: ");
            String phoneNumber = scanner.nextLine();
            codeDecriptor.decript(phoneNumber);
        }
    }
}
