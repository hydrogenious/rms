package com.hydrogenious.rms;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.hydrogenious.rms")
@PropertySource("classpath:/application.properties")
public class RmsApplication {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(RmsApplication.class).start();
    }

}


