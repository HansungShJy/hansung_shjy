package com.example.hansung_shjy_backend.hansung_shjy_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HansungShjyBackEndApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        
        SpringApplication.run(HansungShjyBackEndApplication.class, args);
    }

}
