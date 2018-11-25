package com.Bullseye.Controllers;

import com.Bullseye.Controllers.Models.UserRegistrationDTO;
import com.Bullseye.Controllers.Services.MainControllerService;
import com.Bullseye.Models.Service.RolesService;
import com.Bullseye.Models.Service.UserService;
import com.Bullseye.Services.KaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

@Controller
public class MainController
{   
    @Autowired
    MainControllerService hMainControllerService;
    
    @Autowired
    UserService hUserService;
    
    @Autowired
    RolesService hRoleService;
    
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
    public String Register_POST(@Valid UserRegistrationDTO UserRegistrationData, BindingResult hBindResult, HttpServletRequest hRequest)
    {
        // Check If Users Submitted Bad Data, If So, Redirect And Try Again
        if(hBindResult.hasErrors()) {
            return "redirect:/Register?Error";
        }
        
        // First: Check The Captcha, Then Proceed To Create The Account
        if(UserRegistrationData.getCaptcha().equals(hKaptcha.getGeneratedKey(hRequest)))
        {
            // Second: Try To Create New User
            if(this.hMainControllerService.RegisterUser(UserRegistrationData, hRequest))
            {
                return "redirect:/Login";
            }
        }

        return "redirect:/Register?Error";
    }
    
    //
    //  Admin User Management Page
    //
    
    @RequestMapping(value = "/Dashboard/Admin/Users", method = RequestMethod.GET)
    public String Admin_Users_GET(ModelMap model)
    {
        // Add User Roles List
        model.addAttribute("Roles", getUserRoles());
        
        // Add Username
        model.addAttribute("Username", getUsername());
        
        // Add List Of Users
        model.addAttribute("UserList", this.hUserService.listAll());
        
        return "/Admin/Users";
    }
    
    //
    //  Admin Role Management Page
    //
    @RequestMapping(value = "/Dashboard/Admin/Roles", method = RequestMethod.GET)
    public String Admin_Roles_GET(ModelMap model)
    {
        // Add User Roles List
        model.addAttribute("Roles", getUserRoles());
        
        // Add Username
        model.addAttribute("Username", getUsername());
        
        // Add List Of Users
        model.addAttribute("RoleList", this.hRoleService.listAll());
        
        return "/Admin/Roles";
    }
    
    //
    //  Admin Global Management Page
    //
    @RequestMapping(value = "/Dashboard/Admin/Global", method = RequestMethod.GET)
    public String Admin_Global_Settings_GET(ModelMap model)
    {
        // Add User Roles List
        model.addAttribute("Roles", getUserRoles());
        
        // Add Username
        model.addAttribute("Username", getUsername());

        return "/Admin/Global";
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

        // User Is Logged In
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
    
    //
    //  Return User Roles
    //
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