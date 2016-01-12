package com.Bullseye.Models.DAO;

import com.Bullseye.Models.User;

public interface UserDAO extends GenericDAO<User, Integer>
{
    public User getUserByEmail(String argEmail);
    public User getUserByUsername(String argUsername);
}