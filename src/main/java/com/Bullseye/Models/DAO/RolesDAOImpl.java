package com.Bullseye.Models.DAO;

import com.Bullseye.Models.Roles;
import org.springframework.stereotype.Repository;

@Repository("rolesDAO")
public class RolesDAOImpl extends GenericDAOImpl<Roles, Integer> implements RolesDAO
{
    
}