package com.zurion.contactreg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ContactRegApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactRegApplication.class, args);
    }

}
