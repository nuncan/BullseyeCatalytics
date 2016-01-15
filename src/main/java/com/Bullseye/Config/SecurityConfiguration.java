package com.Bullseye.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
    
    @Autowired
    BCryptPasswordEncoder getBCryptPasswordEncoder;
    
    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception
    {
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
        customUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return customUsernamePasswordAuthenticationFilter;
    }
    
    //
    //  Configuring Spring Security With Our User Model
    //
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(getBCryptPasswordEncoder);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {  
        http
            // Used To Impose Captcha Check On Login
            .addFilter(customUsernamePasswordAuthenticationFilter())
            .authorizeRequests()
                // Allow Access To Landing Page & Resources
		.antMatchers("/", "/Resources/**").permitAll()
	  	.antMatchers("/Dashboard/**").access("hasRole('CLIENT') or hasRole('ADMIN')")
	  	.and()
            // Setup Login Page
            .formLogin()
                .loginPage("/Login").permitAll()
                .failureUrl("/Login?Error").permitAll()
                .defaultSuccessUrl("/Dashboard", true)
                .usernameParameter("username")
                .passwordParameter("password")
	  	.and()
            // Setup 'Remember Me' Option
            .rememberMe()
                .tokenValiditySeconds(604800) // 604800 Seconds = 1 Week (Causing Issues With Sign Out Button)
                .and()
            // Setup Logout Page
            .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/Logout").permitAll()
                .logoutSuccessUrl("/Login?LoggedOut")
                .and()
            // Setup CSRF Protection
            .csrf()
	  	.and()
            .exceptionHandling()
            .accessDeniedPage("/Denied");
    }
}
