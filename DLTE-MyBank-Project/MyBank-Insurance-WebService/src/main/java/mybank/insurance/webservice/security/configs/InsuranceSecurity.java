package mybank.insurance.webservice.security.configs;

import maybank.insurance.dao.services.CustomerDbRepo;
import mybank.insurance.webservice.security.loginhandler.CustomerFailureHandler;
import mybank.insurance.webservice.security.loginhandler.CustomerSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ComponentScan("maybank.insurance.dao")
public class InsuranceSecurity {
    @Autowired
    private CustomerDbRepo service;

    AuthenticationManager manager;

    @Autowired
    CustomerSuccessHandler successHandler;
    @Autowired
    CustomerFailureHandler failureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.formLogin()
                .usernameParameter("username")
                .failureHandler(failureHandler)
                .successHandler(successHandler);
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();
//        httpSecurity.authorizeRequests().antMatchers("/insurancerepo/insurance.wsdl").permitAll();

        httpSecurity.authorizeRequests().anyRequest().authenticated();


        // 3rd layer
        AuthenticationManagerBuilder builder=httpSecurity.
                getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(service);
        manager=builder.build();
        httpSecurity.authenticationManager(manager);

        return httpSecurity.build();
    }

}
