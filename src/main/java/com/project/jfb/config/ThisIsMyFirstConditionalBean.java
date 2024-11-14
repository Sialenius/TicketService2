package com.project.jfb.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ThisIsMyFirstConditionalBean {
    
    @Bean
    @ConditionalOnProperty(name = "isConditionalBeanAvailable", havingValue = "true")
    public ThisIsMyFirstConditionalBean thisIsMyFirstConditionalBean() {
        return new ThisIsMyFirstConditionalBean();
    }
}
