package com.amazonaws.serverless.sample.springboot3.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;

@Component
public class SimpleFilter extends OncePerRequestFilter {

    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("Aniket Saini", "", null));

        if("OPTIONS".equals(request.getMethod())) {
            System.out.println("Inside OPTIONS call");

            response.addHeader("Name", "Aniket Saini");
            response.setStatus(200);
            response.setContentType(MediaType.TEXT_PLAIN);
            response.getWriter().write("Not able to get response back");

            // filterChain.doFilter(request, response);

        } else {
            filterChain.doFilter(request, response);
        }
    }
}