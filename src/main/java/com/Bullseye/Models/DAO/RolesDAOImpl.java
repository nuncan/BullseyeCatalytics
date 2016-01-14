package com.Bullseye.Models.DAO;

import com.Bullseye.Models.Roles;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("rolesDAO")
public class RolesDAOImpl extends GenericDAOImpl<Roles, Integer> implements RolesDAO
{
    @Override
    public Roles getRoleByName(String argRoleName)
    {
        Criteria crit = getSession().createCriteria(persistentClass);
        crit.add(Restrictions.eq("Name", argRoleName));
        crit.setFirstResult(0);                     // Result 0 Is First Result
        crit.setMaxResults(1);                      // Result 1 Is Max Result
        // Check Result
        if((Roles)crit.uniqueResult() != null) {
            return (Roles)crit.uniqueResult();
        }
        else {
            throw new RuntimeException("No Role With That Name Found");
        }
    }
}