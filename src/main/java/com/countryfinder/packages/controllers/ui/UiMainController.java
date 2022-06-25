package com.countryfinder.packages.controllers.ui;

import com.countryfinder.packages.domain.PhoneNumber;
import com.countryfinder.packages.services.CodeDecryptor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/ui")
@AllArgsConstructor
public class UiMainController {
    private CodeDecryptor codeDecryptor;

    @GetMapping("/find-country")
    public String formPage(Model model){
        PhoneNumber phoneNumber = new PhoneNumber();
        model.addAttribute(phoneNumber);
        return "start";
    }

    @PostMapping("/found-country")
    public String resultPage(@ModelAttribute PhoneNumber phoneNumber, Model model){
        var obj = codeDecryptor.decrypt(phoneNumber);
        model.addAttribute("phoneNumber", obj);
        return "result";
    }
}
