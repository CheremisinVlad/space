package com.own.space.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] ALLOWED = new String[]{
            "/login","/register","/api/registrations","logout","/error"
    };
    private static final String[] STATIC_CONTENT = new String[]{
            "/images/**","/js/**","/css/**","/static/**"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(STATIC_CONTENT);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http
                    .authorizeRequests().antMatchers(ALLOWED).permitAll().anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login")
                .and()
                    .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logged-out")
                .and()
                    .csrf().disable();
    }
}
