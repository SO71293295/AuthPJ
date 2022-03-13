package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MyUser;
import com.example.demo.repository.UserDao;

@Service
public class AccountUserDetailsService implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public AccountUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {          // Methods for retrieving account information from the database

        if (userName == null || "".equals(userName)) {
            throw new UsernameNotFoundException(userName + "is not found");
        }

        // Get a single User If there is no userName, an exception is raised
        try {
            // Get a User
            MyUser myUser = userDao.findUserByUserName(userName);

            if (myUser != null) {
                return new AccountUserDetails(myUser); // Generate UserDetails implementation class

            } else {
                throw new UsernameNotFoundException(userName + "is not found");
            }

        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException(userName + "is not found");
        }
    }
}
