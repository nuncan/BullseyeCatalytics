package com.Bullseye.Config;

import com.Bullseye.Services.KaptchaService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
 
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
        super.unsuccessfulAuthentication(request, response, failed);
    }
     
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {       
        String CaptchaAnswer    = hKaptcha.getGeneratedKey(request);
        String CaptchaAttempt   = request.getParameter("Captcha");
        
        System.out.println("Captcha Answer: " + CaptchaAnswer + " | Captcha Attempt: " + CaptchaAttempt);
        
        // If Captcha Attempt Is Bad
        if(!CaptchaAttempt.equals(CaptchaAnswer))
        {
            throw new AuthenticationServiceException("Incorrect Captcha On Login Request");
        }
        return super.attemptAuthentication(request, response);
    }
}