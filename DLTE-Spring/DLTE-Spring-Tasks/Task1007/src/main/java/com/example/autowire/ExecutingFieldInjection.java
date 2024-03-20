package com.example.autowire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExecutingFieldInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.scan("com.example.autowire");
        applicationContext.refresh();
        MyBank myBank=applicationContext.getBean(MyBank.class);
        System.out.println(myBank.execute());
    }
}
