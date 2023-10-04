package com.odilon;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
     
        HttpServletRequest httpRequest = (HttpServletRequest) request;

     
        String requestURI = httpRequest.getRequestURI();

 
        System.out.println("Request received: " + request.getRemoteAddr() + " - " + requestURI);

        chain.doFilter(request, response);

     
        System.out.println("Response sent: " + response.getContentType());
    }

    @Override
    public void destroy() {

    }
}

