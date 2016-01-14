package com.Bullseye.Controllers;

import com.Bullseye.Controllers.Models.UserRegistrationDTO;
import com.Bullseye.Controllers.Services.UserRegistrationService;
import javax.validation.Valid;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Bullseye.Models.Service.UserService;
import com.google.code.kaptcha.servlet.KaptchaExtend;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
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
public class MainController extends KaptchaExtend
{   
    @Autowired
    UserService hUserService;
    
    @Autowired
    UserRegistrationService hUserReg;
    
    //
    //  Index Page
    //
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index_GET(ModelMap model)
    {
        return "Login";
    }
    
    //
    //  Add Captcha Support
    //
    @RequestMapping(value = "/captcha.jpg", method = RequestMethod.GET)
    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.captcha(req, resp);
    }
    
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
            return "forward:/Dashboard";
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
        if(UserRegistrationData.getCaptcha().equals(getGeneratedKey(req)))
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
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String Login_GET()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Check If The Users Is Already Logged In
        if (!(auth instanceof AnonymousAuthenticationToken))
        {
            return "forward:/Dashboard";
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
	if (auth != null){
            auth.setAuthenticated(false);
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