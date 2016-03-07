package com.Bullseye.Controllers.Services;

import javax.servlet.http.HttpServletRequest;
import com.Bullseye.Controllers.Models.UserRegistrationDTO;

public interface MainControllerService
{
    public boolean RegisterUser(UserRegistrationDTO hData, HttpServletRequest hRequest);
}