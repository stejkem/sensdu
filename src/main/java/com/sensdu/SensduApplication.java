package com.sensdu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class SensduApplication extends SpringBootServletInitializer {
    private static Class<SensduApplication> applicationClass = SensduApplication.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    public static void main(String[] args) {
        SpringApplication.run(SensduApplication.class, args);
    }
}