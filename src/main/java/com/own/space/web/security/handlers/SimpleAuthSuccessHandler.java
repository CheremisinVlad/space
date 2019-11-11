package com.own.space.web.security.handlers;

import com.own.space.domain.AuthUser;
import com.own.space.web.results.RequestResult;
import com.own.space.web.util.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        AuthUser authorizedUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JsonUtils.write(response.getWriter(), createResponse(authorizedUser));
    }

    private RequestResult createResponse(AuthUser authorizedUser) {
       return RequestResult.empty()
               .add("authenticated","true")
               .add("userId",authorizedUser.getId())
               .add("name",authorizedUser.getUsername());
    }
}
