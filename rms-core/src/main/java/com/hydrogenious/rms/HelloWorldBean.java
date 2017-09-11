package com.hydrogenious.rms;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldBean {
    public String getMessage(Object greeter) {
        return "Hello, my name is " + greeter;
    }
}
