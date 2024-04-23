package com.example;

import com.example.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);

        System.out.println(employeeService.getAllEmployees().toString());
    }

}
