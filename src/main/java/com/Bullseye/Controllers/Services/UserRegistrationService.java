package com.Bullseye.Controllers.Services;

import com.Bullseye.Controllers.Models.UserRegistrationDTO;
import javax.servlet.http.HttpServletRequest;

public interface UserRegistrationService
{
    public boolean RegisterUser(UserRegistrationDTO hData, HttpServletRequest hRequest);
}