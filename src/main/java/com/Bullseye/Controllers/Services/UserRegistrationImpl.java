package com.Bullseye.Controllers.Services;

import com.Bullseye.Controllers.Models.UserRegistrationDTO;
import com.Bullseye.Models.Service.RolesService;
import com.Bullseye.Models.Service.UserService;
import com.Bullseye.Models.Users;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserRegistration")
public class UserRegistrationImpl implements UserRegistrationService
{
    @Autowired
    UserService hUserService;
    
    @Autowired
    RolesService hRolesService;
    
    @Autowired
    MapperFacade autoMapper;
    
    @Override
    @Transactional
    public boolean RegisterUser(UserRegistrationDTO hData)
    {
        Users hUser = autoMapper.map(hData, Users.class);
        
        if(this.hRolesService.getRoleByName("CLIENT") == null) {
            System.out.println("Couldnt Get A Pointer To ROLE_CLIENT In The Database!");
            return(false);
        }
        
        hUser.addRole(this.hRolesService.getRoleByName("CLIENT"));
        
        this.hUserService.addByEntity(hUser);
        
        return(true);
    }
}