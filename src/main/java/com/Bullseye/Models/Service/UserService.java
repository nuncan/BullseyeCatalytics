package com.Bullseye.Models.Service;

import com.Bullseye.Models.User;

public interface UserService extends GenericService<User, Integer>
{
    public User getUserByEmail(String argEmail);
    public User getUserByUsername(String argUsername);
}