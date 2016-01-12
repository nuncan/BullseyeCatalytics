package com.Bullseye.Models.Service;

import com.Bullseye.Models.User;
import com.Bullseye.Models.DAO.UserDAO;
import com.Bullseye.Models.DAO.GenericDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

/*
    Service Layer Provides Access To Models And Their Data
    The Service Layer Is Transactional And Uses The DAO Layer
    To Access Data.
*/

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService
{
    private UserDAO userDAO;
    
    public UserServiceImpl()
    {
    
    }
    
    @Autowired
    public UserServiceImpl(@Qualifier("userDAO") GenericDAO<User, Integer> genericDAO)
    {
        super(genericDAO);
        this.userDAO = (UserDAO)genericDAO;
    }
    
    //
    //  Methods Unique To Users
    //
    @Override
    @Transactional
    public User getUserByEmail(String argEmail)
    {
        return(userDAO.getUserByEmail(argEmail));
    }
    
    @Override
    @Transactional
    public User getUserByUsername(String argUsername)
    {
        return(userDAO.getUserByUsername(argUsername));
    }
    

    
}