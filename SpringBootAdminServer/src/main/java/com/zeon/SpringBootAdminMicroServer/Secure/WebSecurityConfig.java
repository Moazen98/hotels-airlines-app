package com.zeon.SpringBootAdminMicroServer.Secure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .formLogin()
            .loginPage("/login")
            .permitAll(); //	any one can  to access  the login and logout page.
        http
            .logout().logoutUrl("/logout").permitAll(); //	Configures login and logout.
        http.csrf()
            .ignoringAntMatchers("/actuator/**", "/instances/**", "/logout");
        //Disables CSRF-Protection for the endpoint the Spring Boot Admin Client
        //Disables CSRF-Protection for the actuator endpoints.
        http
            .authorizeRequests()
            .antMatchers("/**/*.css", "/assets/**", "/third-party/**", "/logout", "/login")
           
            .permitAll();
        http
            .authorizeRequests()
            .anyRequest()
            .authenticated();	//Every other request must be authenticated.
        http.httpBasic(); // Activate Http basic Auth for the server
    }
}
