package com.zeon.UserPortable.SpringSecurityClientConfig;



import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
public class OauthConfig extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {

       // http.antMatcher("/**") //here it mean all url request that come
    	//http.csrf().disable();
    	   http
   			.antMatcher("/**")
                .authorizeRequests()// all url I need for authorization
                .antMatchers("/","/login**") // just this two page permit to all
                .permitAll()
                .antMatchers("/get-plane-port")
                .permitAll()
                .antMatchers("/get-all-hotel-amd")
                .permitAll()
                .antMatchers("/get-payment")
                .permitAll()
                .antMatchers("/get-check-info")
                .permitAll()
                .antMatchers("/user-hotel-post")
                .permitAll()
                .antMatchers("/search-hotel-city")
                .permitAll()
                .antMatchers("/register-user")
                .permitAll()
                .antMatchers("/index")
                .permitAll()
                .antMatchers("/user-edit-page-set")
                .permitAll()
               
                .antMatchers("/after-payment")
                .authenticated()
                .antMatchers("/payment-return-plane")
                .authenticated()
                .antMatchers("/payment-plane")
                .authenticated()
                .antMatchers("/check-taxi-exist")
                .authenticated()
                .antMatchers("/hotel-info")
                .authenticated()
                .antMatchers("/send-email")
                .authenticated()
                .antMatchers("/get-singel-room")
                .authenticated()
                .antMatchers("/get-dual-room")
                .authenticated()
                .antMatchers("/get-suit-room")
                .authenticated()
                .antMatchers("/user-hotel-post")
                .authenticated()
                .antMatchers("/user-edit-page")
                .authenticated()//
                .antMatchers("/planes")
                .permitAll()
                .antMatchers("/get-all-planes")
                .permitAll()
                .antMatchers("/get-all-planes-new")
                .permitAll()
                
                .anyRequest()
                .permitAll();
        
    }
}
