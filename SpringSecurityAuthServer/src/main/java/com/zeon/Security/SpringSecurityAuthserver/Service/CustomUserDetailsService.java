package com.zeon.Security.SpringSecurityAuthserver.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zeon.Security.SpringSecurityAuthserver.Model.CustomUserDetails;
import com.zeon.Security.SpringSecurityAuthserver.Model.Users;
import com.zeon.Security.SpringSecurityAuthserver.Repository.UsersRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//here we want to get data from database

        Optional<Users> usersOptional = usersRepository.findByName(username);

        usersOptional
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));//if we dont find user from 
        return usersOptional
                .map(CustomUserDetails::new)//the CustomUserDetails have a Constructor of Users its mean we convert 
                //CustomUserDetails::new is a refrance for (users -> new CustomUserDetails(users))
                .get();//return the result into spring framework
        //we can use the get user value in WebSecurityConfigurerAdapter Class By configure(AuthenticationManagerBuilderauth) method	
    }
    
    
    
}
