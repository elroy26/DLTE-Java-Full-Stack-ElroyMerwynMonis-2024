package com.example.demo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/web")
public class MyBankWebController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String myTemplate(){
//        model.addAttribute("greet","Welcome to My Bank");
        return "index";
    }
}
