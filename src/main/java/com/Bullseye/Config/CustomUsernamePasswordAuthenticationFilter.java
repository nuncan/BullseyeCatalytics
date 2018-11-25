package com.Bullseye.Config;

import com.Bullseye.Services.KaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    @Autowired
    KaptchaService hKaptcha;
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }
     
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // Need To Override This Exception Too
        super.unsuccessfulAuthentication(request, response, failed);
    }
     
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        // Note To Self: How Will This Behave If This Is Not Found ??
        String CaptchaAnswer    = hKaptcha.getGeneratedKey(request);
        String CaptchaAttempt   = request.getParameter("Captcha");
        
        System.out.println("Captcha Answer: " + CaptchaAnswer + " | Captcha Attempt: " + CaptchaAttempt);
        
        // If Captcha Attempt Is Bad
        if(!CaptchaAttempt.equals(CaptchaAnswer))
        {
            // Gonna Want To Return A Page With This Warning
            throw new AuthenticationServiceException("Incorrect Captcha On Login Request");
        }
        return super.attemptAuthentication(request, response);
    }
}