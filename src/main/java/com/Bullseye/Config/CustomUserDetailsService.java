package com.Bullseye.Config;

import com.Bullseye.Models.Roles;
import com.Bullseye.Models.Service.UserService;
import com.Bullseye.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
 
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserService userService;
    
    // Spring Automatically Checks The Password
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String argUsername) throws UsernameNotFoundException
    {
        Users user = userService.getUserByUsername(argUsername);
        if(user == null)
        {
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), getGrantedAuthorities(user));
    }
 
    /*
        Returns A List Of Roles Assigned To The User
    */
    private List<GrantedAuthority> getGrantedAuthorities(Users user)
    {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        for(Roles Role : user.getUser_Roles())
        {
            System.out.println("Adding Role: " + Role.getName());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + Role.getName()));
        }
        
        return authorities;
    }
}