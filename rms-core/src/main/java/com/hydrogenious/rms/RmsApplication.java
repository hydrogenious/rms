package com.hydrogenious.rms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("com.hydrogenious.rms")
public class RmsApplication {

    private final HelloWorldBean bean;

    @Autowired
    public RmsApplication(HelloWorldBean bean) {
        this.bean = bean;
    }

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(RmsApplication.class).start();
    }

    @PostConstruct
    public void greet() {
        System.out.println(bean.getMessage(this));
    }
}
