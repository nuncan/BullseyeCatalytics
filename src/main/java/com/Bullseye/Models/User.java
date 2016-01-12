package com.Bullseye.Models;

import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

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
    
    @Column(name = "Status")
    private String Status = "Active";
    
    @Column(name = "Role")
    private String Role = "CLIENT";

    public User()
    {
        
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    
}