package com.Bullseye.Models.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*
    (Another Layer)
    This Layer Handles:
        * Calls To Hibernate
        * Session
*/
public abstract class AbstractDAO
{
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public void persist(Object argEntity)
    {
        getSession().persist(argEntity);
    }

    public void delete(Object argEntity)
    {
        getSession().delete(argEntity);
    }
    
    public void update(Object argEntity)
    {
        getSession().update(argEntity);
    }
}