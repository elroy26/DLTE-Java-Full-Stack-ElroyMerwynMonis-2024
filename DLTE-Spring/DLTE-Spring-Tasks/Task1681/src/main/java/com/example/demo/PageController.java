package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/AvailInsurance.html")
    public String availInsurancePage() {
        return "AvailInsurance"; // This should match the name of your HTML template file
    }

    @GetMapping("/ActualCards.html")
    public String actualCardsPage() {
        return "ActualCards"; // This should match the name of your HTML template file
    }
}
