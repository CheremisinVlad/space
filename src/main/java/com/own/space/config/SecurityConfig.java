package com.own.space.config;

import com.own.space.util.aspects.RepositoryExceptionInterceptor;
import com.own.space.web.security.authenticate.JsonAuthFilter;
import com.own.space.web.security.handlers.SimpleAuthFailureHandler;
import com.own.space.web.security.handlers.SimpleAuthSuccessHandler;
import com.own.space.web.security.handlers.SimpleLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] ALLOWED = new String[]{
            "/login","/register","/registration","/logout","/error"
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
                    .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class).formLogin().loginPage("/login")
                .and()
                    .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler())
                .and()
                    .csrf().disable();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleAuthSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleAuthFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new SimpleLogoutSuccessHandler();
    }
    @Bean
    public JsonAuthFilter authenticationFilter() throws Exception {
        JsonAuthFilter authFilter = new JsonAuthFilter();
        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        authFilter.setAuthenticationManager(authenticationManagerBean());
        return authFilter;
    }

}
