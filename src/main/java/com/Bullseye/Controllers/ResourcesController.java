package com.Bullseye.Controllers;

import com.Bullseye.Services.KaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ResourcesController
{
    @Autowired
    KaptchaService hKaptcha;
    
    //
    //  Add Captcha Support
    //
    @RequestMapping(value = "/Captcha.jpg", method = RequestMethod.GET)
    public void Captcha_GET(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        hKaptcha.captcha(req, resp);
    }
}