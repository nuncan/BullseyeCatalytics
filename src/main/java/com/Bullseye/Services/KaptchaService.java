package com.Bullseye.Services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface KaptchaService
{
    public String getGeneratedKey(HttpServletRequest req);
    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}