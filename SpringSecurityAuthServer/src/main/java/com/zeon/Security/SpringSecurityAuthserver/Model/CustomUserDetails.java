package com.zeon.Security.SpringSecurityAuthserver.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails extends Users implements UserDetails {//here we can reuse the UserDetails by extend the User here

    public CustomUserDetails(final Users users) {
        super(users);  //The constractor of the User 
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//here we set the role to the user

        return getRoles()//here we need to convert the getRoles Method to anthor type here
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return super.getPassword();//the super is the consturactor of the parent and we extends from user so the constractor is have the data of the User
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    //Properties about the Users acounts 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
