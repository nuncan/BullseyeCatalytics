package com.Bullseye.Models.Service;

import com.Bullseye.Models.Users;

public interface UserService extends GenericService<Users, Integer>
{
    public Users getUserByEmail(String argEmail);
    public Users getUserByUsername(String argUsername);
}