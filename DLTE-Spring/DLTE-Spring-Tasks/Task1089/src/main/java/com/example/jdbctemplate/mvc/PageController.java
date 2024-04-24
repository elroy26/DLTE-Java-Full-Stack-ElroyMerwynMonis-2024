package com.example.jdbctemplate.mvc;

import com.example.jdbctemplate.TransactionEntity;
import com.example.jdbctemplate.TransactionException;
import com.example.jdbctemplate.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/web")
public class PageController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/")
    public String showIndexPage(Model model) {
        model.addAttribute("transaction", new TransactionEntity());
        return "index";
    }

    @PostMapping("/send")
    public String newTransaction(@ModelAttribute("transaction") TransactionEntity transactionEntity,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        try {
            transactionService.newTransaction(transactionEntity);
            redirectAttributes.addFlashAttribute("message", "Transaction created successfully");
            return "redirect:/web/confirmation";
        } catch (TransactionException exception) {
            model.addAttribute("error", exception.getMessage());
            return "index";
        }
    }

    @GetMapping("/confirmation")
    public String transactionConfirmation(Model model) {
        return "transactionConfirmation";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
