package com.countryfinder.packages;

import com.countryfinder.packages.console.InputManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        InputManager inputManager = context.getBean(InputManager.class);
        inputManager.run();
    }
}
