package com.project.SoyouCrypto;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@EnableJpaAuditing
@SpringBootApplication
public class SoyouCryptoApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.properties";


    public static void main(String[] args) {
        new SpringApplicationBuilder(SoyouCryptoApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
        System.out.println("current time : " + LocalDateTime.now());

    }

}