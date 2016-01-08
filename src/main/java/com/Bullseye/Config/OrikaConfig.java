package com.Bullseye.Config;

import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;

@Component
public class OrikaConfig implements FactoryBean<MapperFacade>
{
    @Override
    public MapperFacade getObject() throws Exception {
        return new DefaultMapperFactory.Builder().build().getMapperFacade();
    }
 
    @Override
    public Class<?> getObjectType() {
        return MapperFacade.class;
    }
 
    @Override
    public boolean isSingleton() {
        return true;
    }
}