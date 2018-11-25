package com.Bullseye.Controllers.Services;

import com.Bullseye.Controllers.Models.UserRegistrationDTO;
import com.Bullseye.Models.Service.RolesService;
import com.Bullseye.Models.Service.UserService;
import com.Bullseye.Models.Users;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service("UserRegistration")
public class MainControllerImpl implements MainControllerService 
{
    @Autowired
    UserService hUserService;
    
    @Autowired
    RolesService hRolesService;
    
    @Autowired
    MapperFacade autoMapper;
    
    @Autowired
    BCryptPasswordEncoder getBCryptPasswordEncoder;
    
    /*
        This Method Is Responsible For Registering New Users Into The System
    */
    @Override
    @Transactional
    public boolean RegisterUser(UserRegistrationDTO hData, HttpServletRequest hRequest)
    {
        // Automap The Properties Into A New User Entity (Not Sure This Is The Best Way To Be Doing This...)
        Users hUser = autoMapper.map(hData, Users.class);
        
        // Try To Lookup The Client Role In The Database
        if(this.hRolesService.getRoleByName("CLIENT") == null) {
        // throw new RuntimeException("Couldnt Get A Pointer To The Client Role In The Database!");
            return(false);
        }
        
        // Assign IP Address
        hUser.setCreatorIP(hRequest.getRemoteAddr());
        
        // Encode The Users Password With BCrypt
        hUser.setPassword(this.getBCryptPasswordEncoder.encode(hUser.getPassword()));
        
        // Assign The Default Client Role To User
        hUser.addRole(this.hRolesService.getRoleByName("CLIENT"));
        
        // Persist User In Database
        this.hUserService.addByEntity(hUser);
        
        return(true);
    }
}