package com.sensdu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sensdu.service.MediaWikiAPIRequestBuilder;

/**
 * @author Stejkem
 */
@Configuration
public class AppConfig {

    @Bean
    public MediaWikiAPIRequestBuilder mediaWikiEnglishAPI() {
        return new MediaWikiAPIRequestBuilder("en");
    }
}