package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/AvailInsurance.html")
    public String availInsurancePage() {
        return "AvailInsurance";
    }

    @GetMapping("/ActualCards.html")
    public String actualCardsPage() {
        return "ActualCards";
    }
}
