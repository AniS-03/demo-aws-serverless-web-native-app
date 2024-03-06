package com.amazonaws.serverless.sample.springboot3.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OutsideSecurityFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("Outside security filter chain.");

        if("OPTIONS".equals(request.getMethod())) {

            System.out.println("Inside OPTIONS call");

            response.addHeader("Name", "Aniket Saini");
            response.setStatus(200);
            return;
        } else {

            filterChain.doFilter(request, response);

        }
        
    }
    
}
