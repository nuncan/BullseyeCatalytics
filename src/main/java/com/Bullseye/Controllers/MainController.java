package com.Bullseye.Controllers;

import com.Bullseye.Controllers.Models.UserRegistrationDTO;
import com.Bullseye.Controllers.Services.UserRegistrationService;
import javax.validation.Valid;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Bullseye.Services.KaptchaService;
import java.util.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Controller
public class MainController
{
    @Autowired
    UserRegistrationService hUserReg;
    
    @Autowired
    KaptchaService hKaptcha;
    
    //
    //  Dashboard Page
    //
    @RequestMapping(value = { "/Dashboard" }, method = RequestMethod.GET)
    public String Dashboard_GET(ModelMap model)
    {
        model.addAttribute("Roles", getUserRoles());
        model.addAttribute("Username", getUsername());
        return "Dashboard";
    }
    
    //
    //  Denied Page
    //
    @RequestMapping(value = "/Denied", method = RequestMethod.GET)
    public String Denied_GET(ModelMap model)
    {
        model.addAttribute("Username", getUsername());
	return "Denied";
    }
    
    //
    //  Registration Page
    //
    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String Register_GET()
    {
        // Check If The Users Is Already Logged In & If So, Then Redirect Them
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken))
        {
            return "redirect:/Dashboard";
        }
        
        return "Register";
    }
    
    //
    //  This Method Captures Data From The Registration Page Into A New
    //  Users Object Then Perists That Object Into The Database
    //
    @RequestMapping(value = "/Register" , method = RequestMethod.POST)
    public String Register_POST(@Valid UserRegistrationDTO UserRegistrationData, BindingResult hBindResult, HttpServletRequest req)
    {
        // Check If Users Submitted Bad Data, If So, Redirect And Try Again
        if(hBindResult.hasErrors()) {
            return "redirect:/Register?Error";
        }
        
        // First Check The Captcha, The Create The Account
        if(UserRegistrationData.getCaptcha().equals(hKaptcha.getGeneratedKey(req)))
        {
            // Seconds Try To Create New User
            if(this.hUserReg.RegisterUser(UserRegistrationData))
            {
                return "redirect:/Login";
            }
        }

        return "redirect:/Register?Error";
    }

    //
    //  Login Page
    //
    @RequestMapping(value = { "/Login", "/" }, method = RequestMethod.GET)
    public String Login_GET()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Check If The Users Is Already Logged In
        if (!(auth instanceof AnonymousAuthenticationToken))
        {
            return "redirect:/Dashboard";
        }
        return "Login";
    }

    //
    //  Logout Page
    //
    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    public String Logout_GET (HttpServletRequest request, HttpServletResponse response)
    {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
	}
	return "redirect:/Login?LoggedOut";
    }

    //
    //  Return Users Information
    //
    private String getUsername()
    {
	String userName;
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	if (principal instanceof UserDetails)
        {
            userName = ((UserDetails)principal).getUsername();
	}
        else
        {
            userName = principal.toString();
	}
	return userName;
    }
    
    
    private String getUserRoles()
    {
        String userRoles;
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // User Is Logged In
	if (principal instanceof UserDetails)
        {
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            userRoles = authorities.toString().replaceAll("ROLE_", "");
	}
        else
        {
            userRoles = principal.toString();
	}
	return userRoles;
    }
}