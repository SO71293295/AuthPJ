package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.MyUser;

public class AccountUserDetails implements UserDetails {
    private final MyUser myUser;

    public AccountUserDetails(MyUser myUser) {
        this.myUser = myUser;
    }

    public MyUser getUser() { // Methods that return MyUser
        return myUser;
    }

    public String getName() { // Methods that return name
        return this.myUser.getName();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // Method to return the list of permissions granted to the user
        return AuthorityUtils.createAuthorityList("ROLE_" + this.myUser.getRoleName());
    }

    @Override
    public String getPassword() { // Methods to return registered passwords
        return this.myUser.getPassword();
    }

    @Override
    public String getUsername() { // Methods to return usernames
        return this.myUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() { // Methods for determining the expiry status of an account
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // Methods for determining the locked status of an account
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // Methods for determining the expiry status of credentials
        return true;
    }

    @Override
    public boolean isEnabled() { // Methods for determining whether a user is valid
        return true;
    }
}
