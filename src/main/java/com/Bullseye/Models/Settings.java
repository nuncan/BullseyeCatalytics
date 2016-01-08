package com.Bullseye.Models;

import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "Settings")
public class Settings implements Serializable
{
    @Id
    @GeneratedValue
    
    @Column(name = "Settings_ID", nullable = false)
    private int ID;
    
    @Column(name = "Lang", nullable = false, length = 32)
    private String Language;
    
    public Settings()
    {
        
    }
    
    public Settings(String argLanguage)
    {
        this.Language = argLanguage;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }
}