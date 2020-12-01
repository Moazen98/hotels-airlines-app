package com.zeon.Security.AuthServer.ResourceServerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;





@EnableResourceServer
@Configuration
public class ResourceConfig extends WebSecurityConfigurerAdapter{

	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	//Authentication is the way to check the username and password of the user
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { //here we provide the Authentication Server

		auth.parentAuthenticationManager(authenticationManager)
		.inMemoryAuthentication()
		.withUser("peter")
		.password("peter")
		.roles("ADMIN"); 
	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	http.requestMatchers()
	.antMatchers("/login","/oauth/authorize") //Match this url
	.and()
	.authorizeRequests()  //authorized the request that come to this url
	.anyRequest()   //any request come to this url
	.authenticated() //make them authenticated
	.and()
	.formLogin()  //enable the login page to every body 
	.permitAll();
	
	
	}
	
	
	
	

}
