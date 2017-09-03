package com.softserve.edu.lv251.config;

/**
 * Created by Taras on 03.08.2017.
 */

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private Logger logger;

    public TokenAuthenticationFilter() {
        super("/rest/api/**");
        setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {

            protected String determineTargetUrl(HttpServletRequest request,
                                                HttpServletResponse response) {
                String context = request.getContextPath();
                String fullURL = request.getRequestURI();
                String url = fullURL.substring(fullURL.indexOf(context)+context.length());
                return url;
            }

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String url = determineTargetUrl(request,response);

                request.getRequestDispatcher(url).forward(request, response);
            }
        });
        setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
                response.getOutputStream().print(authenticationException.getMessage());
            }
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        String token = request.getHeader("token");
        if (token == null)
            token = request.getParameter("token");
        if (token == null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("authToken")){
                    token = cookie.getValue();
                }
            }
        }
        if (token == null) {
            TokenAuthentication authentication = new TokenAuthentication(null);
            authentication.setAuthenticated(false);
            return authentication;
        }



        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        Authentication authentication = getAuthenticationManager().authenticate(tokenAuthentication);
        return authentication;
    }


}