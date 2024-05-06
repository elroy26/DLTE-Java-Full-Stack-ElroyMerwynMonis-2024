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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.ResourceBundle;

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

    ResourceBundle resourceBundle= ResourceBundle.getBundle("webservice");

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList(resourceBundle.getString("cors.url")));
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests().antMatchers(resourceBundle.getString("image.url")).permitAll();
        httpSecurity.authorizeRequests().antMatchers(resourceBundle.getString("style.url")).permitAll();
        httpSecurity.authorizeRequests().antMatchers(resourceBundle.getString("url")).permitAll();

        httpSecurity.authorizeRequests().antMatchers(resourceBundle.getString("signup.url")).permitAll();
        httpSecurity.authorizeRequests().antMatchers(resourceBundle.getString("api.doc.url")).permitAll();
        httpSecurity.authorizeRequests().antMatchers(resourceBundle.getString("soap.wsdl")).permitAll();
        httpSecurity.formLogin().loginPage(resourceBundle.getString("url"))
                .usernameParameter(resourceBundle.getString("param.user"))
                .failureHandler(failureHandler)
                .successHandler(successHandler);
        httpSecurity.csrf().disable();
        httpSecurity.cors();

        httpSecurity.authorizeRequests().anyRequest().authenticated();

        AuthenticationManagerBuilder builder=httpSecurity.
                getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(service);
        manager=builder.build();
        httpSecurity.authenticationManager(manager);

        return httpSecurity.build();
    }

}
