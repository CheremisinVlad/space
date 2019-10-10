package com.own.space.web.security.authenticate;

import com.own.space.web.util.JsonUtils;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonAuthFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger LOG = LoggerFactory.getLogger(JsonAuthFilter.class);

    public JsonAuthFilter() {
        super(new AntPathRequestMatcher("/api/authentication", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        String requestBody = IOUtils.toString(request.getReader());

        LOG.debug("processing request with body {}",requestBody);

        RequestDetails requestDetails = JsonUtils.objFromJson(requestBody, RequestDetails.class);
        if(requestDetails == null || requestDetails.getPassword().isBlank() || requestDetails.getUsername().isBlank()){
            throw new InsufficientAuthenticationException("incorrect authentication data");
        }
        return this.getAuthenticationManager().authenticate(createAuthToken(requestDetails));
    }

    private UsernamePasswordAuthenticationToken createAuthToken(RequestDetails requestDetails) {
        return new UsernamePasswordAuthenticationToken(requestDetails.getUsername(),requestDetails.getPassword());
    }

    @Data
    static class RequestDetails {
        private String username;
        private String password;
    }
}
