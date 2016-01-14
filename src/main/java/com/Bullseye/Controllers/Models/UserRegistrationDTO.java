package com.Bullseye.Controllers.Models;

import com.Bullseye.Models.Roles;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;


public class UserRegistrationDTO
{
    private int ID;
    
    @Size(min = 4, max = 32, message = "Username must be between 4 and 32 characters")
    private String Username;
    
    @Email(message = "Must be an Email")
    private String Email;
    
    @Size(min = 6, max = 32, message = "Password must be between 6 and 32 characters")
    private String Password;
    
    private String Captcha;
    
    private boolean Enabled                 = true;
    private boolean accountNonExpired       = true;
    private boolean credentialsNonExpired   = true;
    private boolean accountNonLocked        = true;
    private Set<Roles> User_Roles           = new HashSet<Roles>();
    
    public UserRegistrationDTO()
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

    public String getCaptcha() {
        return Captcha;
    }

    public void setCaptcha(String Captcha) {
        this.Captcha = Captcha;
    }
}