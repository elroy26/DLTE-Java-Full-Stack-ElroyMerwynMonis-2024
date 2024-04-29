package mybank.insurance.webservice.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class InsuranceWebController {
    @GetMapping("/")
    public String dash(){
        return "dashboard";
    }

    @GetMapping("/footer")
    public String footer(){
        return "footer";
    }
}
