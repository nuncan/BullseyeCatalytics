package com.Bullseye.Controllers.Services;

import com.Bullseye.Controllers.Models.UserRegistrationDTO;
import com.Bullseye.Models.Users;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

public interface MainControllerService
{
    public boolean RegisterUser(UserRegistrationDTO hData, HttpServletRequest hRequest);
}