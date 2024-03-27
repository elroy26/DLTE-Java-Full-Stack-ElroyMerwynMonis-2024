package com.example.soapendpoints.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@Configuration
@EnableWs
public class SoapServiceConfiguration extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet=new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet,"/transactionsrepo/*");

    }
//localhost:transactionPort/transactions.srvuce/transactions.wsdl
    @Bean(name="transactions")
    public DefaultWsdl11Definition convertToWsdl(XsdSchema xsdSchema){
        DefaultWsdl11Definition defaultWsdl11Definition=new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("TransactionsPort");
        defaultWsdl11Definition.setTargetNamespace("http://transactions.services");
        defaultWsdl11Definition.setLocationUri("/transactionsrepo");
        defaultWsdl11Definition.setSchema(xsdSchema);
        return defaultWsdl11Definition;
    }
    @Bean
    public XsdSchema loansSchema(){
        return new SimpleXsdSchema(new ClassPathResource("transactions.xsd"));
    }

}
