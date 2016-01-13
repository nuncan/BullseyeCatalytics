package com.Bullseye.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
public class Roles implements Serializable
{
    @Id
    @GeneratedValue
    
    @Column(name = "Role_ID", nullable = false, unique = true)
    private int ID;
    
    @Column(name = "Role_Name", nullable = false, unique = true)
    private String Name;
    
    public Roles()
    {
        
    }

    public Roles(String Name)
    {
        this.Name = Name;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}