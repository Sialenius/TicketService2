package com.project.jfb.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ConditionalConfiguration {

    @Bean
    @ConditionalOnProperty(name = "isConditionalBeanAvailable", havingValue = "true")
    public ThisIsMyFirstConditionalBean useMyConditionalBean() {
        return new ThisIsMyFirstConditionalBean();
    }
}

