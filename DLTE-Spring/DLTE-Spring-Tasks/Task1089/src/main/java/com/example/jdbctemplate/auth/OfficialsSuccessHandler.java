package com.example.jdbctemplate.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OfficialsSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    MyBankOfficialsService service;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MyBankOfficials myBankOfficials= (MyBankOfficials) authentication.getPrincipal();
        if(myBankOfficials.getStatus()!=0){
            if(myBankOfficials.getStatus()>1){
                myBankOfficials.setAttempts(1);
                service.updateAttempts(myBankOfficials);
            }
            super.setDefaultTargetUrl("/web/dash");
        }
        else{
            logger.warn("Max attempts reached contact admin");
            super.setDefaultTargetUrl("/web/?error=contact admin");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
