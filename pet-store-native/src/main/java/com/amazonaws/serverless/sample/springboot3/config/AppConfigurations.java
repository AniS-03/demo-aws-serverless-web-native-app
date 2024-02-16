package com.amazonaws.serverless.sample.springboot3.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.serverless.sample.springboot3.filter.SimpleFilter;
import com.sample.filters.OutsideProjectFilter;

@Configuration
public class AppConfigurations {

    @Bean(name= "filterRegistrationSimpleFilter")
    public FilterRegistrationBean<SimpleFilter> registrationSimpleFilter(SimpleFilter simpleFilter) {
        FilterRegistrationBean<SimpleFilter> registration = new FilterRegistrationBean<>(simpleFilter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean(name= "filterRegistrationOutsideProjectFilter")
    public FilterRegistrationBean<OutsideProjectFilter> registrationOutsideFilter(OutsideProjectFilter outsideFilter) {
        FilterRegistrationBean<OutsideProjectFilter> registration = new FilterRegistrationBean<>(outsideFilter);
        registration.setEnabled(false);
        return registration;
    }
    
}
