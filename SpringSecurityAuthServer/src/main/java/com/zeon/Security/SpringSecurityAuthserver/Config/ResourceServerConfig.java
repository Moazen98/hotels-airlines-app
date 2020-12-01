package com.zeon.Security.SpringSecurityAuthserver.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http.csrf().disable();
        http.requestMatchers()
                .antMatchers("/actuator/**","/login**","/oauth/authorize","/oauth/**","/loginnew**","/loginout**")  //the /login here is the spring login
            
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/health","/loginnew**","/oauth/authorize**","/loginout**","/login**").permitAll()
               
                .anyRequest()
                .authenticated()
                
                .and()
                .formLogin()
             //   .loginPage("/login")
            //    .loginProcessingUrl("/login")
                .permitAll();
        
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
//      auth.parentAuthenticationManager(authenticationManager)
//      .inMemoryAuthentication()
//      .withUser("mezo98")
//      .password("mezo")
//      .roles("USER");

        auth.parentAuthenticationManager(authenticationManager)
                .userDetailsService(customUserDetailsService);
    }
}
