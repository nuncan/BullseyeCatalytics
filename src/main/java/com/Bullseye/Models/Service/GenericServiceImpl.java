package com.Bullseye.Models.Service;

import com.Bullseye.Models.DAO.GenericDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K>
{
    private GenericDAO<E, K> genericDAO;
 
    public GenericServiceImpl()
    {
        // Empty
    }
        
    public GenericServiceImpl(GenericDAO<E, K> argDAO)
    {
        this.genericDAO = argDAO;
    }
    
    @Override
    @Transactional
    public void addByEntity(E argEntity)
    {
        genericDAO.addByEntity(argEntity);
    }
    
    @Override
    @Transactional
    public void updateByEntity(E argEntity)
    {
        genericDAO.updateByEntity(argEntity);
    }
    
    @Override
    @Transactional
    public void deleteByID(int argID)
    {
        genericDAO.deleteByID(argID);
    }
    
    @Override
    @Transactional
    public E findByID(int argID)
    {
        return(genericDAO.findByID(argID));
    }
    
    @Override
    @Transactional
    public List<E> listAll()
    {
        return(genericDAO.listAll());
    }
}