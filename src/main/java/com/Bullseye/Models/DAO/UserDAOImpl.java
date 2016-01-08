package com.Bullseye.Models.DAO;

import com.Bullseye.Models.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository("userDAO") // Declares (To Spring) This Class As Apart Of The Persistance Layer
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO
{
    @Autowired
    BCryptPasswordEncoder getBCryptPasswordEncoder;
    
    @Override
    public User getUserByEmail(String argEmail)
    {
        Criteria crit = getSession().createCriteria(persistentClass);
        crit.add(Restrictions.eq("Email", argEmail));
        crit.setFirstResult(0);                     // Result 0 Is First Result
        crit.setMaxResults(1);                      // Result 1 Is Max Result
        // Check Result
        if((User)crit.uniqueResult() != null) {
            return (User)crit.uniqueResult();
        }
        else {
            throw new RuntimeException("No User With That Email Found");
        }
    }
}