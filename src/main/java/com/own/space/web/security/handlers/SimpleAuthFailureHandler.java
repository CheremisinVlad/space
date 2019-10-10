package com.own.space.web.security.handlers;

import com.own.space.web.results.RequestResult;
import com.own.space.web.util.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RequestResult failure;
         if (exception instanceof InsufficientAuthenticationException) {
            failure = RequestResult.withMessage("Bad authentication request");
        }else if (exception instanceof BadCredentialsException) {
            failure = RequestResult.withMessage("Invalid credentials");
        }else {
            failure = RequestResult.withMessage("Authentication failure");
        }
        JsonUtils.write(response.getWriter(), failure);
    }
}
