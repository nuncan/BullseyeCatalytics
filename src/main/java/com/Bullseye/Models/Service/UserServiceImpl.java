package com.Bullseye.Models.Service;

import com.Bullseye.Models.User;
import com.Bullseye.Models.DAO.UserDAO;
import com.Bullseye.Models.DAO.GenericDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Service("userService") // Declares This Class As A Service To Spring
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
}