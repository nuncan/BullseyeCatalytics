package com.Bullseye.Services;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

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