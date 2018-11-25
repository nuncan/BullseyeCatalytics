package com.Bullseye.Services;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service("Kaptcha")
public class Kaptcha extends KaptchaExtend implements KaptchaService
{
    @Override
    public String getGeneratedKey(HttpServletRequest req)
    {
        return(super.getGeneratedKey(req));
    }
    
    @Override
    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.captcha(req, resp);
    }
}