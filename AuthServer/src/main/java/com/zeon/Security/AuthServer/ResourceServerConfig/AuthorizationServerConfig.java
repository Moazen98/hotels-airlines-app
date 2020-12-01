package com.zeon.Security.AuthServer.ResourceServerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;



//the Authorization is check the role of the access user if he can access this page
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter{  //here spring provide us a Authentication server so we can costimiaze it here

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception { //here we secure the Authorization server

	security.tokenKeyAccess("permitAll()") //here we permit all to access the token key
	.checkTokenAccess("isAuthenticated()"); //here before we generate the token we need to validate it 

	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

	clients.inMemory()
	.withClient("ClientId") //here we give the user id
	.secret("secret") // we give secrete
	.authorizedGrantTypes("authorization_code")
	.scopes("user_info")
	.autoApprove(true); // to generate the token
	
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.authenticationManager(authenticationManager);
	}  
	
	
	

	
	
	 
	
	
}
