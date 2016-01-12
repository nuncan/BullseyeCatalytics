package com.Bullseye.Config;

import com.Bullseye.Models.Service.UserService;
import com.Bullseye.Models.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserService userService;
    
    // Spring Automatically Checks The Password
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String argUsername) throws UsernameNotFoundException
    {
        User user = userService.getUserByUsername(argUsername);
        if(user == null)
        {
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getStatus().equals("Active"), true, true, true, getGrantedAuthorities(user));
    }
 
    private List<GrantedAuthority> getGrantedAuthorities(User user)
    {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }
}