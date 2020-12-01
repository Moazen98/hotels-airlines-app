package com.zeon.HotelReservation.SpringSecurityClientConfig;



import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableOAuth2Sso
@Configuration
public class OauthConfig extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
       // http.antMatcher("/**") //here it mean all url request that come
    	   http
   			.antMatcher("/**")
                .authorizeRequests()// all url I need for authorization
                .antMatchers("/","/login**","/loginnew**","/loginnew") // just this two page permit to all
                .permitAll()
                .antMatchers("/home")
                .hasRole("ADMIN")
                .antMatchers("/register-hotel")
                .hasRole("ADMIN")
                .antMatchers("/save-hotel")
                .hasRole("ADMIN")
                .antMatchers("/get-all-hotel")
                .hasRole("ADMIN")
                .antMatchers("/delete-hotel")
                .hasRole("ADMIN")
                .antMatchers("/edit-hotel")
                .hasRole("ADMIN")
                .antMatchers("/enter-user-info")
                .hasRole("ADMIN")
                .antMatchers("/save-user")
                .hasRole("ADMIN")
                .antMatchers("/get-all-plane")
                .hasRole("ADMIN")
                .antMatchers("/home")
                .hasRole("ADMIN")
                .antMatchers("/delete-request-hotel") //////here it's dangours to still permit all see it 
                .permitAll()
                .antMatchers("/amd-hotel") //////here it's dangours to still permit all see it 
                .permitAll()
                .antMatchers("/amd-hotel-post") //////here it's dangours to still permit all see it 
                .permitAll()
                .antMatchers("/show-all-hotel-for-user")
                .permitAll()
                .antMatchers("/get-hotel-api")
                .permitAll()
                .antMatchers("/get-request-hotel-show")
                .permitAll()
                .antMatchers("/post-request-hotel-user")
                .permitAll()
                .antMatchers("/get-request-hotel-city")
                .permitAll()
                .antMatchers("/search-hotel")
                .permitAll()
                .antMatchers("/get-singleroom-price")
                .permitAll()
                .antMatchers("/user-hotel-post")
                .permitAll()
                .antMatchers("/planes")
                .permitAll()
                .antMatchers("/get-all-planes")
                .permitAll()
                .antMatchers("/get-all-planes-new")
                .permitAll()
               
                .anyRequest()
                .permitAll()
                
                .and()
                .logout().permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //the route of logout
		//		.logoutSuccessUrl("/login")
				.permitAll();
        
    }
}
