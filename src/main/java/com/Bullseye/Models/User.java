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
    
    @Column(name = "User_ID", nullable = false, unique = true)
    private int ID;
    
    @Column(name = "Username", nullable = false, unique = true)
    private String Username;
    
    @Column(name = "Email", nullable = false, unique = true)
    private String Email;
    
    @Column(name = "Password", nullable = false)
    private String Password;
    
    @Column(name = "Role", nullable = false)
    private String Role;
    
    @Column(name = "Enabled", nullable = false)
    private boolean Enabled;
    
    @Column(name = "accountNonExpired", nullable = false)
    private boolean accountNonExpired;
    
    @Column(name = "credentialsNonExpired", nullable = false)
    private boolean credentialsNonExpired;
    
    @Column(name = "accountNonLocked", nullable = false)
    private boolean accountNonLocked;

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

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean Enabled) {
        this.Enabled = Enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public String toString() {
        return "User{" + "ID=" + ID + ", Username=" + Username + ", Email=" + Email + ", Password=" + Password + ", Role=" + Role + ", Enabled=" + Enabled + ", accountNonExpired=" + accountNonExpired + ", credentialsNonExpired=" + credentialsNonExpired + ", accountNonLocked=" + accountNonLocked + '}';
    }

}