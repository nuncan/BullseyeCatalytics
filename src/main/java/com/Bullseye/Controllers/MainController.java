package com.Bullseye.Controllers;

import com.Bullseye.Models.DTO.UserRegistrationDTO;
import javax.validation.Valid;
import com.Bullseye.Models.User;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Bullseye.Models.Service.UserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Controller
public class MainController
{   
    @Autowired
    UserService hUserService;
    
    @Autowired
    MapperFacade autoMapper;
    
    //
    //  Index Page
    //
    @RequestMapping(value = { "/", "/Index" }, method = RequestMethod.GET)
    public String Index_GET(ModelMap model)
    {
        model.addAttribute("user", getPrincipal());
        return "Index";
    }
    
    //
    //  Install Page
    //
    @RequestMapping(value = "/Install", method = RequestMethod.GET)
    public String Install_GET(ModelMap model)
    {

	return "Index";
    }
    
    
    //
    //  Dashboard Page
    //
    @RequestMapping(value = { "/Dashboard" }, method = RequestMethod.GET)
    public String Dashboard_GET(ModelMap model)
    {
        model.addAttribute("user", getPrincipal());
        return "Dashboard";
    }
    
    //
    //  Denied Page
    //
    @RequestMapping(value = "/Denied", method = RequestMethod.GET)
    public String Denied_GET(ModelMap model)
    {
        model.addAttribute("user", getPrincipal());
	return "Denied";
    }
    
    //
    //  Registration Page
    //
    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String Register_GET()
    {
        // Check If The User Is Already Logged In & If So, Then Redirect Them
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken))
        {
            return "forward:/Dashboard";
        }
        
        return "Register";
    }
    
    //
    //  This Method Captures Data From The Registration Page Into A New
    //  User Object Then Perists That Object Into The Database
    //
    @RequestMapping(value = "/Register" , method = RequestMethod.POST)
    public String Register_POST(@Valid UserRegistrationDTO UserRegistartionData, BindingResult hBindResult)
    {
        // Check If User Submitted Bad Data, If So, Redirect And Try Again
        if(hBindResult.hasErrors()) {
            return "forward:/Register?Error";
        }
       
        // Automap Properties To A New User Object
        User hUser = autoMapper.map(UserRegistartionData, User.class);
        
        System.out.println("Dumping User Before We Persist: " + hUser.toString());
        
        // Finally, We Save The New User Entity
        this.hUserService.addByEntity(hUser);
        
        // Redirect Client Back To Their Page (Notice: You HAVE To Redirect, A Forward Will Also Do A POST)
        return "redirect:/Login";
    }

    //
    //  Login Page
    //
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String Login_GET()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Check If The User Is Already Logged In
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
            new SecurityContextLogoutHandler().logout(request, response, auth);
	}
	return "redirect:/Login?LoggedOut";
    }

    //
    //  Return User Information
    //
    private String getPrincipal()
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
}