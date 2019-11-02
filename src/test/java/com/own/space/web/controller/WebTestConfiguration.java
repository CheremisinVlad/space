package com.own.space.web.controller;

import com.own.space.web.ControllersExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebTestConfiguration {

    @Bean
    public ControllersExceptionHandler controllersExceptionHandler(){
        return new ControllersExceptionHandler();
    }
}
