package com.Bullseye.Controllers.Services;

import com.Bullseye.Controllers.Models.UserRegistrationDTO;

import javax.servlet.http.HttpServletRequest;

public interface MainControllerService
{
    boolean RegisterUser(UserRegistrationDTO hData, HttpServletRequest hRequest);
}