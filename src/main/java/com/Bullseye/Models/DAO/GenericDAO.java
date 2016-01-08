package com.Bullseye.Models.DAO;

import java.util.List;

public interface GenericDAO <E, K> // E = Entity And K = Key
{
    public List<E> listAll();
    public E findByID(int argID, boolean isAPI);
    public void deleteByID(int argID);
    public void addByEntity(E argEntity);
    public void updateByEntity(E argEntity);
}