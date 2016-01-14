package com.Bullseye.Models;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "Users")
public class Users implements Serializable
{
    @Id
    @GeneratedValue
    
    @Column(name = "User_ID", nullable = false, unique = true)
    private int ID;
    
    @Column(name = "User_Username", nullable = false, unique = true)
    private String Username;
    
    @Column(name = "User_Email", nullable = false, unique = true)
    private String Email;
    
    @Column(name = "User_Password", nullable = false)
    private String Password;
    
    @Column(name = "User_Enabled", nullable = false)
    private boolean Enabled;
    
    @Column(name = "User_accountNonExpired", nullable = false)
    private boolean accountNonExpired;
    
    @Column(name = "User_credentialsNonExpired", nullable = false)
    private boolean credentialsNonExpired;
    
    @Column(name = "User_accountNonLocked", nullable = false)
    private boolean accountNonLocked;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Roles> User_Roles = new HashSet<Roles>();
    
    public Users()
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

    public Set<Roles> getUser_Roles() {
        return User_Roles;
    }

    public void setUser_Roles(Set<Roles> User_Roles) {
        this.User_Roles = User_Roles;
    }
    
    public void addRole(Roles argRole) {
        this.User_Roles.add(argRole);
    }
}