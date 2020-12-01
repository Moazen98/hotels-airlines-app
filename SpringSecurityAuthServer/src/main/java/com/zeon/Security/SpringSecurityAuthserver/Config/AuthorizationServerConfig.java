package com.zeon.Security.SpringSecurityAuthserver.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer  //is enable the Oauth Authoraization service 
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Value("signing-key:qweasd123")
    private String signingKey;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    	//AuthorizationServerSecurityConfigurer enable the security constraint on the access token of the endpoint

        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
        //isAuthenticated() expression is mean it return true when the user not anonymes so here it check the Access token
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	//ClientDetailsServiceConfigurer is a configure that define the client details service
        clients
                .inMemory()
                .withClient("ClientId") //clientId: (required) the client id. 
                //The client_id is a public identifier for apps. Even though it’s public, it’s best that it isn’t guessable by third parties
                .secret("secret") //secret: (required for trusted clients) the client secret the secert is speciel for client app
              //The client_secret is a secret known only to the application and the authorization server
                .authorizedGrantTypes("authorization_code","password")//authorizedGrantTypes: Grant types that are authorized for the client to use. Default value is empty
                .scopes("user_info")//scope: The scope to which the client is limited. If scope is undefined or empty (the default) the client is not limited by scope
                .accessTokenValiditySeconds(4000) //in second how mush the token access is a live
                .autoApprove(true);
        /*
         * An app requesting an access token has to know the client secret in order to gain the token. 
         * This prevents malicious apps that have not been authorized 
         * from using the tokens from ever obtaining a valid access token
         */
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	//AuthorizationServerEndpointsConfigurer defines the authorization and token endpoints and the token services.
        endpoints.tokenStore(tokenStore()).
        authenticationManager(authenticationManager)
        .accessTokenConverter(accessTokenConverter());
        
        
        
    }
    
    
    //The JwtAccessTokenConverter is decode the encode the jwt token into oauth information  
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
    	final JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
    	accessTokenConverter.setSigningKey(signingKey);
    	return accessTokenConverter;
    }
    
    
    public TokenStore tokenStore() {
    	return new JwtTokenStore(accessTokenConverter());
    }
    
}
