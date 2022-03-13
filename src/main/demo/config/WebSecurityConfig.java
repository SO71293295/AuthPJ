package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.AccountUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enable method authorisation process
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountUserDetailsService userDetailsService;

    PasswordEncoder passwordEncoder() {
        //Hash of passwords using the BCrypt algorithm.
        return new BCryptPasswordEncoder(); // Use of the BCrypt algorithm.
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Set the implemented UserDetailsService in the AuthenticationManagerBuilder
        auth.userDetailsService(userDetailsService)     // Configure the created UserDetailsService
                .passwordEncoder(passwordEncoder());    // Specify password hashing method (BCrypt algorithm)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Set authorisation
        http.exceptionHandling()
                .accessDeniedPage("/accessDeniedPage")  // Password to transition when access is denied
            .and()
            .authorizeRequests()
                .antMatchers("/loginForm").permitAll()  // /loginForm allows access from all users
                .anyRequest().authenticated();          // /Except for loginForm, which requires authentication

        // ログイン設定
        http.formLogin()                                // Enable form authentication
                .loginPage("/loginForm")                // Password to display login form
                .loginProcessingUrl("/authenticate")    // Password of the form authentication process
                .usernameParameter("userName")          // Username of request parameter name
                .passwordParameter("password")          // Password of request parameter name
                .defaultSuccessUrl("/home")             // Default password to transition to on successful authentication
                .failureUrl("/loginForm?error=true");   // Password to be transitioned to in the event of authentication failure

        // ログアウト設定
        http.logout()
                .logoutSuccessUrl("/loginForm")         // Password to be transitioned to on successful logout
                .permitAll();                           // Access allows to all users
    }
}
