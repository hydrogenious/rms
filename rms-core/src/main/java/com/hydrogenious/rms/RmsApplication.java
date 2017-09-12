package com.hydrogenious.rms;

import com.hydrogenious.rms.api.ReferenceTermsApi;
import com.hydrogenious.rms.stub.ReferenceTermsApiStub;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static java.util.Collections.emptySet;

@Configuration
@ComponentScan("com.hydrogenious.rms")
public class RmsApplication {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(RmsApplication.class).start();
    }

    @Bean
    public ReferenceTermsApi referenceTermsApi() {
        return new ReferenceTermsApiStub(emptySet());
    }
}
