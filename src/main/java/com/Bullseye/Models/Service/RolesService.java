package com.Bullseye.Models.Service;

import com.Bullseye.Models.Roles;

public interface RolesService extends GenericService<Roles, Integer>
{
    public Roles getRoleByName(String argRoleName);
}