package com.amazonaws.serverless.sample.springboot3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;

import com.amazonaws.serverless.sample.springboot3.filter.SimpleFilter;
import com.sample.filters.OutsideProjectFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private OutsideProjectFilter outsideFilter;

    @Autowired
    private SimpleFilter simpleFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/pets").authenticated()
                            .anyRequest().permitAll();
                            // .anyRequest().permitAll();
                })
                .addFilterAfter(outsideFilter, LogoutFilter.class)
                .addFilterAfter(simpleFilter, RequestCacheAwareFilter.class)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS).sessionFixation().none());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }

    
}
