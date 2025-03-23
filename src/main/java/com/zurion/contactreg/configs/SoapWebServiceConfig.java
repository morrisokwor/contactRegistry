package com.zurion.contactreg.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@Configuration
@EnableWs
public class SoapWebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "soap")
    public DefaultWsdl11Definition defaultWsdl11Definition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("serviceSoapHttp");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://interfaces.soapservice.zurion.com");
        wsdl11Definition.setSchema(contactsSchema());
        return wsdl11Definition;
    }

//    @Bean
//    public XsdSchemaCollection schemaCollection() {
//        CommonsXsdSchemaCollection commonsXsdSchemaCollection = new CommonsXsdSchemaCollection(
//                new ClassPathResource("contacts.xsd"));
//        commonsXsdSchemaCollection.setInline(true);
//        return commonsXsdSchemaCollection;
//    }

    @Bean
    public XsdSchema contactsSchema() {
        return new SimpleXsdSchema(new org.springframework.core.io.ClassPathResource("xsd/contacts.xsd"));
    }
}
