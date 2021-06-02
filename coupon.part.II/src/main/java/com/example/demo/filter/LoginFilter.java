/*
 * Copyright (c) // Author Gadiler 28.5.2021, 15:25.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.filter;

import com.example.demo.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
public class LoginFilter implements Filter {
    @Autowired
    private TokenManager tokensManager;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        // Exclude : "Register", "Login"
        String pageRequested = req.getRequestURL().toString();

        if (pageRequested.endsWith("/login")) {
            System.out.println(pageRequested);
            chain.doFilter(request, response);
            return;
        }


//	        if(pageRequested.endsWith("/customers") && req.getMethod().toString().equals("POST")){
//	            chain.doFilter(request,response);
//	            return;
//	        }

        String token = req.getHeader("Authorization");
        //String token = req.getParameter("token");
        System.out.println("Token : " + token);

        if (token != null) {
            try {
                tokensManager.isExist(token);
                //request.setAttribute("token",token);
                chain.doFilter(request, response);
                return;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        HttpServletResponse res = (HttpServletResponse) response;
        // U're not logged in
        res.setStatus(401);
    }
}
