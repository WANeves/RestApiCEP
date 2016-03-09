package com.restapicep.service;

import com.restapicep.init.RestApiCepServiceInitialiser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.restapicep")
public class WebApplication {

    public static void main(String[] args) {


        ConfigurableApplicationContext context =
                SpringApplication.run(new Object[]{WebApplication.class, "address.xml"}, args);
        RestApiCepServiceInitialiser.init(context.getBean(AddressService.class));
    }
}
