package com.Bullseye.Services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface KaptchaService
{
    String getGeneratedKey(HttpServletRequest req);

    void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}