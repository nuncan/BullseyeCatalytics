package com.Bullseye.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {  
        http
            .authorizeRequests()
                // Allow Access To Landing Page & Resources
		.antMatchers("/", "/Index", "/Resources/**", "/Register/**").permitAll()
	  	.antMatchers("/Dashboard/**").access("hasRole('CLIENT') or hasRole('ADMIN')")
	  	.and()
            // Setup Login Page
            .formLogin()
                .loginPage("/Login").permitAll()
                .failureUrl("/Login?Error").permitAll()
                .defaultSuccessUrl("/Dashboard")
                .usernameParameter("username")
                .passwordParameter("password")
	  	.and()
            // Setup 'Remember Me' Option
            .rememberMe()
                .tokenValiditySeconds(604800) // 604800 Seconds = 1 Week
                .and()
            // Setup Logout Page
            .logout()
                .logoutUrl("/Logout").permitAll()
                .logoutSuccessUrl("/Login?LoggedOut")
                .and()
            // Setup CSRF Protection
            .csrf()
	  	.and()
            .exceptionHandling()
            .accessDeniedPage("/Denied");
    }
    
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService);
    }
    
    
/*    
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception
    {
	auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN", "CLIENT");
	auth.inMemoryAuthentication().withUser("demo").password("123456").roles("CLIENT");
    }
*/    
}
