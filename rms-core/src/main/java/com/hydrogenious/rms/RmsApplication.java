package com.hydrogenious.rms;

import org.eclipse.jgit.util.FS;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.hydrogenious.rms")
@PropertySource("classpath:/application.properties")
public class RmsApplication {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(RmsApplication.class).start();
    }

    @Bean
    public AbstractFactoryBean<FS> fileSystemFactory() {
        return new AbstractFactoryBean<FS>() {
            @Override
            public Class<?> getObjectType() {
                return FS.class;
            }

            @Override
            protected FS createInstance() throws Exception {
                return FS.DETECTED;
            }
        };
    }
}


