package com.Bullseye.Models.DAO;

import com.Bullseye.Models.Users;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
    This Layer Is Used To Access The Database.
    This Layer Is Never To Be Accessed Directly
    Instead This Layer Is To Be Accessed Only By The Service Layer
*/
@Repository("usersDAO")
public class UserDAOImpl extends GenericDAOImpl<Users, Integer> implements UserDAO
{
    @Autowired
    BCryptPasswordEncoder getBCryptPasswordEncoder;
    
    @Override
    public Users getUserByEmail(String argEmail)
    {
        Criteria crit = getSession().createCriteria(persistentClass);
        crit.add(Restrictions.eq("Email", argEmail));
        crit.setFirstResult(0);                     // Result 0 Is First Result
        crit.setMaxResults(1);                      // Result 1 Is Max Result
        // Check Result
        if((Users)crit.uniqueResult() != null) {
            return (Users)crit.uniqueResult();
        }
        else {
            throw new RuntimeException("No User With That Email Found");
        }
    }
    
    @Override
    public Users getUserByUsername(String argUsername)
    {
        Criteria crit = getSession().createCriteria(persistentClass);
        crit.add(Restrictions.eq("Username", argUsername));
        crit.setFirstResult(0);                     // Result 0 Is First Result
        crit.setMaxResults(1);                      // Result 1 Is Max Result
        // Check Result
        if((Users)crit.uniqueResult() != null) {
            return (Users)crit.uniqueResult();
        }
        else {
            throw new RuntimeException("No User With That Username Found");
        }
    }
}