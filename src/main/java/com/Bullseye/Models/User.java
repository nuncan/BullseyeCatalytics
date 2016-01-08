package com.Bullseye.Models;

import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

@Entity
@Table(name = "Users")
public class User implements Serializable
{
    @Id
    @GeneratedValue
    
    @Column(name = "User_ID")
    private int ID;
    
    @Column(name = "Username")
    private String Username;
    
    @Column(name = "Email")
    private String Email;
    
    @Column(name = "Password")
    private String Password;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Settings Settings;
    
    public User()
    {
        this.Settings = new Settings("English");
    }
    
    public User(String argUsername, String argEmail, String argPassword)
    {
        this.Username = argUsername;
        this.Email = argEmail;
        this.Password = argPassword;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Settings getSettings() {
        return Settings;
    }

    public void setSettings(Settings Settings) {
        this.Settings = Settings;
    }
}