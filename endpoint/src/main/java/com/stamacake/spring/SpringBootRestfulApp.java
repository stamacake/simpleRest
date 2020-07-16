package com.stamacake.spring;

import com.stamacake.spring.controller.MainRESTController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRestfulApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestfulApp.class, args);
    }

}
