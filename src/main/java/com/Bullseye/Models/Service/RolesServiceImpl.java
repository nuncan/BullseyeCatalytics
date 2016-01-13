package com.Bullseye.Models.Service;

import com.Bullseye.Models.DAO.GenericDAO;
import com.Bullseye.Models.DAO.RolesDAO;
import com.Bullseye.Models.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("rolesService")
public class RolesServiceImpl extends GenericServiceImpl<Roles, Integer> implements RolesService
{
    private RolesDAO rolesDAO;

    public RolesServiceImpl()
    {
        
    }
    
    @Autowired
    public RolesServiceImpl(@Qualifier("rolesDAO") GenericDAO<Roles, Integer> genericDAO)
    {
        super(genericDAO);
        this.rolesDAO = (RolesDAO)genericDAO;
    }
    
}