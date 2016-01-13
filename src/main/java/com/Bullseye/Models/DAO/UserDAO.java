package com.Bullseye.Models.DAO;

import com.Bullseye.Models.Users;

public interface UserDAO extends GenericDAO<Users, Integer>
{
    public Users getUserByEmail(String argEmail);
    public Users getUserByUsername(String argUsername);
}